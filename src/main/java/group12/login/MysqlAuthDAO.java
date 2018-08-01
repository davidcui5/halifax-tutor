package group12.login;

import group12.data_access.*;

public class MysqlAuthDAO implements IAuthDAO {

    @Override
    public Student getStudentByEmail(String email) {
        SQLOperationTemplate op = new GetStudentSQLOperation(email);
        return (Student) op.executeMysqlQuery();
    }

    @Override
    public Tutor getTutorByEmail(String email) {
        SQLOperationTemplate op = new GetTutorSQLOperation(email);
        return (Tutor) op.executeMysqlQuery();
    }

    @Override
    public Admin getAdminByEmail(String email) {
        SQLOperationTemplate op = new GetAdminSQLOperation(email);
        return (Admin) op.executeMysqlQuery();
    }
}
