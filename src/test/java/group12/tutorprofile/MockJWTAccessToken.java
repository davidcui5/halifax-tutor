package group12.tutorprofile;

import group12.tokenauth.IAccessToken;

public class MockJWTAccessToken implements IAccessToken {
    @Override
    public String generateToken(String userEmail) {
        return "MOCKTOKEN";
    }

    @Override
    public String decodeToken(String token) {
        return "rh318779@dal.ca";
    }
}
