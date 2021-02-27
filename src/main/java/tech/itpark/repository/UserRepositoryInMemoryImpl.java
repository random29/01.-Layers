package tech.itpark.repository;

import tech.itpark.entity.UserEntity;

import java.util.*;

public class UserRepositoryInMemoryImpl implements UserRepository {
    private UUID nextId;
    private final Map<UUID, UserEntity> storage = new HashMap<>();
    private final Map<String, UserEntity> loginToEntity = new HashMap<>();
//    private final Set<String> logins = new HashSet<>(); // кеш

    @Override
    public List<UserEntity> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public Optional<UserEntity> findById(UUID uuid) {
        return Optional.ofNullable(storage.get(uuid));
    }

    @Override
    public UserEntity save(UserEntity entity) {
        if (entity.getId().equals(0)) {
            entity.setId(UUID.randomUUID());
        }
        loginToEntity.put(entity.getLogin(), entity);
        return storage.put(entity.getId(), entity);
    }

    @Override
    public boolean removeById(UUID uuid) {
        return storage.remove(uuid) != null;
    }

    public boolean isExisting(String login) {
        return loginToEntity.containsKey(login);
    }

    @Override
    public Optional<UserEntity> findByLogin(String login) {
        return Optional.ofNullable(loginToEntity.get(login));
    }
}
