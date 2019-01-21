package Model;

public class AssignedSkill {
	private int empl_id ;	
	private String empl_name; 	
	private int skill_id ;	
	private String skill_name ;
	private int skill_status;
	
	public AssignedSkill(int empl_id, String empl_name, int skill_id, String skill_name,int skill_status) {
		super();
		this.empl_id = empl_id;
		this.empl_name = empl_name;
		this.skill_id = skill_id;
		this.skill_name = skill_name;
		this.setSkill_status(skill_status);
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
	public int getSkill_id() {
		return skill_id;
	}
	public void setSkill_id(int skill_id) {
		this.skill_id = skill_id;
	}
	public String getSkill_name() {
		return skill_name;
	}
	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}
	public int getSkill_status() {
		return skill_status;
	}
	public void setSkill_status(int skill_status) {
		this.skill_status = skill_status;
	}
}
