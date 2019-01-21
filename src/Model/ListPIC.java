package Model;

public class ListPIC {
	private String empl_id;
	private String empl_name;
	
	
	public ListPIC(String empl_id, String empl_name) {
		super();
		this.empl_id = empl_id;
		this.empl_name = empl_name;
	}
	public String getEmpl_id() {
		return empl_id;
	}
	public void setEmpl_id(String empl_id) {
		this.empl_id = empl_id;
	}
	public String getEmpl_name() {
		return empl_name;
	}
	public void setEmpl_name(String empl_name) {
		this.empl_name = empl_name;
	}
}
