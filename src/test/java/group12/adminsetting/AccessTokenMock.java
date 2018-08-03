package group12.adminsetting;

import group12.tokenauth.IAccessToken;

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
