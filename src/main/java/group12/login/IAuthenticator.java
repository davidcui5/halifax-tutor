package group12.login;

import group12.data_access.User;

public interface IAuthenticator {
    void authenticate(User user);
}
