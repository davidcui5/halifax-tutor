package group12.search;

import java.util.List;

public interface TutorPublicInfoDAO {
    List<TutorPublicInfo> getTutorPublicInfo(String school, String courseName);
}
