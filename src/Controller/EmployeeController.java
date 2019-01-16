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

import com.mysql.cj.MysqlConnection;

import Database.DBUtils;
import Database.MySQLConnection;
import Model.Employee;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeController() {
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
				getAllEmployee(request,response);
				break;
			case "EDIT":
				getSelectedEmployee(request,response);
				break;
			case "DELETE":
				deleteEmployee(request,response);
				break;
			default:
				break;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("command");
		if(param.equalsIgnoreCase("CREATE")) {
			insertEmployee(request,response);
		}
		if(param.equalsIgnoreCase("UPDATE")) {
			updateEmployee(request,response);
		}
	}
	private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nama = request.getParameter("emplNameForm");
		String age = request.getParameter("emplAgeForm");
		String address = request.getParameter("emplAddressForm");
		int age1 = Integer.valueOf(age);
		Connection con;
		Employee emp_id = new Employee(0, nama, age1, 0, address);
		try {
			con = MySQLConnection.getMySQLConnection();
			DBUtils.insertEmployee(con,emp_id);
			getAllEmployee(request,response);	
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con;
		String emp_id = request.getParameter("employeeId");
		try {
			con = MySQLConnection.getMySQLConnection();
			DBUtils.deleteEmployee(con,emp_id);
			getAllEmployee(request,response);
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void getSelectedEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con;
		List<String> emp = null;
		String emp_id = request.getParameter("employeeId");
		try {
			con = MySQLConnection.getMySQLConnection();
			emp = DBUtils.getSpecificEmployee(con,emp_id);
			request.setAttribute("employeeSelectedAttribute", emp);
			RequestDispatcher rd = request.getRequestDispatcher("/updateView.jsp");
			rd.forward(request, response);
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void getAllEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection con;
		List<Employee> emp = null;
		try {
			con = MySQLConnection.getMySQLConnection();
			emp = DBUtils.getAllEmployeeActive(con);

			request.setAttribute("employeeAttribute", emp);
			RequestDispatcher rd = request.getRequestDispatcher("/employeeView.jsp");
			rd.forward(request, response);
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con;
		
		String emp_id =  request.getParameter("emplIdForm");
		String emp_name = request.getParameter("emplNameForm");
		String emp_age = request.getParameter("emplAgeForm");
		String emp_address = request.getParameter("emplAddressForm");
		int id1 = Integer.valueOf(emp_id);
		int emp_age1 = Integer.valueOf(emp_age);
		Employee empl = new Employee(id1, emp_name, 0, emp_age1, emp_address);
		try {
			con = MySQLConnection.getMySQLConnection();
			DBUtils.updateEmployee(con,empl);
			getAllEmployee(request,response);
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
