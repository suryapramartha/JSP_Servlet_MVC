package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.AssignedSkill;
import Model.Employee;
import Model.ListSkill;
import Model.Skill;

public class DBAssignedSkill {

	
	public static List<String> getAssignedSkill(Connection conn,String empl_id) throws SQLException {
        String sql = "Select * from para_skill  where skill_status=0 and skill_id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,empl_id);
        ResultSet rs = pstm.executeQuery();
        List<String> list = new ArrayList<String>();
        while (rs.next()) {
        	String emp_id =String.valueOf(rs.getInt("empl_id"));
            String emp_name = rs.getString("empl_name");
            String skill_id =String.valueOf(rs.getInt("skill_id"));          
            String skill_name = rs.getString("skill_name");
            
            list.add(emp_id);
            list.add(emp_name);
            list.add(skill_id);
            list.add(skill_name);
        }
        conn.close();
        return list;
    }

 
    public static void insertAssignedSkill(Connection conn, AssignedSkill sk)  {
       
    	try{
    		String sql = "insert into empl_assigned_skill values (?, ?, ?, ?,?)";
    		PreparedStatement pstm = conn.prepareStatement(sql);
    		pstm.setInt(1,sk.getEmpl_id());
	        pstm.setString(2, sk.getEmpl_name());
	        pstm.setInt(3, sk.getSkill_id());
	        pstm.setString(4,sk.getSkill_name());
	        pstm.setInt(5,0);
	        pstm.executeUpdate();
	        conn.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
 
    public static void deleteAssignedSkill(Connection conn, String empl_id,String skill_id) throws SQLException {
        String sql = "Delete From empl_assigned_skill where empl_id=? and skill_id= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1,Integer.valueOf(empl_id));
        pstm.setInt(2, Integer.valueOf(skill_id));
        pstm.executeUpdate();
        conn.close();
    }

    public static List<AssignedSkill> getAllAssignedSkill(Connection conn,String empl_id) throws SQLException {
        String sql = "Select * from empl_assigned_skill  where empl_id=? and skill_status=0";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, empl_id);
 
        ResultSet rs = pstm.executeQuery();
        List<AssignedSkill> list = new ArrayList<AssignedSkill>();
        while (rs.next()) {
            int emp_id = rs.getInt("empl_id");
            String emp_name = rs.getString("empl_name");
            int skill_id = rs.getInt("skill_id");
            String skill_name = rs.getString("skill_name");
            int skill_status = rs.getInt("skill_status");
            
            AssignedSkill emp = new AssignedSkill(emp_id,emp_name,skill_id,skill_name,skill_status);
            list.add(emp);
        }
        conn.close();
        return list;
    }
    public static List<String> getEmployee(Connection conn,String empl_id) throws SQLException {
        String sql = "Select * from para_employee  where empl_id=? and empl_status=0";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, empl_id);
 
        ResultSet rs = pstm.executeQuery();
        List<String> list = new ArrayList<String>();
        while (rs.next()) {
            String emp_id =String.valueOf(rs.getInt("empl_id"));
            String emp_name = rs.getString("empl_name");
            String emp_age = String.valueOf(rs.getInt("empl_age"));
            String emp_address = rs.getString("empl_address");

            list.add(emp_id);
            list.add(emp_name);
            list.add(emp_age);
            list.add(emp_address);
        }
        conn.close();
        return list;
    }
    public static List<ListSkill> getListSkillNames(Connection conn,String empl_id) throws SQLException {
        String sql = "Select skill_id,skill_name from para_skill  where skill_status=0 and skill_id not in "
        		+ "(select skill_id from empl_assigned_skill where empl_id=? and skill_status=0)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, empl_id);
 
        ResultSet rs = pstm.executeQuery();
        List<ListSkill> list = new ArrayList<ListSkill>();
        while (rs.next()) {
        	
            int skill_id =rs.getInt("skill_id");
            String skill_name = rs.getString("skill_name");
            
            ListSkill emp = new ListSkill(skill_id,skill_name);
            list.add(emp);
        }
        conn.close();
        return list;
    }
    public static String getSkillNames(Connection conn,String skill_id) throws SQLException {
        String sql = "Select skill_name from para_skill where skill_id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, skill_id);
        String skill_name=null;
        ResultSet rs = pstm.executeQuery();
        
        while (rs.next()) {
        	
        	skill_name = rs.getString("skill_name");
        }
        conn.close();
        return skill_name;
    }
    public static void updateSkill(Connection conn,String skill_id) throws SQLException {
        String sql = "update empl_assigned_skill set skill_status=1 where skill_id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, skill_id);
        pstm.executeUpdate();
        
        conn.close();
    }
    public static void updateEmployee(Connection conn, Employee emp) throws SQLException {
        String sql = "Update empl_assigned_skill set empl_name =? where empl_id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, emp.getEmpl_name());
        pstm.setInt(2, emp.getEmpl_id());
        pstm.executeUpdate();
        conn.close();
    }
    public static void updateSkill(Connection conn, Skill sk) throws SQLException {
        String sql = "Update empl_assigned_skill set skill_name =? where skill_id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, sk.getSkill_name());
        pstm.setInt(2, sk.getSkill_id());
        pstm.executeUpdate();
        conn.close();
    }
}
