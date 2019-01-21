package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DBAssignedSkill;
import Database.DBProject;
import Database.DBSkill;
import Database.DBProject;
import Database.MySQLConnection;
import Model.AssignedSkill;
import Model.ListPIC;
import Model.ListSkill;
import Model.Project;
import Model.Skill;

/**
 * Servlet implementation class ProjectController
 */
@WebServlet("/ProjectController")
public class ProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectController() {
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
				getAllProject(request,response);
				break;
			case "EDIT":
				getSelectedProject(request,response);
				break;
			case "DELETE":
				deleteProject(request,response);
				break;
			case "NEW":
				newProject(request,response);
				break;
			case "NEWPIC":
				newPIC(request,response);
				break;
			default:
				break;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void newPIC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con;
		List<String> sk = null;
		List<ListPIC> empl = null;
		String Project_id = request.getParameter("projectId");
		String empl_name = request.getParameter("empl_name");

		List<ListSkill> skillNames = null;

		try {
			con = MySQLConnection.getMySQLConnection();
			skillNames = DBProject.getListSelectedSkillNames(con,Project_id);
			
			con = MySQLConnection.getMySQLConnection();
			sk = DBProject.getSpecificProject(con,Project_id);

			con = MySQLConnection.getMySQLConnection();
			empl = DBProject.getRelatedPIC(con, Project_id,empl_name);
			
			request.setAttribute("listSkillAttribute", skillNames);
			request.setAttribute("projectSelectedAttribute", sk);
			request.setAttribute("listPICAttribute", empl);
			RequestDispatcher rd = request.getRequestDispatcher("/newPIC.jsp");
			rd.forward(request, response);
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void newProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con;
		
		List<ListSkill> skillNames = null;
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sm.format(new java.util.Date());
		try {
			con = MySQLConnection.getMySQLConnection();
			skillNames = DBProject.getListSkillNames(con);
			request.setAttribute("listSkillAttribute", skillNames);
			request.setAttribute("currDate",strDate);
			RequestDispatcher rd = request.getRequestDispatcher("/newProject.jsp");
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
				insertProject(request,response);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(param.equalsIgnoreCase("CREATEPIC")) {
			try {
				updateProjectPIC(request,response);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(param.equalsIgnoreCase("UPDATE")) {
			try {
				updateProject(request,response);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void updateProjectPIC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InterruptedException {
		String project_id = request.getParameter("projectIdForm");
		String empl_name = request.getParameter("ListPIC");
		if(empl_name==null) {
			empl_name = "";
		}
		int project_id1 = Integer.valueOf(project_id);
		Connection con;
		try {

			if(!empl_name.equalsIgnoreCase("")) {
				con = MySQLConnection.getMySQLConnection();
				DBProject.updateProjectPIC(con, project_id1, empl_name);
				
				Thread.sleep(500);
				getAllProject(request,response);	
			}else {
				Thread.sleep(500);
				getAllProject(request,response);			
						
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void updateProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InterruptedException {
		
		Connection con;
		String project_id = request.getParameter("projectIdForm");
		String project_name = request.getParameter("projectNameForm");
		String project_start_date = request.getParameter("startDateForm");
		String project_end_date = request.getParameter("endDateForm");
		String project_requirement = request.getParameter("ListSkill");
		String project_PIC = request.getParameter("picForm");
		String curr_requirement = request.getParameter("currReq");
		int project_id1 = Integer.valueOf(project_id);
		Date startDate1 =  Date.valueOf(project_start_date);
		Date endDate1 =  Date.valueOf(project_end_date);
		int empl_id1=0;////////////////////////////////////

		Project pr = new Project(project_id1, project_name,startDate1, endDate1,0,project_requirement,empl_id1,project_PIC);
		try {
			con = MySQLConnection.getMySQLConnection();
			DBProject.updateProject(con,pr);
			if(!curr_requirement.equalsIgnoreCase(project_requirement)) {
				con = MySQLConnection.getMySQLConnection();
				DBProject.resetProjectPIC(con,project_id1);
			}
			Thread.sleep(500);
			getAllProject(request,response);
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InterruptedException {
		String nama = request.getParameter("projectNameForm");
		String startDate = request.getParameter("projectStartForm");
		String endDate = request.getParameter("projectEndForm");
		String req = request.getParameter("ListSkill");
		Date startDate1 =  Date.valueOf(startDate);
		Date endDate1 =  Date.valueOf(endDate);
		Connection con;
		Project Project_id = new Project(0, nama,startDate1, endDate1,0,req,0,"-");
		try {
			con = MySQLConnection.getMySQLConnection();
			DBProject.insertProject(con,Project_id);
			Thread.sleep(500);
			getAllProject(request,response);	
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	private void deleteProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InterruptedException {
		Connection con;
		String Project_id = request.getParameter("projectId");
		try {
			con = MySQLConnection.getMySQLConnection();
			DBProject.deleteProject(con,Project_id);
			Thread.sleep(500);
			getAllProject(request,response);
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void getSelectedProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con;
		List<String> sk = null;
		String Project_id = request.getParameter("projectId");
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sm.format(new java.util.Date());
		List<ListSkill> skillNames = null;

		try {
			con = MySQLConnection.getMySQLConnection();
			skillNames = DBProject.getListSelectedSkillNames(con,Project_id);
			
			con = MySQLConnection.getMySQLConnection();
			sk = DBProject.getSpecificProject(con,Project_id);

			request.setAttribute("listSkillAttribute", skillNames);
			request.setAttribute("projectSelectedAttribute", sk);
			request.setAttribute("currDate",strDate);
			RequestDispatcher rd = request.getRequestDispatcher("/updateProjectView.jsp");
			rd.forward(request, response);
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void getAllProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection con;
		List<Project> sk = null;
		try {
			con = MySQLConnection.getMySQLConnection();
			sk = DBProject.getAllProjectActive(con);
			request.setAttribute("projectAttribute", sk);
			RequestDispatcher rd = request.getRequestDispatcher("/projectView.jsp");
			rd.forward(request, response);
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	

}
