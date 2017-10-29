package model;

import java.util.ArrayList;
import java.util.HashSet;

/*
 * Implementa��o da Disciplina.
 */
public class Discipline {
	
	private String code;							/* C�digo da disciplina. 											*/
	private String name;							/* Nome da disciplina. 												*/
	private int workload;							/* Carga hor�ria semanal da disciplina. 							*/
	private ArrayList<Discipline> prerequisites; 	/* Lista de disciplinas que s�o pr�-requisitos desta disciplina. 	*/
	private int semester;
	
	public Discipline(String code, String name, int workload, ArrayList<Discipline> prerequisites) {
		this.code = code;
		this.name = name;
		this.workload = workload;
		this.prerequisites = prerequisites;
	}
	
	/* Retorna o c�digo da disciplina. */
	public String getCode() {
		return code;
	}
	
	/* Insere o c�digo na disciplina. */
	public void setCode(String code) {
		this.code = code;
	}
	
	/* Retorna o nome da disciplina. */
	public String getName() {
		return name;
	}
	
	/* Insere o nome da disciplina. */
	public void setName(String name) {
		this.name = name;
	}
	
	/* Retorna a carga hor�ria semanal da disciplina. */
	public int getWorkload() {
		return workload;
	}
	
	/* Insere a carga hor�ria semanal na disciplina. */
	public void setWorkload(int workload) {
		this.workload = workload;
	}
	
	/* Retorna uma lista com as disciplinas que pr�-requisitos desta disciplina. */
	public ArrayList<Discipline> getPrerequisites() {
		return prerequisites;
	}
	
	/* Insere as disciplinas que s�o pr�-requisitos desta disciplina na lista. */
	public void setPrerequisite(ArrayList<Discipline> prerequisites) {
		this.prerequisites = prerequisites;
	}
	
	public int getSemester() {
		return semester;
	}
	
	public void setSemester(int semester) {
		if (semester < 0)
			this.semester = 0;
		else
			this.semester = semester;
	}

}
