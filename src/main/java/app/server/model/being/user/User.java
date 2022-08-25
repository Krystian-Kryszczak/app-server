package app.server.model.being.user;

import app.server.model.being.Being;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
@Introspected
public class User extends Being {
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
		super(id, name);
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}
	public User(@NonNull String name, @Nullable String lastname, @NonNull String email, @NonNull String password) {
		super(name);
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}
}