package group12.admin_setting;

import group12.data_access.Student;
import group12.data_access.Tutor;

import java.util.ArrayList;
import java.util.List;

public class AdminSettingDAOMock implements IAdminSettingDAO {
    @Override
    public int countAdminByEmail(String email) {
        if(email.equals("validAdmin@email.com")){
            return 1;
        }
        else if(email.equals("sql failed")){
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public boolean setAdminPassword(String email, String password) {
        if(email.equals("sql failed")){
            return false;
        }
        return true;
    }

    @Override
    public boolean setSubPlanPrice(int planID, float price) {
        if(price < 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean setStudentBanStatus(int id, boolean status) {
        if(id < 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean setTutorBanStatus(int id, boolean status) {
        if(id < 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteReviewByID(int id) {
        if(id == 9){
            return false;
        }
        return true;
    }

    @Override
    public boolean setTutorRatingAndTotalRatings(float rating, int totalRatings, int tutorID) {
        if(tutorID == 9){
            return false;
        }
        return true;
    }

    //111 and 555 has reviews
    @Override
    public List<ReviewDTO> getReviewsMadeByStudent(int studentID) {
        if(studentID == 111){
            List<ReviewDTO> list = new ArrayList<>();
            list.add(new ReviewDTO(15,"s1 review for t5"));
            return list;
        }
        return null;
    }

    @Override
    public List<ReviewDTO> getReviewsMadeOnTutors(int tutorID) {
        if(tutorID == 555){
            List<ReviewDTO> list = new ArrayList<>();
            list.add(new ReviewDTO(15,"s1 review for t5"));
            return list;
        }
        return null;
    }

    //333 does not have reviews
    @Override
    public Student getStudentByEmail(String email) {
        if(email.equals("validS111@email.com")){
            Student s1 = new Student();
            s1.setStudentID(111);
            return s1;
        }
        if(email.equals("validS333@email.com")){
            Student s3 = new Student();
            s3.setStudentID(333);
            return s3;
        }
        return null;
    }

    //777 does not have reviews
    @Override
    public Tutor getTutorByEmail(String email) {
        if(email.equals("validT555@email.com")){
            Tutor t5 = new Tutor();
            t5.setTutorID(555);
            return t5;
        }
        if(email.equals("validT777@email.com")){
            Tutor t7 = new Tutor();
            t7.setTutorID(777);
            return t7;
        }
        return null;
    }

    @Override
    public Tutor getTutorByID(int tutorID) {
        if(tutorID == 555){
            Tutor t5 = new Tutor();
            t5.setTutorID(555);
            t5.setRating(5.0F);
            t5.setTotalRating(10);
            return t5;
        }
        if(tutorID == 777){
            Tutor t7 = new Tutor();
            t7.setTutorID(777);
            t7.setRating(5.0F);
            t7.setTotalRating(10);
            return t7;
        }
        if(tutorID == 9){
            Tutor t9 = new Tutor();
            t9.setTutorID(9);
            t9.setRating(5.0F);
            t9.setTotalRating(10);
            return t9;
        }
        return null;
    }

    @Override
    public ReviewDTO getReviewByReviewID(int reviewID) {
        if(reviewID == 15){
            return new ReviewDTO(15,"s1 review for t5",5.0F,555);
        }
        if(reviewID == 9){
            return new ReviewDTO(9,"deleting review 9 cause sql failure",5.0F,9);
        }
        if(reviewID == 10){
            return new ReviewDTO(10,"tutor 9 also cause sql failure",5.0F,9);
        }
        return null;
    }
}
