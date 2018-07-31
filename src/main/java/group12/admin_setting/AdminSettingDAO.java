package group12.admin_setting;

import group12.data_access.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AdminSettingDAO implements IAdminSettingDAO {
    private static Logger logger = LogManager.getLogger(AdminSettingDAO.class);

    @Override
    public int countAdminByEmail(String email) {
        try{
            SQLOperationTemplate op = new CountAdminByEmailSQLOp(email);
            return (int)op.executeMysqlQuery();
        }
        catch(Exception e){
            logger.error("Error",e);
        }
        return 0;
    }

    @Override
    public boolean setAdminPassword(String email, String password) {
        try{
            SQLOperationTemplate op = new UpdateAdminPasswordSQLOperation(email,password);
            return (Boolean)op.executeMysqlQuery();
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
            return (Boolean)op.executeMysqlQuery();
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
            return (Boolean)op.executeMysqlQuery();
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
            return (Boolean)op.executeMysqlQuery();
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
    public boolean deleteReviewByID(int id) {
        try{
            SQLOperationTemplate op = new DeleteReviewByIDSQLOperation(id);
            return (Boolean)op.executeMysqlQuery();
        }
        catch(Exception e){
            logger.error("Error",e);
        }
        return false;
    }

    @Override
    public int getStudentIDByEmail(String email) {
        SQLOperationTemplate op = new GetStudentSQLOperation(email);
        Student s = (Student)op.executeMysqlQuery();
        if(s == null){
            return -1;
        }
        else{
            return s.getStudentID();
        }
    }

    @Override
    public int getTutorIDByEmail(String email) {
        SQLOperationTemplate op = new GetTutorSQLOperation(email);
        Tutor t = (Tutor)op.executeMysqlQuery();
        if(t == null){
            return -1;
        }
        else{
            return t.getTutorID();
        }
    }
}
