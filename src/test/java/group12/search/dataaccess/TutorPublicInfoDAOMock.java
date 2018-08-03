package group12.search.dataaccess;

import java.util.ArrayList;
import java.util.List;

public class TutorPublicInfoDAOMock implements TutorPublicInfoDAO {
    @Override
    public List<TutorPublicInfo> getTutorPublicInfo(String school, String courseName) {
        List<TutorPublicInfo> results = new ArrayList<>();
        if (school.equals("DAL") && courseName.equals("CSCI5308")) {
            results.add(new TutorPublicInfo());
        }
        return results;
    }
}
