package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Skill;

public class DBSkill {
	public static List<Skill> getAllSkillActive(Connection conn) throws SQLException {
        String sql = "Select * from para_skill  where skill_status=0 ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Skill> list = new ArrayList<Skill>();
        while (rs.next()) {
            int id = rs.getInt("skill_id");
            String name = rs.getString("skill_name");
            String desc = rs.getString("skill_desc");          
            int status = rs.getInt("skill_status");
            Skill emp = new Skill(id,name,desc,status);
            list.add(emp);
        }
        conn.close();
        return list;
    }
	public static List<String> getSpecificSkill(Connection conn,String skill_id) throws SQLException {
        String sql = "Select * from para_skill  where skill_status=0 and skill_id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,skill_id);
        ResultSet rs = pstm.executeQuery();
        List<String> list = new ArrayList<String>();
        while (rs.next()) {
        	String id =String.valueOf(rs.getInt("skill_id"));
            String name = rs.getString("skill_name");
            String desc = rs.getString("skill_desc");          
            String status =String.valueOf(rs.getInt("skill_status"));
            
            list.add(id);
            list.add(name);
            list.add(desc);
            list.add(status);
            
        }
        conn.close();
        return list;
    }
	public static void updateSkill(Connection conn, Skill sk) throws SQLException {
        String sql = "Update para_skill set skill_name =?, skill_desc=? where skill_id=? and skill_status=0";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, sk.getSkill_name());
        pstm.setString(2, sk.getSkill_desc());
        pstm.setInt(3, sk.getSkill_id());
        pstm.executeUpdate();
        conn.close();
    }
 
    public static void insertSkill(Connection conn, Skill sk)  {
       
    	try{
    		int id = getNewId(conn);
    		String sql = "insert into para_skill values (?, ?, ?, ?)";
    		PreparedStatement pstm = conn.prepareStatement(sql);
    		pstm.setInt(1,id);
	        pstm.setString(2, sk.getSkill_name());
	        pstm.setString(3,sk.getSkill_desc());
	        pstm.setInt(4, sk.getSkill_status());
	        pstm.executeUpdate();
	        conn.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
 
    public static void deleteSkill(Connection conn, String skill_id) throws SQLException {
        String sql = "Delete From para_skill where skill_id= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, skill_id);
        pstm.executeUpdate();
        DBAssignedSkill.updateSkill(conn, skill_id);
    }
    
    public static int getNewId(Connection conn) throws SQLException {
    	int id = 0;
    	int idCore = 10;
    	String sql = "Select MAX(skill_id) as skill_id From para_skill";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            id = rs.getInt("skill_id");
        }

        if (id==0) {
        	return idCore+1;
        }else {
        	return id+1;
        }
    }
}
