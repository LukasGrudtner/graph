package discipline;

public class Discipline {
	
	private String name;
	private int workload;
	private Discipline prerequisite;
	
	public Discipline(String name, int workload, Discipline prerequisite) {
		this.name = name;
		this.workload = workload;
		this.prerequisite = prerequisite;
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
	
	public Discipline getPrerequisite() {
		return prerequisite;
	}
	
	public void setPrerequisite(Discipline prerequisite) {
		this.prerequisite = prerequisite;
	}

}
