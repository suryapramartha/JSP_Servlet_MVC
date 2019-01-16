package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class MySQLConnection {
	    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost/dev_studycase1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=" + TimeZone.getDefault().getID();
		static final String USER = "root";
		static final String PASS = ""; 
		
		public static Connection getMySQLConnection() throws SQLException,ClassNotFoundException {
			Class.forName(JDBC_DRIVER);
	        Connection connect = DriverManager.getConnection(DB_URL,USER,PASS);
	        return connect;
		}
}
