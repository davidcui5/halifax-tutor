package group12.TutorProfile;

import group12.token_auth.IAccessToken;

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
