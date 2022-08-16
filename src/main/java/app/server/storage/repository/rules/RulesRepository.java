package app.server.storage.repository.rules;

import app.server.model.rules.Rules;
import app.server.storage.repository.Repository;

public interface RulesRepository<T extends Rules> extends Repository<T> {}