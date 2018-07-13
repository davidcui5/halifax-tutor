package group12.data_access;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class SQLOperationTemplate {

    private DataSource dataSource;
    private ArrayList<Object> parameters;
    private static Logger logger = LogManager.getLogger(SQLOperationTemplate.class);

    public SQLOperationTemplate(Object... parameters){
        this.parameters = new ArrayList<Object>();
        for(Object parameter : parameters){
            this.parameters.add(parameter);
        }
    }

    abstract String makeSQL();
    abstract PreparedStatement addParameters(PreparedStatement ps);
    abstract Object extractResultSet(ResultSet rs) throws SQLException;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<Object> getParameters() {
        return parameters;
    }

    public Object executeMysqlQuery(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = makeSQL();
        Object result = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps = addParameters(ps);
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
