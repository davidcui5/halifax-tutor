package group12.search.dataaccess;

import group12.tokenauth.IAccessToken;

public class JWTAccessTokenMock implements IAccessToken {
    @Override
    public String generateToken(String userEmail) {
        if (userEmail.equals("zongming.liu@dal.ca")) {
            return "token";
        } else {
            return null;
        }
    }

    @Override
    public String decodeToken(String token) {
        if (token.equals("token")) {
            return "zongming@dal.ca";
        } else {
            return null;
        }
    }
}
