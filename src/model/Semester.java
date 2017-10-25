package model;

import java.util.HashSet;

public class Semester {
	
	private HashSet<Discipline> disciplines;
	private int workload;
	
	public Semester() {
		disciplines = new HashSet<Discipline>();
		workload = 0;
	}
	
	public HashSet<Discipline> getDisciplines() {
		return disciplines;
	}
	
	public void addDiscipline(Discipline discipline) {
		this.disciplines.add(discipline);
	}
	
	public int getWorkload() {
		return workload;
	}
	
	public void incWorkload(int increase) {
		this.workload += increase;
	}

}
