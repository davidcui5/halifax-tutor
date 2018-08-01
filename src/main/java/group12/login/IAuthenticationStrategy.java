package group12.login;

import group12.dataaccess.User;

public interface IAuthenticationStrategy {
    void authenticate(User user);
}
