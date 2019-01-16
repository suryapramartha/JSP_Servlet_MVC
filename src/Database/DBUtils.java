package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Employee;

public class DBUtils {
	public static List<Employee> getAllEmployeeActive(Connection conn) throws SQLException {
        String sql = "Select * from para_employee  where empl_status=0 ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Employee> list = new ArrayList<Employee>();
        while (rs.next()) {
            // (empl_id,empl_name,empl_status,empl_age,empl_address)
            int id = rs.getInt("empl_id");
            String name = rs.getString("empl_name");
            int status = rs.getInt("empl_status");
            int age = rs.getInt("empl_age");
            String adress = rs.getString("empl_address");
            Employee emp = new Employee(id,name,status,age,adress);
            list.add(emp);
        }
        conn.close();
        return list;
    }
	public static List<String> getSpecificEmployee(Connection conn,String empl_id) throws SQLException {
        String sql = "Select * from para_employee  where empl_status=0 and empl_id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,empl_id);
        ResultSet rs = pstm.executeQuery();
        List<String> list = new ArrayList<String>();
        while (rs.next()) {
            // (empl_id,empl_name,empl_status,empl_age,empl_address)
            String id = rs.getString("empl_id");
            String name = rs.getString("empl_name");
            int status = rs.getInt("empl_status");
            int age = rs.getInt("empl_age");
            String adress = rs.getString("empl_address");
            
            list.add(id);
            list.add(name);
            list.add(String.valueOf(status));
            list.add(String.valueOf(age));
            list.add(adress);
            
        }
        conn.close();
        return list;
    }
	public static void updateEmployee(Connection conn, Employee emp) throws SQLException {
        String sql = "Update para_employee set empl_name =?, empl_age=?, empl_address=? where empl_id=? and empl_status=0";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, emp.getEmpl_name());
        pstm.setInt(2, emp.getEmpl_age());
        pstm.setString(3, emp.getEmpl_address());
        pstm.setInt(4, emp.getEmpl_id());
        pstm.executeUpdate();
        conn.close();
    }
 
    public static void insertEmployee(Connection conn, Employee emp)  {
       
    	try{
    		int id = getNewId(conn);
    		String sql = "insert into para_employee values (?, ?, ?, ?, ?)";
    		PreparedStatement pstm = conn.prepareStatement(sql);
    		pstm.setInt(1,id);
	        pstm.setString(2, emp.getEmpl_name());
	        pstm.setInt(3, emp.getEmpl_age());
	        pstm.setInt(4,emp.getEmpl_status());
	        pstm.setString(5, emp.getEmpl_address());
	        pstm.executeUpdate();
	        conn.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
 
    public static void deleteEmployee(Connection conn, String empl_id) throws SQLException {
        String sql = "Delete From para_employee where empl_id= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, empl_id);
        pstm.executeUpdate();
        conn.close();
    }
    
    public static int getNewId(Connection conn) throws SQLException {
    	int id = 0;
    	int idCore = 10000;
    	String sql = "Select MAX(empl_id) as empl_id From para_employee";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            id = rs.getInt("empl_id");
        }

        if (id==0) {
        	return idCore+1;
        }else {
        	return id+1;
        }
    }
    
}
