package group12.tutorsetting.dataaccess;

import group12.tokenauth.IAccessToken;

public class JWTAccessTokenMock implements IAccessToken {
    @Override
    public String generateToken(String userEmail) {
        if (userEmail.equals("zongming.liu@dal.ca")) {
            return "token";
        } else {
            return "";
        }
    }

    @Override
    public String decodeToken(String token) {
        if (token.equals("token")) {
            return "zongming.liu@dal.ca";
        } else {
            return "";
        }
    }
}