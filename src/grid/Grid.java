package grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import graph.DirectedGraph;
import graph.Graph;
import model.Discipline;
import model.DisciplinesNotTaken;
import model.Semester;
import vertex.DirectedGraphVertex;
import vertex.Vertex;

public class Grid {
	
	private final static int MAX_WORKLOAD = 30;		/* Carga horária semanal máxima definida para o curso. 	*/
	private static Graph graph;						/* Grafo utilizado para a aplicação. 					*/

	
	public static void main(String[] args) {
		createGraph(new DisciplinesNotTaken().getDisciplinesNotTakenSet());
		ArrayList<Vertex> vertices = ((DirectedGraph) graph).topologicalSort();					/* Lista com a ordenação topológica de vértices do grafo. */
		ArrayList<Semester> semesters = distributeDisciplinesInSubsequentSemesters(vertices);	/* Lista de semestres com suas respectivas disciplinas. */
		
		print(semesters);
	}
	
	/* Inicializa o grafo como um grafo orientado. */
	public static void createGraph(HashSet<Object> objectSet) {
		graph = new DirectedGraph();
		initializeVertices(objectSet);
	}
	
	/* Inicializa os vértices do grafo, cada um representando uma disciplina. */
	private static void initializeVertices(HashSet<Object> objectSet) {
		HashSet<Object> markedObjects = new HashSet<Object>();
		Iterator<Object> iterator = objectSet.iterator();
		Object object;
		
		while (iterator.hasNext()) {
			object = iterator.next();
			addAndConnectVertices(object, null, markedObjects);
		}
	}
	
	/* Função auxiliar para inicializar os vértices, adicioná-los no grafo e criar suas conexões. */
	private static void addAndConnectVertices(Object prerequisiteDiscipline, Vertex vertexPredecessor, HashSet<Object> markedDisciplines) {
		Vertex vertex = null;
		
		/* Se a disciplina não foi marcada, marque-a. Crie um vértice com a disciplina e o adicione ao grafo. */
		if (!markedDisciplines.contains(prerequisiteDiscipline)) {

			vertex = new DirectedGraphVertex(prerequisiteDiscipline);
			graph.addVertex(vertex);
			markedDisciplines.add(prerequisiteDiscipline);
			
			/* Itera sobre seus pré-requisitos. Para cada um, chame addVertex com vertex sendo o vértice predecessor.*/
			if (((Discipline) prerequisiteDiscipline).getPrerequisites() != null) {
				Iterator<Discipline> iterator = ((Discipline) prerequisiteDiscipline).getPrerequisites().iterator();
					
				Discipline discipline;
				while (iterator.hasNext()) {
					discipline = iterator.next();
					addAndConnectVertices(discipline, vertex, markedDisciplines);
				}
			}
			
			/* Ao retornar da recursão, faça a conexão dos vértices. Caso seja um vértice fonte, não faça a conexão, pois este não possui predecessor. */
			if (vertexPredecessor != null) {

				/* 
				 * A ordem de conexão é alterada pois começamos com as disciplinas que possuem pré-requisitos, porém, no grafo, os vértices fontes
				 * serão as disciplinas sem pré-requisitos.
				 */
				graph.connect(vertex, vertexPredecessor);
			}
		
		/* 
		 * Se encontrar uma disciplina marcada, não temos a referência do seu vértice. Logo, chame a função getVertex, que recebe
		 * a disciplina como parâmetro e devolve o seu respectivo vértice. Então, faça a conexão entre eles.
		 */
		} else {
			vertex = getVertex(prerequisiteDiscipline);
			if (vertex != null && vertexPredecessor != null) {
				graph.connect(vertex, vertexPredecessor);

			}
		}
	}
	
	/* Função auxiliar que retorna o vértice que possui o objeto informado por parâmetro. */
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
	
	/* Organiza todas as disciplinas em uma lista de semestres ordenados, respeitando as condições de pré-requisitos. */
	private static ArrayList<Semester> distributeDisciplinesInSubsequentSemesters(ArrayList<Vertex> topologicalSort) {
		ArrayList<Semester> semesters = new ArrayList<Semester>();
		
		/* Adiciona o semestre inicial e a primeira disciplina da ordenação topológica. */
		Semester semester = new Semester();
		semester.addDiscipline((Discipline) topologicalSort.get(0).getData());
		semester.incWorkload(((Discipline) topologicalSort.get(0).getData()).getWorkload());
		semesters.add(semester);
		
		int i = 1;
		
		/* Para cada vértice da lista de ordenação topólogica: */
		while (i < topologicalSort.size()) {
			
			Discipline discipline = (Discipline) topologicalSort.get(i).getData();
			int higherIndex = 0;
			
			/* Se a disciplina possui pré-requisitos, pegue o maior índice (semestre) deles. */
			if (discipline.getPrerequisites() != null) {
				
				Iterator<Discipline> iterator = discipline.getPrerequisites().iterator();
				Discipline prerequisite;
				
				/* Varre todos os pré-requisitos e verifica qual o semestre mais avançado em que uma disciplina pré-requisito se encontra. */
				while (iterator.hasNext()) {
					prerequisite = iterator.next();
					if (prerequisite.getSemester() > higherIndex) {
						higherIndex = prerequisite.getSemester();
					}
				}
				higherIndex++;
			}
			
			/* Percorremos a lista de semestres a partir do maior índice obtido anteriormente. */
			boolean disciplineWasAdded = false;
			for (int j = higherIndex; j < semesters.size(); j++) {
				
				/* Se encontrarmos um semestre que possua espaço de workload, adicione. */
				if ((semesters.get(j).getWorkload() + discipline.getWorkload() <= MAX_WORKLOAD)) {
					semesters.get(j).addDiscipline(discipline);
					semesters.get(j).incWorkload(discipline.getWorkload());
					discipline.setSemester(j);
					disciplineWasAdded = true;
					break;
				}
			}
			
			/* Se a disciplina não pôde ser adicionada em nenhum semestre, crie um novo e adicione-o. */
			if (!disciplineWasAdded) {
				
				Semester lastSemester = new Semester();
				lastSemester.addDiscipline(discipline);
				lastSemester.incWorkload(discipline.getWorkload());
				semesters.add(lastSemester);
				discipline.setSemester(semesters.size()-1);
			}
			
			i++;
		}
		return semesters;
	}
	
	/* Função auxiliar para imprimir na tela a lista de semestres com suas disciplinas. */
	private static void print(ArrayList<Semester> semesters) {
		for (int i = 0; i < semesters.size(); i++) {
			System.out.println("\n### SEMESTRE " + (i+1) + " ### " + (semesters.get(i)).getWorkload() + " créditos");
			
			Iterator<Discipline> iterator = ((Semester) semesters.get(i)).getDisciplines().iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next().getName());
			}
		}
	}
}
