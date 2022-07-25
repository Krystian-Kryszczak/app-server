package app.server.model.being.user;

import app.server.model.being.Being;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import lombok.Builder;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;

@Builder
@Introspected
public class User extends Being {
	@NonNull
	@NotBlank
	@BsonProperty("name")
	private final String name;
	@Nullable
	@BsonProperty("lastname")
	private final String lastname;
	@NonNull
	@BsonProperty("email")
	private final String email;
	@NonNull
	@BsonProperty("password")
	private final String password;
	@Creator
	@BsonCreator
	public User(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("name") String name, @Nullable @BsonProperty("lastname") String lastname,
				@NonNull @BsonProperty("email") String email, @NonNull @BsonProperty("password") String password) {
		super(id);
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}
	public User(@NonNull String name, @Nullable String lastname, @NonNull String email, @NonNull String password) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}
	@NonNull
	public String getName() {
		return name;
	}
	@Nullable
	public String getLastname() {
		return lastname;
	}
	@NonNull
	public String getEmail() {
		return email;
	}
	@NonNull
	public String getPassword() {
		return password;
	}
}