package app.server.service.account;

import app.server.model.reset.ResetCode;
import app.server.model.being.user.User;
import app.server.security.PasswordEncoder;
import app.server.storage.repository.reset.ResetPasswordRepository;
import app.server.storage.repository.being.user.UserRepository;
import com.mongodb.client.result.InsertOneResult;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.runtime.ApplicationConfiguration;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bson.BsonValue;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Singleton
public class AccountService {
    static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    @Inject static ApplicationConfiguration.InstanceConfiguration instanceConfiguration;
    @Inject static UserRepository userRepository;
    @Inject static ResetPasswordRepository resetPasswordRepository;
    public Publisher<ObjectId> register(@Email String email, @NotNull @Size(min = 2, max = 60) String name, @NotNull @Size(min = 2, max = 60) String lastname,
        @NotNull @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$") String password) { // OK
//           return Mono.from(userRepository.save(new User(name, lastname, email, PasswordEncoder.sha256(password))))
//            .flatMap(insertOneResult -> {
//                Mono.from(userRepository.findByEmail(email)).map(user -> {
//                    logger.info("New account was created! (" + user.getEmail() + ")");
//                    return false;
//                });
//                return Mono.create(objectIdMonoSink -> {
//                    BsonValue value = insertOneResult.getInsertedId();
//                    if ((value != null)) {
//                        objectIdMonoSink.success(value.asObjectId().getValue());
//                    } else {
//                        objectIdMonoSink.error(new NullPointerException("BsonValue equal null.") {});
//                    }
//                });
//            });
        return Mono.create(monoSink -> {
            Mono.from(userRepository.findByEmail(email)).map(existUser -> { // if user with the same e-mail already exists
                logger.info("Failed: Account has not been created! Because the account with the email " + existUser.getEmail() + " already exits!");
                throw new RuntimeException() {
                };
            }).switchIfEmpty(
                Mono.from(userRepository.save(new User(name, lastname, email, PasswordEncoder.sha256(password)))) // Save new user in database
                .flatMap(insertOneResult -> Mono.from(userRepository.findByEmail(email)).mapNotNull(user -> {
                    logger.info("New account was created! (" + user.getEmail() + ")");
                    BsonValue value = insertOneResult.getInsertedId();
                    if ((value != null)) {
                        ObjectId objectId = value.asObjectId().getValue();
                        monoSink.success(objectId);
                        return objectId;
                    } else {
                        monoSink.error(new NullPointerException("BsonValue equal null.") {
                        });
                    }
                    return null;
                }))
            );
        });
    }
    public Publisher<HttpStatus> forgotPassword(@NotNull String email) { // Almost OK
        return Mono.from(userRepository.findDocumentByEmail(email)).flatMap(doc -> {
            ZonedDateTime dateTime = ZonedDateTime.of(LocalDateTime.now().plusHours(24L), ZoneId.of(instanceConfiguration.getZone().orElse("Europe/Warsaw")));
            String code = UUID.randomUUID().toString();
            Mono<InsertOneResult> resultPublisher
                = Mono.from(resetPasswordRepository.save(new ResetCode(code, doc.getString("_id"), dateTime.toLocalDateTime())));
            // TODO sending email with template
            return resultPublisher.map(result -> {
                BsonValue value = result.getInsertedId();
                if (value != null) return HttpStatus.OK;
                else return HttpStatus.CONFLICT; //new RuntimeException("Reset password failed!")
            });
        }).defaultIfEmpty(HttpStatus.CONFLICT);
    }
    public Publisher<HttpResponse<HttpStatus>> resetPassword(@NonNull String code, @NotNull String password) { // OK
        return Mono.from(resetPasswordRepository.find(code)).flatMap(resetCode -> Mono.create(
            monoSink -> {
                // ---------------------------------------------------------------------------------------------------- // [Code has been expired]
                if (ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(instanceConfiguration.getZone().orElse("Europe/Warsaw")))
                .toLocalDateTime().compareTo(resetCode.getExpiration()) > 0) { // check if resetCode has expired (true -> delete)
                    Mono.from(resetPasswordRepository.delete(code)).mapNotNull(deletedCode -> {
                        monoSink.success(HttpResponse.status(HttpStatus.NOT_FOUND, "The code has expired."));
                        return HttpResponse.status(HttpStatus.NOT_FOUND);
                    }).switchIfEmpty(Mono.just(HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)).map(v -> { // error
                        monoSink.success(HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR));
                        return v;
                    }));
                // ---------------------------------------------------------------------------------------------------- // [Else]
                } else { // code was found in database
                   Mono.from(userRepository.findById(resetCode.getUserId())).flatMap(
                        user -> Mono.from(userRepository.resetPassword(resetCode.getUserId(), password)) // if user has been found in database
                            .flatMap(
                                newUser -> Mono.from(resetPasswordRepository.delete(code)) // Successful
                                .map(v -> {
                                    monoSink.success(HttpResponse.status(HttpStatus.OK));
                                    return HttpResponse.status(HttpStatus.OK);
                                })
                            )).switchIfEmpty( // "User not found." - Error
                                Mono.just(HttpResponse.status(HttpStatus.NOT_FOUND)).map(notFound -> {
                                    monoSink.success(HttpResponse.status(HttpStatus.NOT_FOUND));
                                    return notFound;
                                })
                            );
                }
                // ---------------------------------------------------------------------------------------------------- //
            } //  && resetPasswordRepository.delete(code) ? HttpResponse.status(HttpStatus.OK) : HttpResponse.status(HttpStatus.CONFLICT, "Something went wrong!");
        ));//.switchIfEmpty(Mono.just(HttpResponse.status(HttpStatus.NOT_FOUND))); // if resetCode not exist
//        if (!resetCode.isPresent()) return HttpResponse.status(HttpStatus.NOT_FOUND, "Code not found."); // if resetCode not exist
//        if (ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(instanceConfiguration.getZone().orElse("Europe/Warsaw"))).toLocalDateTime().compareTo(resetCode.get().getExpiration()) > 0) { // check if resetCode has expired
//            return resetPasswordRepository.delete(code) ? HttpResponse.status(HttpStatus.NOT_FOUND, "The code has expired.") : HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        if (!userRepository.findById(code).isPresent()) return HttpResponse.status(HttpStatus.NOT_FOUND, "User not found.");
//        //if (user.get().getPassword().equals(PasswordEncoder.encode(password))) return HttpResponse.status(HttpStatus.CONFLICT, "The new password is the same as the old password.");
//        return userRepository.resetPassword(code, password) && resetPasswordRepository.delete(code) ? HttpResponse.status(HttpStatus.OK) : HttpResponse.status(HttpStatus.CONFLICT, "Something went wrong!");
    }
}
/*
####################
> TODO ReCaptcha
####################
*/