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
		
		for (int i = 0; i < disciplinePlan.size(); i++) {
			System.out.println(disciplinePlan.get(i).getName());
		}
	}
	
	public static void createGraph(HashSet<Object> objectSet) {
		/* Inicizaliza o grafo como um grafo orientado. */
		graph = new DirectedGraph();
		initilizeVertices(objectSet);
	}
	
	private static void initilizeVertices(HashSet<Object> objectSet) {
		HashSet<Object> markedObjects = new HashSet<Object>();
		Iterator<Object> iterator = objectSet.iterator();
		Object object;
		
		while (iterator.hasNext()) {
			object = iterator.next();
			
			/* Chama a fun��o addVertex somente para disciplinas que possuem pr�-requisitos.*/
			if (((Discipline) object).getPrerequisites() != null)
				addVertex(object, null, markedObjects);
		}
	}
	
	private static void addVertex(Object prerequisiteDiscipline, Vertex vertexPredecessor, HashSet<Object> markedDisciplines) {
		Vertex vertex = null;
		
		/* Se a disciplina n�o foi marcada, marque-a, crie um v�rtice com a disciplina e o adicione ao grafo. */
		if (!markedDisciplines.contains(prerequisiteDiscipline)) {
			vertex = new DGVertex(prerequisiteDiscipline);
			graph.addVertex(vertex);
			markedDisciplines.add(prerequisiteDiscipline);
		
			/* Itera sobre seus pr�-requisitos, e para cada um, chame addVertex com vertex sendo o v�rtice pai.*/
			if (((Discipline) prerequisiteDiscipline).getPrerequisites() != null) {
				Iterator<Discipline> iterator = ((Discipline) prerequisiteDiscipline).getPrerequisites().iterator();
					
				Discipline discipline;
				while (iterator.hasNext()) {
					discipline = iterator.next();
					addVertex(discipline, vertex, markedDisciplines);
				}
			}
			
			/* Ao retornar da recurs�o, fa�a a conex�o dos v�rtices. Caso seja um v�rtice fonte, n�o fa�a a conex�o, pois este n�o possui predecessor. */
			if (vertexPredecessor != null) {
				/* A ordem de conex�o � alterada pois come�amos com as disciplinas que possuem pr�-requisitos, por�m, no grafo, os v�rtices fontes
				 * ser�o as disciplinas sem pr�-requisitos.
				 */
				graph.connect(vertex, vertexPredecessor);
				System.out.println("Connect: " + ((Discipline) vertexPredecessor.getData()).getName() + " with " + ((Discipline) vertex.getData()).getName());
			}
		
		/* Se encontrar uma disciplina marcada, n�o temos a refer�ncia do seu v�rtice. Logo, chame a fun��o getVertex, que recebe
		 * a disciplina como par�metro, e devolve o seu respectivo v�rtice. Ent�o, fa�a a conex�o entre eles.
		 */
		} else {
			vertex = getVertex(prerequisiteDiscipline);
			if (vertex != null && vertexPredecessor != null) {
				graph.connect(vertex, vertexPredecessor);
				System.out.println("Connect: " + ((Discipline) vertexPredecessor.getData()).getName() + " with " + ((Discipline) vertex.getData()).getName());

			}
		}
	}
	
	private static Vertex getVertex(Object object) {
		Iterator<Vertex> iterator = graph.vertices().iterator();
		Vertex v;
		Discipline auxObject;
		
		while (iterator.hasNext()) {
			v = iterator.next();
			auxObject = (Discipline) v.getData();
			
			if (auxObject.equals(object))
				return v;
		}
		
		return null;
	}
	
	/* Faz a conex�o dos v�rtices baseando-se nos pr�-requisitos de cada disciplina. */
	private static void connectVertices() {
		Iterator<Vertex> iterator = graph.vertices().iterator();
		Vertex v1;
		Discipline disciplineV1;
		
		/* Para cada v�rtice do grafo: */
		while (iterator.hasNext()) {
			v1 = iterator.next();
			disciplineV1 = (Discipline) v1.getData();
			
			/* Se a disciplina v1 tem pr�-requisitos: */
			if (disciplineV1.getPrerequisites() != null) {
				
				/* Para cada disciplina pr�-requisito da disciplina de v1: */
				for (int i = 0; i < disciplineV1.getPrerequisites().size(); i++) {
					Iterator<Vertex> iterator2 = graph.vertices().iterator();
					Vertex v2;
					Discipline prerequisiteV1, discipline;
					prerequisiteV1 = disciplineV1.getPrerequisites().get(i);
					
					/* Encontra o v�rtice que possui aquela disciplina. */
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
}
