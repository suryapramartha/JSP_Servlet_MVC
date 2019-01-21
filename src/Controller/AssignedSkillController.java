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
import Database.MySQLConnection;
import Model.AssignedSkill;
import Model.ListSkill;
import Model.Skill;

/**
 * Servlet implementation class AssignedSkillController
 */
@WebServlet("/AssignedSkillController")
public class AssignedSkillController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignedSkillController() {
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
				getAllAssignedSkill(request,response);
				break;
			case "DELETE":
				deleteAssignedSkill(request,response);
				break;
			case "NEW":
				newAssignedSkill(request,response);
				break;
			default:
				break;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void newAssignedSkill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con;
		List<String> emp = null;
		List<ListSkill> skillNames = null;
		String id = request.getParameter("employeeId");
		try {
			con = MySQLConnection.getMySQLConnection();
			emp = DBAssignedSkill.getEmployee(con, id);
		
			con = MySQLConnection.getMySQLConnection();
			skillNames = DBAssignedSkill.getListSkillNames(con, id);
			request.setAttribute("currEmployeeAttribute",emp);
			request.setAttribute("listSkillAttribute", skillNames);
			RequestDispatcher rd = request.getRequestDispatcher("/newAssignedSkill.jsp");
			rd.forward(request, response);
				
		} catch (ClassNotFoundException | SQLException e) {
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
				insertAssignedSkill(request,response);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void insertAssignedSkill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InterruptedException {
		String empl_id = request.getParameter("emplIdForm");
		String empl_name = request.getParameter("emplNameForm");
		String skill_id = request.getParameter("ListSkill");
		if(skill_id==null) {
			skill_id = "";
		}
		int empl_id1 = Integer.valueOf(empl_id);
		
		
		Connection con;
		try {

			if(!skill_id.equalsIgnoreCase("")) {
				int skill_id1 = Integer.valueOf(skill_id);
				con = MySQLConnection.getMySQLConnection();
				String skill_name = DBAssignedSkill.getSkillNames(con, skill_id);
				
				AssignedSkill skill_ = new AssignedSkill(empl_id1, empl_name,skill_id1,skill_name,0);
				con = MySQLConnection.getMySQLConnection();
				DBAssignedSkill.insertAssignedSkill(con, skill_);
				getAllAssignedSkill(request,response);	
			}else {
				getAllAssignedSkill(request,response);			
						
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteAssignedSkill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InterruptedException {
		Connection con;
		String skill_id = request.getParameter("skillId");
		String empl_id = request.getParameter("emplId");
		try {
			con = MySQLConnection.getMySQLConnection();
			DBAssignedSkill.deleteAssignedSkill(con, empl_id, skill_id);
			Thread.sleep(500);
			getAllAssignedSkill(request,response);
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	private void getAllAssignedSkill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection con;
		List<AssignedSkill> sk = null;
		List<String> emp = null;
		String id = request.getParameter("employeeId");
		if(id==null) {
			id=request.getParameter("emplIdForm");
		}
		try {
			con = MySQLConnection.getMySQLConnection();
			sk = DBAssignedSkill.getAllAssignedSkill(con, id);
			con = MySQLConnection.getMySQLConnection();
			emp = DBAssignedSkill.getEmployee(con, id);
		
			request.setAttribute("assignedSkillAttribute", sk);
			request.setAttribute("currEmployeeAttribute",emp);
			RequestDispatcher rd = request.getRequestDispatcher("/assignedSkillView.jsp");
			rd.forward(request, response);
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	
}
