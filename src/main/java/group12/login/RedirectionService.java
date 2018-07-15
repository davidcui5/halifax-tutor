package group12.login;

import group12.data_access.User;

public class RedirectionService implements IRedirection {
    public void redirect(User user){
        IRedirectionStrategy redirectStrategy = user.createRedirectionStrategy();
        redirectStrategy.redirect(user);
    }
}