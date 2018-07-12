package group12.data_access;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DatabaseOperationTemplate {

    private static Logger logger = LogManager.getLogger(DatabaseOperationTemplate.class);
    private DataSource dataSource;

    //concrete class defines SQL, and ways to extract ResultSet
    abstract String makeSQL();
    abstract Object extractResultSet(ResultSet rs) throws SQLException;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Object executeMysqlQuery(String... parameters){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = makeSQL();
        Object result = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            for (int i = 0; i < parameters.length; i++) {
                ps.setString(i + 1, parameters[i]);
            }
            rs = ps.executeQuery();
            if(rs.next()){
                result = extractResultSet(rs);
                return result;
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            logger.error("SQL Error", e);
        } finally {
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                if(con != null){
                    con.close();
                }
            }catch(Exception e){
                logger.error("Close Connection Error", e);
            }
        }
        return result;
    }
}
