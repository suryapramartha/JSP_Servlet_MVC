package Model;

public class Employee {
	private int empl_id;
	private String empl_name;
	private int empl_status;
	private int empl_age;
	private String empl_address;
	public Employee(int empl_id, String empl_name, int empl_status, int empl_age, String empl_address) {
		super();
		this.empl_id = empl_id;
		this.empl_name = empl_name;
		this.empl_status = empl_status;
		this.empl_age = empl_age;
		this.empl_address = empl_address;
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
	public int getEmpl_status() {
		return empl_status;
	}
	public void setEmpl_status(int empl_status) {
		this.empl_status = empl_status;
	}
	public int getEmpl_age() {
		return empl_age;
	}
	public void setEmpl_age(int empl_age) {
		this.empl_age = empl_age;
	}
	public String getEmpl_address() {
		return empl_address;
	}
	public void setEmpl_address(String empl_address) {
		this.empl_address = empl_address;
	}

}
