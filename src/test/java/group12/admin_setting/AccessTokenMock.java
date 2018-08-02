package group12.admin_setting;

import group12.token_auth.IAccessToken;

public class AccessTokenMock implements IAccessToken {
    @Override
    public String generateToken(String userEmail) {
        return "token";
    }

    @Override
    public String decodeToken(String token) {
        return token;
    }
}
