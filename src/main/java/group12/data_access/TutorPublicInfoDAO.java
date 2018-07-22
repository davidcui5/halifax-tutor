package group12.data_access;

import java.util.List;

public interface TutorPublicInfoDAO {
    List<TutorPublicInfo> getTutorPublicInfo(String school, String courseName);
}
