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
	
	private final static int MAX_WORKLOAD = 30;		/* Carga horária semanal máxima definida para o curso. 	*/
	private static Graph graph;						/* Grafo utilizado para a aplicação. 					*/

	
	public static void main(String[] args) {
		createGraph(new DisciplinesRemaining().getDisciplinesRemainingSet());
		ArrayList<Vertex> vertices = ((DirectedGraph) graph).topologicalSort();
		ArrayList<Discipline> disciplinePlan = convertVerticesListToDisciplinesList(vertices);
		
		ArrayList<ArrayList<Discipline>> semesters = calculatesSemesters(disciplinePlan);
		
		for (int i = 0; i < disciplinePlan.size(); i++) {
			System.out.println(disciplinePlan.get(i).getName());
		}
	}
	
	/* Inicializa o grafo como um grafo orientado, e inicializa os vértices. */
	public static void createGraph(HashSet<Object> objectSet) {
		graph = new DirectedGraph();
		initilizeVertices(objectSet);
	}
	
	/* Chama a função auxiliar AddVertex somente para as disciplinas que possuem pré-requisitos. */
	private static void initilizeVertices(HashSet<Object> objectSet) {
		HashSet<Object> markedObjects = new HashSet<Object>();
		Iterator<Object> iterator = objectSet.iterator();
		Object object;
		
		while (iterator.hasNext()) {
			object = iterator.next();
			
			/* Chama a função addVertex somente para disciplinas que possuem pré-requisitos.*/
			if (((Discipline) object).getPrerequisites() != null)
				addVertex(object, null, markedObjects);
		}
	}
	
	/* Função auxiliar para inicializar os vértices, adicioná-los no grafo e criar suas conexões. */
	private static void addVertex(Object prerequisiteDiscipline, Vertex vertexPredecessor, HashSet<Object> markedDisciplines) {
		Vertex vertex = null;
		
		/* Se a disciplina não foi marcada, marque-a, crie um vértice com a disciplina e o adicione ao grafo. */
		if (!markedDisciplines.contains(prerequisiteDiscipline)) {
			vertex = new DGVertex(prerequisiteDiscipline);
			graph.addVertex(vertex);
			markedDisciplines.add(prerequisiteDiscipline);
		
			/* Itera sobre seus pré-requisitos, e para cada um, chame addVertex com vertex sendo o vértice pai.*/
			if (((Discipline) prerequisiteDiscipline).getPrerequisites() != null) {
				Iterator<Discipline> iterator = ((Discipline) prerequisiteDiscipline).getPrerequisites().iterator();
					
				Discipline discipline;
				while (iterator.hasNext()) {
					discipline = iterator.next();
					addVertex(discipline, vertex, markedDisciplines);
				}
			}
			
			/* Ao retornar da recursão, faça a conexão dos vértices. Caso seja um vértice fonte, não faça a conexão, pois este não possui predecessor. */
			if (vertexPredecessor != null) {
				/* A ordem de conexão é alterada pois começamos com as disciplinas que possuem pré-requisitos, porém, no grafo, os vértices fontes
				 * serão as disciplinas sem pré-requisitos.
				 */
				graph.connect(vertex, vertexPredecessor);
				System.out.println("Connect: " + ((Discipline) vertexPredecessor.getData()).getName() + " with " + ((Discipline) vertex.getData()).getName());
			}
		
		/* Se encontrar uma disciplina marcada, não temos a referência do seu vértice. Logo, chame a função getVertex, que recebe
		 * a disciplina como parâmetro, e devolve o seu respectivo vértice. Então, faça a conexão entre eles.
		 */
		} else {
			vertex = getVertex(prerequisiteDiscipline);
			if (vertex != null && vertexPredecessor != null) {
				graph.connect(vertex, vertexPredecessor);
				System.out.println("Connect: " + ((Discipline) vertexPredecessor.getData()).getName() + " with " + ((Discipline) vertex.getData()).getName());

			}
		}
	}
	
	//man-in-the-middle, reply
	
	/* Função auxiliar, retorna o vértice que possui o objeto informado por parâmetro. */
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
	
	/* Recebe uma lista de vértices ordenados topológicamente e retorna uma lista com suas respectivas disciplinas. */
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
