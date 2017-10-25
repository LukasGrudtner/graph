package control;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import discipline.Discipline;
import discipline.DisciplinesRemaining;
import graph.DirectedGraph;
import graph.Graph;
import vertex.DGVertex;
import vertex.Vertex;

public class Control {
	
	private final static int MAX_WORKLOAD = 30;
	private static Graph graph;

	public static void main(String[] args) {
		createGraph(new DisciplinesRemaining().getDisciplinesRemainingSet());
		ArrayList<Vertex> vertices = ((DirectedGraph) graph).topologicalSort();
		ArrayList<Discipline> disciplinePlan = convertVerticesListToDisciplinesList(vertices);
		
		ArrayList<ArrayList<Discipline>> semesters = calculatesSemesters(disciplinePlan);
		
//		printSemesters(semesters);
	}
	
	public static void createGraph(HashSet<Object> objectSet) {
		/* Inicizaliza o grafo como um grafo orientado. */
		graph = new DirectedGraph();
		initilizeVertices(objectSet);
		connectVertices();
	}
	
	private static void initilizeVertices(HashSet<Object> objectSet) {
		Iterator<Object> iterator = objectSet.iterator();
		Vertex vertex;
		Object object;
		
		while (iterator.hasNext()) {
			object = iterator.next();
			vertex = new DGVertex(object);
			graph.addVertex(vertex);
		}
	}
	
	/* Faz a conexão dos vértices baseando-se nos pré-requisitos de cada disciplina. */
	private static void connectVertices() {
		Iterator<Vertex> iterator = graph.vertices().iterator();
		Vertex v1;
		Discipline disciplineV1;
		
		/* Para cada vértice do grafo: */
		while (iterator.hasNext()) {
			v1 = iterator.next();
			disciplineV1 = (Discipline) v1.getData();
			
			/* Se a disciplina v1 tem pré-requisitos: */
			if (disciplineV1.getPrerequisites() != null) {
				
				/* Para cada disciplina pré-requisito da disciplina de v1: */
				for (int i = 0; i < disciplineV1.getPrerequisites().size(); i++) {
					Iterator<Vertex> iterator2 = graph.vertices().iterator();
					Vertex v2;
					Discipline prerequisiteV1, discipline;
					prerequisiteV1 = disciplineV1.getPrerequisites().get(i);
					
					/* Encontra o vértice que possui aquela disciplina. */
					while (iterator2.hasNext()) {
						v2 = iterator2.next();
						discipline = (Discipline) v2.getData();
						
						if (prerequisiteV1.equals(discipline))
							graph.connect(v2, v1);
					}
				}
				
			}
				
		}
	}
	
	private static ArrayList<Discipline> convertVerticesListToDisciplinesList(ArrayList<Vertex> vertices) {
		ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
		
		for (int i = 0; i < vertices.size(); i++) {
			disciplines.add((Discipline) vertices.get(i).getData());
		}
		
		return disciplines;
	}
	
	private static ArrayList<ArrayList<Discipline>> calculatesSemesters(ArrayList<Discipline> disciplinePlan) {
		ArrayList<ArrayList<Discipline>> semesters = new ArrayList<ArrayList<Discipline>>();
		
		int workload = 0;
		int i = 0;
		while (i < disciplinePlan.size()) {
			ArrayList<Discipline> semester = new ArrayList<Discipline>();
			while (workload < MAX_WORKLOAD && i < disciplinePlan.size()) {
				workload += disciplinePlan.get(i).getWorkload();
				if (workload > MAX_WORKLOAD) {
					workload = 0;
					break;
				} else {
					semester.add(disciplinePlan.get(i));
					i++;
				}
			}
			semesters.add(semester);
		}
		return semesters;
	}
	
//	private void printSemesters(ArrayList<ArrayList<Discipline>> semesters) {
//		for (int i = 0; i < semesters.size(); i++) {
//			for (int j = 0; j < semesters.get(i).)
//		}
//	}
}
