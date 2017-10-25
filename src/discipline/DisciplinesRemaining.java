package discipline;

import java.util.ArrayList;
import java.util.HashSet;

public class DisciplinesRemaining {
	
	private HashSet<Object> disciplinesRemaining;
	
	public DisciplinesRemaining() {
		
		disciplinesRemaining = new HashSet<Object>();
		insertDisciplines();
	}
	
	private void insertDisciplines() {
		Discipline ine5405 = new Discipline("INE5405", "Probabilidade e Estatística", 5, null);
		disciplinesRemaining.add(ine5405);
		
		Discipline ine5409 = new Discipline("INE5409", "Cálculo Numérico para Computação", 4, null);
		disciplinesRemaining.add(ine5409);
		
		Discipline ine5418 = new Discipline("INE5418", "Computação Distribuída", 4, null);
		disciplinesRemaining.add(ine5418);
		
		Discipline ine5420 = new Discipline("INE5420", "Computação Gráfica", 4, null);
		disciplinesRemaining.add(ine5420);
		
		Discipline ine5421 = new Discipline("INE5421", "Linguagens Formais e Compiladores", 4, null);
		disciplinesRemaining.add(ine5421);
		
		Discipline ine5422 = new Discipline("INE5422", "Redes de Computadores II", 4, null);
		disciplinesRemaining.add(ine5422);
		
		Discipline ine5423 = new Discipline("INE5423", "Bando de Dados I", 4, null);
		disciplinesRemaining.add(ine5423);
		
		Discipline ine5424 = new Discipline("INE5424", "Sistemas Operacionais II", 4, null);
		disciplinesRemaining.add(ine5424);
		
		ArrayList<Discipline> prereqINE5425 = new ArrayList<Discipline>();
		prereqINE5425.add(ine5405);
		Discipline ine5425 = new Discipline("INE5425", "Modelagem e Simulação", 4, prereqINE5425);
		disciplinesRemaining.add(ine5425);
		
		ArrayList<Discipline> prereqINE5426 = new ArrayList<Discipline>();
		prereqINE5426.add(ine5421);
		Discipline ine5426 = new Discipline("INE5426", "Construção de Compiladores", 4, prereqINE5426);
		disciplinesRemaining.add(ine5426);
		
		Discipline ine5427 = new Discipline("INE5427", "Planejamento e Gestão de Projetos", 4, null);
		disciplinesRemaining.add(ine5427);
		
		ArrayList<Discipline> prereqINE5430 = new ArrayList<Discipline>();
		prereqINE5430.add(ine5405);
		Discipline ine5430 = new Discipline("INE5430", "Inteligência Artificial", 4, prereqINE5430);
		disciplinesRemaining.add(ine5430);
		
		Discipline ine5453 = new Discipline("INE5453", "Introdução ao Trabalho de Conclusão de Curso", 1, null);
		disciplinesRemaining.add(ine5453);
		
		Discipline ine5429 = new Discipline("INE5429", "Segurança em Computação", 4, null);
		disciplinesRemaining.add(ine5429);
		
		Discipline ine5431 = new Discipline("INE5431", "Sistemas Multimídia", 4, null);
		disciplinesRemaining.add(ine5431);
		
		ArrayList<Discipline> prereqINE5432 = new ArrayList<Discipline>();
		prereqINE5432.add(ine5423);
		Discipline ine5432 = new Discipline("INE5432", "Banco de Dados II", 4, prereqINE5432);
		disciplinesRemaining.add(ine5432);
		
		ArrayList<Discipline> prereqINE5433 = new ArrayList<Discipline>();
		prereqINE5433.add(ine5427);
		prereqINE5433.add(ine5453);
		Discipline ine5433 = new Discipline("INE5433", "Trabalho de Conclusão de Curso I (TCC)", 6, prereqINE5433);
		disciplinesRemaining.add(ine5433);
		
		ArrayList<Discipline> prereqINE5434 = new ArrayList<Discipline>();
		prereqINE5434.add(ine5433);
		Discipline ine5434 = new Discipline("INE5434", "Trabalho de Conclusão de Curso II (TCC)", 9, prereqINE5434);
		disciplinesRemaining.add(ine5434);
	}
	
	public HashSet<Object> getDisciplinesRemainingSet() {
		return disciplinesRemaining;
	}

}
