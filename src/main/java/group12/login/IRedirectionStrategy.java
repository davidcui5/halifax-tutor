package group12.login;

import group12.data_access.User;

public interface IRedirectionStrategy {
    void redirect(User user);
}
