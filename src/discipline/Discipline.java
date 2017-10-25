package discipline;

import java.util.ArrayList;
import java.util.HashSet;

public class Discipline {
	
	private String code;
	private String name;
	private int workload;
	private ArrayList<Discipline> prerequisites;
	
	public Discipline(String code, String name, int workload, ArrayList<Discipline> prerequisites) {
		this.code = code;
		this.name = name;
		this.workload = workload;
		this.prerequisites = prerequisites;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getWorkload() {
		return workload;
	}
	
	public void setWorkload(int workload) {
		this.workload = workload;
	}
	
	public ArrayList<Discipline> getPrerequisites() {
		return prerequisites;
	}
	
	public void setPrerequisite(ArrayList<Discipline> prerequisites) {
		this.prerequisites = prerequisites;
	}

}
