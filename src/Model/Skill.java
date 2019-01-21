package Model;

public class Skill {
	private int skill_id; 	
	private String skill_name; 
	private String skill_desc; 
	private int skill_status;
	public Skill(int skill_id, String skill_name, String skill_desc, int skill_status) {
		super();
		this.skill_id = skill_id;
		this.skill_name = skill_name;
		this.skill_desc = skill_desc;
		this.skill_status = skill_status;
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
	public String getSkill_desc() {
		return skill_desc;
	}
	public void setSkill_desc(String skill_desc) {
		this.skill_desc = skill_desc;
	}
	public int getSkill_status() {
		return skill_status;
	}
	public void setSkill_status(int skill_status) {
		this.skill_status = skill_status;
	}
	
}
