package spirngmvc.classLoader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.omg.CORBA.portable.ApplicationException;

public class Snippet {
	public List queryResult(String sql) throws ApplicationException {
	        List IMap = null;
	        try {
	            //      sql = sqlEncode(sql);
	            DbUtils.loadDriver("");
	            QueryRunner qRunner = new QueryRunner();
	            Connection connection=null;
	            IMap = (List)qRunner.query(connection, sql, new MapListHandler());
	        } catch (Exception expt) {
	        } finally {
	        }
	        return IMap;
	    }
	
	public Map<String, Object> toMap(ResultSet rs) throws SQLException {
        Map<String, Object> result = null;
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();

        for (int i = 1; i <= cols; i++) {
            result.put(rsmd.getColumnName(i), rs.getObject(i));
        }

        return result;
    }
}

