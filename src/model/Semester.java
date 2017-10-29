package model;

import java.util.HashSet;

public class Semester {
	
	private HashSet<Discipline> disciplines;		/* Conjunto de disciplinas pertencentes ao semestre. */
	private int workload;							/* Carga hor�ria do semestre (somat�rio da carga hor�ria de todas as disciplinas deste semestre). */
	
	public Semester() {
		disciplines = new HashSet<Discipline>();
		workload = 0;
	}
	
	/* Retorna um conjunto das disciplinas deste semestre. */
	public HashSet<Discipline> getDisciplines() {
		return disciplines;
	}
	
	/* Adiciona uma disciplina ao conjunto. */
	public void addDiscipline(Discipline discipline) {
		this.disciplines.add(discipline);
	}
	
	/* Retorna a carga hor�ria deste semestre. */
	public int getWorkload() {
		return workload;
	}
	
	
	/* Incrementa a carga hor�ria deste semestre. */
	public void incWorkload(int increase) {
		this.workload += increase;
	}

}
