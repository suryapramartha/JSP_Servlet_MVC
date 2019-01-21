package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Model.Employee;
import Model.ListPIC;
import Model.ListSkill;
import Model.Project;
import Model.Skill;

public class DBProject {
	public static List<Project> getAllProjectActive(Connection conn) throws SQLException {
        String sql = "Select * from para_project  where project_status=0 ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Project> list = new ArrayList<Project>();
        while (rs.next()) {
            // (project_id,project_name,project_status,project_age,project_address)
            int id = rs.getInt("project_id");
            String name = rs.getString("project_name");
            Date start_date = rs.getDate("project_start_date");
            Date end_date = rs.getDate("project_end_date");
            int status = rs.getInt("project_status");
            String req = rs.getString("project_requirement");
            int empl_id = rs.getInt("empl_id");
            String empl_name = rs.getString("empl_name");
            Project emp = new Project(id,name,start_date,end_date,status,req,empl_id,empl_name);
            list.add(emp);
        }
        conn.close();
        return list;
    }
	public static List<String> getSpecificProject(Connection conn,String project_id) throws SQLException {
        String sql = "Select * from para_project  where project_status=0 and project_id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,project_id);
        ResultSet rs = pstm.executeQuery();
        List<String> list = new ArrayList<String>();
        while (rs.next()) {
            String id = String.valueOf(rs.getInt("project_id"));
            String name = rs.getString("project_name");
            String start_date = rs.getDate("project_start_date").toString();
            String end_date = rs.getDate("project_end_date").toString();
            String status =String.valueOf(rs.getInt("project_status"));
            String req = rs.getString("project_requirement");
            String pic = rs.getString("empl_name");
            
            list.add(id);
            list.add(name);
            list.add(start_date);
            list.add(end_date);
            list.add(status);
            list.add(req);
            list.add(pic);
            
        }
        conn.close();
        return list;
    }
	public static List<ListPIC> getRelatedPIC(Connection conn,String project_id,String empl_name) throws SQLException {
		String sql;
		PreparedStatement pstm ;
		if(empl_name.equalsIgnoreCase("-")||empl_name==null) {
			sql = "Select empl_id,empl_name from empl_assigned_skill where skill_status=0 and skill_name in"
	        		+ " (select project_requirement from para_project where project_id=?)";
	 	
			
			pstm = conn.prepareStatement(sql);
	        pstm.setString(1,project_id);
			
		}else {
			sql=  "select (SELECT empl_id from para_employee where empl_name in " + 
	        		"        		 ( select empl_name from para_project where project_id=?)  " + 
	        		"        		) as empl_id," + 
	        		"empl_name  from para_project where project_id=? " + 
	        		" UNION   " + 
	        		"        Select empl_id,empl_name from empl_assigned_skill where skill_status=0 and skill_name in" + 
	        		"        		  (select project_requirement from para_project where project_id=?)" + 
	        		"		and empl_name not in(select empl_name from para_project where project_id=?)" ; 
			pstm = conn.prepareStatement(sql);
	        pstm.setString(1,project_id);
	        pstm.setString(2,project_id);
	        pstm.setString(3,project_id);
	        pstm.setString(4,project_id);

	        
		}
        ResultSet rs = pstm.executeQuery();
        List<ListPIC> list = new ArrayList<ListPIC>();
        while (rs.next()) {
            String id = String.valueOf(rs.getInt("empl_id"));
            String name = rs.getString("empl_name");
            
            ListPIC emp = new ListPIC(id,name);
            list.add(emp);
            
        }
        conn.close();
        return list;
       
        
    }
	public static void updateProject(Connection conn, Project emp) throws SQLException {
        String sql = "Update para_project set project_name =?, project_start_date=?,project_end_date=?,project_requirement=? where project_id=? and project_status=0";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, emp.getProject_name());
        pstm.setDate(2, (Date) emp.getProject_start_date());
        pstm.setDate(3, (Date) emp.getProject_end_date());
        pstm.setString(4, emp.getProject_requirement());
        pstm.setInt(5, emp.getProject_id());
        pstm.executeUpdate();
        conn.close();
    }
	public static void resetProjectPIC(Connection conn, int project_id) throws SQLException {
        String sql = "Update para_project set empl_id=0,empl_name='-' where project_id=? and project_status=0";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, project_id);
        pstm.executeUpdate();
        conn.close();
    }
	public static void updateProjectPIC(Connection conn, int project_id,String empl_name) throws SQLException {
        String sql = "Update para_project set empl_id=(select empl_id from para_employee where empl_name=? and empl_status=0),"
        		+ "empl_name=? where project_id=? and project_status=0";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,empl_name);
        pstm.setString(2, empl_name);
        pstm.setInt(3,project_id);
        
        pstm.executeUpdate();
        conn.close();
    }
 
    public static void insertProject(Connection conn, Project emp)  {
       
    	try{
    		int id = getNewId(conn);
    		String sql = "insert into para_project values (?, ?, ?, ?, ?,?,?,?)";
    		PreparedStatement pstm = conn.prepareStatement(sql);
    		pstm.setInt(1,id);
	        pstm.setString(2, emp.getProject_name());
	        pstm.setDate(3,(Date) emp.getProject_start_date());
	        pstm.setDate(4,(Date)emp.getProject_end_date());
	        pstm.setInt(5, emp.getProject_status());
	        pstm.setString(6, emp.getProject_requirement());
	        pstm.setInt(7, 0);
	        pstm.setString(8, "-");
	        pstm.executeUpdate();
	        conn.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
 
    public static void deleteProject(Connection conn, String project_id) throws SQLException {
        String sql = "Delete From para_project where project_id= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, project_id);
        pstm.executeUpdate();
        conn.close();
    }
    
    public static int getNewId(Connection conn) throws SQLException {
    	int id = 0;
    	int idCore = 8000;
    	String sql = "Select MAX(project_id) as project_id From para_project";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            id = rs.getInt("project_id");
        }

        if (id==0) {
        	return idCore+1;
        }else {
        	return id+1;
        }
    }
    
    public static List<ListSkill> getListSkillNames(Connection conn) throws SQLException {
        String sql = "Select skill_id,skill_name from para_skill  where skill_status=0 ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
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
    public static List<ListSkill> getListSelectedSkillNames(Connection conn,String project_id) throws SQLException {
        String sql =
        		" select (SELECT skill_id from para_skill where skill_name in "
        		+ "( select project_requirement from para_project where project_id=?)" + 
        		") as skill_id,project_requirement as skill_name from para_project where project_id=?" + 
        		" UNION " + 
        		"Select skill_id,skill_name from para_skill  "
        		+ "where skill_status=0 and skill_name not in (select project_requirement from para_project where project_id=?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, project_id);
        pstm.setString(2, project_id);
        pstm.setString(3, project_id);
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
    public static void updateEmployee(Connection conn, Employee emp) throws SQLException {
        String sql = "Update para_project set empl_name =? where empl_id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, emp.getEmpl_name());
        pstm.setInt(2, emp.getEmpl_id());
        pstm.executeUpdate();
        conn.close();
    }
}
