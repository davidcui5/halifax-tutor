package group12.login;

import group12.data_access.User;

public interface IAuthenticationStrategy {
    void authenticate(User user);
}