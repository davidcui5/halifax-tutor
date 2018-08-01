package group12.adminsetting;

import group12.dataaccess.*;

import java.util.List;

public class AdminSettingDAO implements IAdminSettingDAO {
    @Override
    public int countAdminByEmail(String email) {
        SQLOperationTemplate op = new CountAdminByEmailSQLOp(email);
        return (int)op.executeMysqlQuery();
    }

    @Override
    public boolean setAdminPassword(String email, String password) {
        SQLOperationTemplate op = new UpdateAdminPasswordSQLOperation(email,password);
        return (Boolean)op.executeMysqlQuery();
    }

    @Override
    public boolean setSubPlanPrice(int planID, float price) {
        SQLOperationTemplate op = new UpdateSubPriceSQLOperation(planID, price);
        return (Boolean)op.executeMysqlQuery();
    }

    @Override
    public boolean setStudentBanStatus(int id, boolean status) {
        SQLOperationTemplate op = new SetStudentBannedStatusSQLOperation(id, status);
        return (Boolean)op.executeMysqlQuery();
    }

    @Override
    public boolean setTutorBanStatus(int id, boolean status) {
        SQLOperationTemplate op = new SetTutorBannedStatusSQLOperation(id, status);
        return (Boolean)op.executeMysqlQuery();
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
        SQLOperationTemplate op = new DeleteReviewByIDSQLOperation(id);
        return (Boolean)op.executeMysqlQuery();
    }
}
