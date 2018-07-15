package group12.login;

import group12.data_access.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//The context for Strategy pattern
//Knows about authentication
public class AuthenticationService implements IAuthenticator {

    private static final Logger logger = LogManager.getLogger(AuthenticationService.class);

    @Override
    public void authenticate(User user) {
        IAuthenticationStrategy authStrategy = user.createAuthenticationStrategy();
        authStrategy.authenticate(user);
    }
}