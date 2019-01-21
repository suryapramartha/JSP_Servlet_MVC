package Model;

import java.util.Date;

public class Project {
	private int project_id 	;
	private String project_name ;
	private Date project_start_date;
	private Date project_end_date ;
	private int project_status 	;
	private String project_requirement ;
	private int empl_id;
	private String empl_name;
	
	public Project(int project_id, String project_name, Date project_start_date, Date project_end_date,
			int project_status, String project_requirement,int empl_id,String empl_name) {
		super();
		this.project_id = project_id;
		this.project_name = project_name;
		this.project_start_date = project_start_date;
		this.project_end_date = project_end_date;
		this.project_status = project_status;
		this.project_requirement = project_requirement;
		this.empl_id = empl_id;
		this.empl_name = empl_name;
	}
	
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public Date getProject_start_date() {
		return project_start_date;
	}
	public void setProject_start_date(Date project_start_date) {
		this.project_start_date = project_start_date;
	}
	public Date getProject_end_date() {
		return project_end_date;
	}
	public void setProject_end_date(Date project_end_date) {
		this.project_end_date = project_end_date;
	}
	public int getProject_status() {
		return project_status;
	}
	public void setProject_status(int project_status) {
		this.project_status = project_status;
	}
	public String getProject_requirement() {
		return project_requirement;
	}
	public void setProject_requirement(String project_requirement) {
		this.project_requirement = project_requirement;
	}
	public int getEmpl_id() {
		return empl_id;
	}
	public void setEmpl_id(int empl_id) {
		this.empl_id = empl_id;
	}
	public String getEmpl_name() {
		return empl_name;
	}
	public void setEmpl_name(String empl_name) {
		this.empl_name = empl_name;
	}
	
}
