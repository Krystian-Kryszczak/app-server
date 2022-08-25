package app.server.api;

import io.micronaut.security.authentication.Authentication;
import org.bson.types.ObjectId;

public abstract class ApiController {
    protected boolean authValidate(Authentication authentication) {
        return ObjectId.isValid((String)authentication.getAttributes().get("id"));
    }
    protected String getClientHexId(Authentication authentication) {
        return (String)authentication.getAttributes().get("id");
    }
}