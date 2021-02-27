package tech.itpark.service;

import tech.itpark.entity.UserEntity;
import tech.itpark.exception.UsernameAlreadyExistsException;
import tech.itpark.exception.UsernameNotExistsException;
import tech.itpark.model.*;
import tech.itpark.repository.UserRepository;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

public class UserServiceDefaultImpl implements UserService {
    private final UserRepository repository;

    public UserServiceDefaultImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserModel register(RegistrationModel registrationModel) {
        if (repository.isExisting(registrationModel.getLogin())) {
            throw new UsernameAlreadyExistsException(registrationModel.getLogin());
        }
        UserEntity entity = repository.save(new UserEntity(
                UUID.fromString("0"),
                registrationModel.getSecret(),
                registrationModel.getLogin(),
                registrationModel.getPassword(),
                registrationModel.getName(),
                Set.of("ROLE_USER"),
                false,
                OffsetDateTime.now().toEpochSecond()
        ));
        return new UserModel(
                entity.getId(),
                entity.getLogin(),
                entity.getName(),
                entity.getRoles(),
                entity.isRemoved(),
                entity.getCreated()
        );
    }

    @Override
    public UserModel login(AuthenticationModel authenticationModel) {
        UserEntity entity = repository
                .findByLogin(authenticationModel.getLogin()).orElseThrow(
                        () -> new UsernameNotExistsException(authenticationModel.getLogin()));
        if (entity.isRemoved()) {
            throw new UsernameNotExistsException(authenticationModel.getLogin());
        }
        if (!authenticationModel.getPassword().equals(entity.getPassword())) {
            throw new UsernameNotExistsException();
        }
        return new UserModel(
                entity.getId(),
                entity.getLogin(),
                entity.getName(),
                entity.getRoles(),
                entity.isRemoved(),
                entity.getCreated()
        );
    }

    @Override
    public UserModel reset(ResetModel resetModel) {
        UserEntity entity = repository
                .findByLogin(resetModel.getLogin()).orElseThrow(() -> new UsernameNotExistsException(resetModel.getLogin()));
        if (entity.getSecret().equals(resetModel.getSecret())) {
            entity.setPassword(resetModel.getNewPassword());
            repository.save(entity);
        }
        return new UserModel(
                entity.getId(),
                entity.getLogin(),
                entity.getName(),
                entity.getRoles(),
                entity.isRemoved(),
                entity.getCreated()
        );
    }

    @Override
    public boolean remove(RemovableModel removableModel) {
        UserEntity entity = repository
                .findByLogin(removableModel.getLogin()).orElseThrow(() -> new UsernameNotExistsException(removableModel.getLogin()));
        if (entity.getPassword() == removableModel.getPassword()) {
            entity.setRemoved(true);
            repository.save(entity);
        }
        return true;
    }
}
// FIXME: reset, remove impl. Remove make flag! RepoInMemory.