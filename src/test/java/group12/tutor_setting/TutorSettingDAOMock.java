package group12.tutor_setting;

import group12.data_access.WeeklySchedule;

public class TutorSettingDAOMock implements ITutorSettingDAO {
    @Override
    public boolean updateTutorPassword(String email, String password) {
        if (email.contains("zongming")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateTutorEmail(String email, String newEmail) {
        if (email.contains("zongming")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateTutorPhone(String email, String phone) {
        if (email.contains("zongming")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateTutorCard(String email, String creditCardHolder, String creditCardNum, String creditCardExpiryDate, int securityCode) {
        if (email.contains("zongming")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateEducation(String email, String education) {
        if (email.contains("zongming")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updatePhoto(String email, String photoURL) {
        if (email.contains("zongming")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateExperience(String email, String experience) {
        if (email.contains("zongming")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addCourse(String email, String school, String courseCode, String price) {
        if (email.contains("zongming")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeCourse(String email, String school, String courseCode) {
        if (email.contains("zongming")) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean updateWeeklySchedule(String email, WeeklySchedule weeklySchedule) {
        if (email.contains("zongming")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updatePlan(String email, String planNo) {
        if (email.contains("zongming")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean cancelPlan(String email) {
        if (email.contains("zongming")) {
            return true;
        } else {
            return false;
        }
    }
}
