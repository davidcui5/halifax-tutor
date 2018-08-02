package group12.admin_setting;

import group12.data_access.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AdminSettingDAO implements IAdminSettingDAO {
    private static Logger logger = LogManager.getLogger(AdminSettingDAO.class);

    @Override
    public int countAdminByEmail(String email) {
         SQLOperationTemplate op = new CountAdminByEmailSQLOp(email);
         return (int)op.executeMysqlQuery();
    }

    @Override
    public boolean setAdminPassword(String email, String password) {
        try{
            SQLOperationTemplate op = new UpdateAdminPasswordSQLOperation(email,password);
            return (boolean)op.executeMysqlQuery();
        }
        catch(Exception e){
            logger.error("Error",e);
        }
        return false;
    }

    @Override
    public boolean setSubPlanPrice(int planID, float price) {
        try{
            SQLOperationTemplate op = new UpdateSubPriceSQLOperation(planID, price);
            return (boolean)op.executeMysqlQuery();
        }
        catch(Exception e){
            logger.error("Error",e);
        }
        return false;
    }

    @Override
    public boolean setStudentBanStatus(int id, boolean status) {
        try{
            SQLOperationTemplate op = new SetStudentBannedStatusSQLOperation(id, status);
            return (boolean)op.executeMysqlQuery();
        }
        catch(Exception e){
            logger.error("Error",e);
        }
        return false;
    }

    @Override
    public boolean setTutorBanStatus(int id, boolean status) {
        try{
            SQLOperationTemplate op = new SetTutorBannedStatusSQLOperation(id, status);
            return (boolean)op.executeMysqlQuery();
        }
        catch(Exception e){
            logger.error("Error",e);
        }
        return false;
    }

    @Override
    public boolean deleteReviewByID(int id) {
        try{
            SQLOperationTemplate op = new DeleteReviewByIDSQLOperation(id);
            return (boolean)op.executeMysqlQuery();
        }
        catch(Exception e){
            logger.error("Error",e);
        }
        return false;
    }

    @Override
    public boolean setTutorRatingAndTotalRatings(float rating, int totalRatings, int tutorID) {
        try{
            SQLOperationTemplate op = new SetTutorRatingAndTotalRatingsSQLOp(rating, totalRatings, tutorID);
            return (boolean)op.executeMysqlQuery();
        }
        catch(Exception e){
            logger.error("Error",e);
        }
        return false;
    }

    @Override
    public List<ReviewDTO> getReviewsMadeByStudent(int studentID) {
        SQLOperationTemplate op = new GetReviewsByStudentIDSQLOp(studentID);
        return (List<ReviewDTO>)op.executeMysqlQuery();
    }

    @Override
    public List<ReviewDTO> getReviewsMadeOnTutors(int tutorID) {
        SQLOperationTemplate op = new GetReviewsByTutorIDSQLOp(tutorID);
        return (List<ReviewDTO>)op.executeMysqlQuery();
    }

    @Override
    public Student getStudentByEmail(String email) {
        SQLOperationTemplate op = new GetStudentByEmailSQLOperation(email);
        return (Student)op.executeMysqlQuery();
    }

    @Override
    public Tutor getTutorByEmail(String email) {
        SQLOperationTemplate op = new GetTutorEmailSQLOperation(email);
        return (Tutor)op.executeMysqlQuery();
    }

    @Override
    public Tutor getTutorByID(int tutorID) {
        SQLOperationTemplate op = new GetTutorByIDSQLOp(tutorID);
        return (Tutor)op.executeMysqlQuery();
    }

    @Override
    public ReviewDTO getReviewByReviewID(int reviewID) {
        SQLOperationTemplate op = new GetReviewByReviewIDSQLOp(reviewID);
        return (ReviewDTO) op.executeMysqlQuery();
    }
}