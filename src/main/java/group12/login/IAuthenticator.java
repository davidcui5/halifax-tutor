package group12.login;

public interface IAuthenticator {

    LoginResponse authenticate(LoginForm form);
}
