package app.server.storage.repository.being.user;

import app.server.model.being.user.User;
import app.server.security.PasswordEncoder;
import app.server.security.PasswordEncoderImpl;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.being.BeingMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;

import javax.validation.constraints.Email;

import static com.mongodb.client.model.Filters.eq;

@Singleton
public class MongoDbUserRepository extends BeingMongoDbRepository<User> implements UserRepository {
    @Inject
    PasswordEncoder passwordEncoder;
    public MongoDbUserRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getUserCollection(), User.class);
    }
    @Override
    public Publisher<User> findByEmail(@NonNull @Email String email) {
        return getCollection().find(eq("email", email)).first();
    }
    @Override
    public Publisher<Document> findDocumentByEmail(@NonNull @Email String email) {
        return getDocCollection().find(eq("email", email)).first();
    }
    @Override
    @NonNull
    public Publisher<User> list() {
        return getCollection().find();
    }
    @Override
    public Publisher<User> resetPassword(@NonNull String hexId, @NonNull String newPassword) {
        return getCollection().findOneAndUpdate(eq("_id", new ObjectId(hexId)), eq("password", passwordEncoder.encode(newPassword)));
    }
}