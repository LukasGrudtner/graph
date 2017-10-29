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
	
	private final static int MAX_WORKLOAD = 30;		/* Carga hor�ria semanal m�xima definida para o curso. 	*/
	private static Graph graph;						/* Grafo utilizado para a aplica��o. 					*/

	
	public static void main(String[] args) {
		createGraph(new DisciplinesNotTaken().getDisciplinesNotTakenSet());
		ArrayList<Vertex> vertices = ((DirectedGraph) graph).topologicalSort();					/* Lista com a ordena��o topol�gica de v�rtices do grafo. */
		ArrayList<Semester> semesters = distributeDisciplinesInSubsequentSemesters(vertices);	/* Lista de semestres com suas respectivas disciplinas. */
		
		print(semesters);
	}
	
	/* Inicializa o grafo como um grafo orientado. */
	public static void createGraph(HashSet<Object> objectSet) {
		graph = new DirectedGraph();
		initializeVertices(objectSet);
	}
	
	/* Inicializa os v�rtices do grafo, cada um representando uma disciplina. */
	private static void initializeVertices(HashSet<Object> objectSet) {
		HashSet<Object> markedObjects = new HashSet<Object>();
		Iterator<Object> iterator = objectSet.iterator();
		Object object;
		
		while (iterator.hasNext()) {
			object = iterator.next();
			addAndConnectVertices(object, null, markedObjects);
		}
	}
	
	/* Fun��o auxiliar para inicializar os v�rtices, adicion�-los no grafo e criar suas conex�es. */
	private static void addAndConnectVertices(Object prerequisiteDiscipline, Vertex vertexPredecessor, HashSet<Object> markedDisciplines) {
		Vertex vertex = null;
		
		/* Se a disciplina n�o foi marcada, marque-a. Crie um v�rtice com a disciplina e o adicione ao grafo. */
		if (!markedDisciplines.contains(prerequisiteDiscipline)) {

			vertex = new DirectedGraphVertex(prerequisiteDiscipline);
			graph.addVertex(vertex);
			markedDisciplines.add(prerequisiteDiscipline);
			
			/* Itera sobre seus pr�-requisitos. Para cada um, chame addVertex com vertex sendo o v�rtice predecessor.*/
			if (((Discipline) prerequisiteDiscipline).getPrerequisites() != null) {
				Iterator<Discipline> iterator = ((Discipline) prerequisiteDiscipline).getPrerequisites().iterator();
					
				Discipline discipline;
				while (iterator.hasNext()) {
					discipline = iterator.next();
					addAndConnectVertices(discipline, vertex, markedDisciplines);
				}
			}
			
			/* Ao retornar da recurs�o, fa�a a conex�o dos v�rtices. Caso seja um v�rtice fonte, n�o fa�a a conex�o, pois este n�o possui predecessor. */
			if (vertexPredecessor != null) {

				/* 
				 * A ordem de conex�o � alterada pois come�amos com as disciplinas que possuem pr�-requisitos, por�m, no grafo, os v�rtices fontes
				 * ser�o as disciplinas sem pr�-requisitos.
				 */
				graph.connect(vertex, vertexPredecessor);
			}
		
		/* 
		 * Se encontrar uma disciplina marcada, n�o temos a refer�ncia do seu v�rtice. Logo, chame a fun��o getVertex, que recebe
		 * a disciplina como par�metro e devolve o seu respectivo v�rtice. Ent�o, fa�a a conex�o entre eles.
		 */
		} else {
			vertex = getVertex(prerequisiteDiscipline);
			if (vertex != null && vertexPredecessor != null) {
				graph.connect(vertex, vertexPredecessor);

			}
		}
	}
	
	/* Fun��o auxiliar que retorna o v�rtice que possui o objeto informado por par�metro. */
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
	
	/* Organiza todas as disciplinas em uma lista de semestres ordenados, respeitando as condi��es de pr�-requisitos. */
	private static ArrayList<Semester> distributeDisciplinesInSubsequentSemesters(ArrayList<Vertex> topologicalSort) {
		ArrayList<Semester> semesters = new ArrayList<Semester>();
		
		/* Adiciona o semestre inicial e a primeira disciplina da ordena��o topol�gica. */
		Semester semester = new Semester();
		semester.addDiscipline((Discipline) topologicalSort.get(0).getData());
		semester.incWorkload(((Discipline) topologicalSort.get(0).getData()).getWorkload());
		semesters.add(semester);
		
		int i = 1;
		
		/* Para cada v�rtice da lista de ordena��o top�logica: */
		while (i < topologicalSort.size()) {
			
			Discipline discipline = (Discipline) topologicalSort.get(i).getData();
			int higherIndex = 0;
			
			/* Se a disciplina possui pr�-requisitos, pegue o maior �ndice (semestre) deles. */
			if (discipline.getPrerequisites() != null) {
				
				Iterator<Discipline> iterator = discipline.getPrerequisites().iterator();
				Discipline prerequisite;
				
				/* Varre todos os pr�-requisitos e verifica qual o semestre mais avan�ado em que uma disciplina pr�-requisito se encontra. */
				while (iterator.hasNext()) {
					prerequisite = iterator.next();
					if (prerequisite.getSemester() > higherIndex) {
						higherIndex = prerequisite.getSemester();
					}
				}
				higherIndex++;
			}
			
			/* Percorremos a lista de semestres a partir do maior �ndice obtido anteriormente. */
			boolean disciplineWasAdded = false;
			for (int j = higherIndex; j < semesters.size(); j++) {
				
				/* Se encontrarmos um semestre que possua espa�o de workload, adicione. */
				if ((semesters.get(j).getWorkload() + discipline.getWorkload() <= MAX_WORKLOAD)) {
					semesters.get(j).addDiscipline(discipline);
					semesters.get(j).incWorkload(discipline.getWorkload());
					discipline.setSemester(j);
					disciplineWasAdded = true;
					break;
				}
			}
			
			/* Se a disciplina n�o p�de ser adicionada em nenhum semestre, crie um novo e adicione-o. */
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
	
	/* Fun��o auxiliar para imprimir na tela a lista de semestres com suas disciplinas. */
	private static void print(ArrayList<Semester> semesters) {
		for (int i = 0; i < semesters.size(); i++) {
			System.out.println("\n### SEMESTRE " + (i+1) + " ### " + (semesters.get(i)).getWorkload() + " cr�ditos");
			
			Iterator<Discipline> iterator = ((Semester) semesters.get(i)).getDisciplines().iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next().getName());
			}
		}
	}
}
