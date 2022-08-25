package app.server.api.being.community;

import app.server.api.being.BeingController;
import app.server.model.being.community.Community;

public abstract class CommunityController<T extends Community> extends BeingController<T> {}