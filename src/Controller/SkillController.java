package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DBAssignedSkill;
import Database.DBSkill;
import Database.DBUtils;
import Database.MySQLConnection;
import Model.Employee;
import Model.Skill;

/**
 * Servlet implementation class SkillController
 */
@WebServlet("/SkillController")
public class SkillController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SkillController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String step = request.getParameter("command");
			if(step==null) {
				step = "LOAD_ALL";
			}
			
			switch (step) {
			case "LOAD_ALL":
				getAllSkill(request,response);
				break;
			case "EDIT":
				getSelectedSkill(request,response);
				break;
			case "DELETE":
				deleteSkill(request,response);
				break;
			default:
				break;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("command");
		if(param.equalsIgnoreCase("CREATE")) {
			try {
				insertSkill(request,response);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(param.equalsIgnoreCase("UPDATE")) {
			try {
				updateSkill(request,response);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void insertSkill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InterruptedException {
		String nama = request.getParameter("skillNameForm");
		String desc = request.getParameter("skillDescForm");
		Connection con;
		Skill skill_id = new Skill(0, nama,desc, 0);
		try {
			con = MySQLConnection.getMySQLConnection();
			DBSkill.insertSkill(con,skill_id);
			Thread.sleep(500);
			getAllSkill(request,response);	
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	private void deleteSkill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InterruptedException {
		Connection con;
		String skill_id = request.getParameter("skillId");
		try {
			con = MySQLConnection.getMySQLConnection();
			DBSkill.deleteSkill(con,skill_id);
			Thread.sleep(500);
			getAllSkill(request,response);
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void getSelectedSkill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con;
		List<String> sk = null;
		String skill_id = request.getParameter("skillId");
		try {
			con = MySQLConnection.getMySQLConnection();
			sk = DBSkill.getSpecificSkill(con,skill_id);
			request.setAttribute("skillSelectedAttribute", sk);
			RequestDispatcher rd = request.getRequestDispatcher("/updateSkillView.jsp");
			rd.forward(request, response);
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void getAllSkill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection con;
		List<Skill> sk = null;
		try {
			con = MySQLConnection.getMySQLConnection();
			sk = DBSkill.getAllSkillActive(con);
			request.setAttribute("skillAttribute", sk);
			RequestDispatcher rd = request.getRequestDispatcher("/skillView.jsp");
			rd.forward(request, response);
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @throws InterruptedException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	private void updateSkill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InterruptedException {
		Connection con;
		
		String skill_id =  request.getParameter("skillIdForm");
		String skill_name = request.getParameter("skillNameForm");
		String skill_desc = request.getParameter("skillDescForm");
		int id1 = Integer.valueOf(skill_id);
		Skill skillful = new Skill(id1, skill_name, skill_desc,0);
		try {
			con = MySQLConnection.getMySQLConnection();
			DBSkill.updateSkill(con,skillful);
			
			con = MySQLConnection.getMySQLConnection();
			DBAssignedSkill.updateSkill(con,skillful);
			
			Thread.sleep(500);
			getAllSkill(request,response);
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
