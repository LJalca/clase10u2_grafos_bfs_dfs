import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Representación de un Grafo mediante Lista de Adyacencia en Java.
 * Incluye algoritmos de recorrido sistemático:
 * - BFS (Breadth-First Search / Búsqueda en Amplitud) con Cola FIFO.
 * - DFS (Depth-First Search / Búsqueda en Profundidad) con Recursión/Pila LIFO.
 */
public class GrafoLista {
    private final int numVertices;
    private final String[] nombresVertices;
    private final List<List<Integer>> listaAdyacencia;

    @SuppressWarnings("unchecked")
    public GrafoLista(String[] nombresVertices) {
        if (nombresVertices == null || nombresVertices.length == 0) {
            throw new IllegalArgumentException("El grafo debe tener al menos un vertice.");
        }
        this.numVertices = nombresVertices.length;
        this.nombresVertices = nombresVertices.clone();
        this.listaAdyacencia = new ArrayList<>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            this.listaAdyacencia.add(new ArrayList<>());
        }
    }

    private void validarVertice(int v) {
        if (v < 0 || v >= numVertices) {
            throw new IndexOutOfBoundsException("Indice de vertice invalido: " + v);
        }
    }

    public void agregarArista(int origen, int destino, boolean bidireccional) {
        validarVertice(origen);
        validarVertice(destino);
        listaAdyacencia.get(origen).add(destino);
        if (bidireccional && origen != destino) {
            listaAdyacencia.get(destino).add(origen);
        }
    }

    public void imprimirGrafo() {
        System.out.println("Lista de Adyacencia del Grafo:");
        for (int i = 0; i < numVertices; i++) {
            System.out.print("[" + i + "] " + nombresVertices[i] + " -> ");
            List<Integer> vecinos = listaAdyacencia.get(i);
            if (vecinos.isEmpty()) {
                System.out.println("(sin conexiones directas)");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int v : vecinos) {
                    sb.append(nombresVertices[v]).append(" (").append(v).append("), ");
                }
                System.out.println(sb.substring(0, sb.length() - 2));
            }
        }
    }

    public List<Integer> recorridoBFS(int inicio) {
        validarVertice(inicio);
        List<Integer> ordenVisita = new ArrayList<>();
        boolean[] visitado = new boolean[numVertices];
        Queue<Integer> cola = new LinkedList<>();

        visitado[inicio] = true;
        cola.add(inicio);

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            ordenVisita.add(actual);

            for (int vecino : listaAdyacencia.get(actual)) {
                if (!visitado[vecino]) {
                    visitado[vecino] = true;
                    cola.add(vecino);
                }
            }
        }
        return ordenVisita;
    }

    public List<Integer> recorridoDFS(int inicio) {
        validarVertice(inicio);
        List<Integer> ordenVisita = new ArrayList<>();
        boolean[] visitado = new boolean[numVertices];
        dfsRecursivo(inicio, visitado, ordenVisita);
        return ordenVisita;
    }

    private void dfsRecursivo(int actual, boolean[] visitado, List<Integer> ordenVisita) {
        visitado[actual] = true;
        ordenVisita.add(actual);

        for (int vecino : listaAdyacencia.get(actual)) {
            if (!visitado[vecino]) {
                dfsRecursivo(vecino, visitado, ordenVisita);
            }
        }
    }

    public String getNombreVertice(int index) {
        validarVertice(index);
        return nombresVertices[index];
    }
}
