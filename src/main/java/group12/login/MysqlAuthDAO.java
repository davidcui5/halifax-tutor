package group12.login;

import group12.dataaccess.*;

public class MysqlAuthDAO implements IAuthDAO {

    @Override
    public Student getStudentByEmail(String email) {
        SQLOperationTemplate op = new GetStudentByEmailSQLOperation(email);
        return (Student) op.executeMysqlQuery();
    }

    @Override
    public Tutor getTutorByEmail(String email) {
        SQLOperationTemplate op = new GetTutorEmailSQLOperation(email);
        return (Tutor) op.executeMysqlQuery();
    }

    @Override
    public Admin getAdminByEmail(String email) {
        SQLOperationTemplate op = new GetAdminSQLOperation(email);
        return (Admin) op.executeMysqlQuery();
    }
}
