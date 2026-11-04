import java.util.List;

/**
 * Demostración docente de Lista de Adyacencia y recorridos BFS / DFS.
 */
public class Main {
    public static void main(String[] args) {

        String[] ciudades = {
            "Calceta",      // 0
            "Chone",        // 1
            "Tosagua",      // 2
            "Portoviejo",   // 3
            "Manta"         // 4
        };

        GrafoLista redManabi = new GrafoLista(ciudades);

        // Rutas directas viales
        redManabi.agregarArista(0, 1, true); // Calceta <-> Chone
        redManabi.agregarArista(0, 2, true); // Calceta <-> Tosagua
        redManabi.agregarArista(1, 3, true); // Chone <-> Portoviejo
        redManabi.agregarArista(2, 3, true); // Tosagua <-> Portoviejo
        redManabi.agregarArista(3, 4, true); // Portoviejo <-> Manta

        redManabi.imprimirGrafo();


         // Recorrido en Amplitud (BFS) desde Calceta (0)
        List<Integer> visitasBFS = redManabi.recorridoBFS(0);
        System.out.print("\nRecorrido BFS (Amplitud por Niveles desde Calceta): ");
        for (int i = 0; i < visitasBFS.size(); i++) {
            System.out.print(redManabi.getNombreVertice(visitasBFS.get(i)) + (i < visitasBFS.size() - 1 ? " -> " : ""));
        }
        System.out.println();

           // Recorrido en Profundidad (DFS) desde Calceta (0)
        List<Integer> visitasDFS = redManabi.recorridoDFS(0);
        System.out.print("Recorrido DFS (Profundidad por Ramas desde Calceta): ");
        for (int i = 0; i < visitasDFS.size(); i++) {
            System.out.print(redManabi.getNombreVertice(visitasDFS.get(i)) + (i < visitasDFS.size() - 1 ? " -> " : ""));
        }
        System.out.println();

       
    }
}
