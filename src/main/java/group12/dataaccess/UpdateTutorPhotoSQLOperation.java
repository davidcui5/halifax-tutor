package group12.dataaccess;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTutorPhotoSQLOperation extends SQLDMLOperation {
    private static Logger logger = LogManager.getLogger(UpdateTutorPhotoSQLOperation.class);

    public UpdateTutorPhotoSQLOperation(String email, String photoURL) {
        super(email, photoURL);
    }

    @Override
    protected String makeSQL() {
        return "UPDATE Tutor " +
        "SET Tutor.PhotoUrl = ? " +
        "WHERE Tutor.Email = ?";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        logger.log(Level.INFO, email);
        String photoURL = (String) getParameters().get(1);
        logger.log(Level.INFO, photoURL);
        ps.setString(1, photoURL);
        ps.setString(2, email);
        return ps;
    }

    @Override
    boolean isSuccess(int numOfResult) {
        return numOfResult >= 1;
    }
}
