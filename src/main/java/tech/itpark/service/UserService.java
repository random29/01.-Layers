package tech.itpark.service;

import tech.itpark.model.*;

public interface UserService {
    UserModel register(RegistrationModel registrationModel);
    UserModel login(AuthenticationModel authenticationModel);
    UserModel reset(ResetModel resetModel);
    boolean remove(RemovableModel removableModel);
}
