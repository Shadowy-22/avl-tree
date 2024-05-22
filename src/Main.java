import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    /* Arbol de Busqueda Binario
     * Una estructura de arbol donde cada nodo es mayor que el hijo
     * izquierdo pero menor que su derecho.
     * Complejidad:
     *         - Mejor caso: O(log n)
     *         - Peor caso:  O(n)
     * */

    static Scanner scanner = new Scanner(System.in);

    public static int obtenerEntero() {
        int entero = 0;
        boolean esEntero = false;

        while (!esEntero) {
            try {
                entero = scanner.nextInt();
                esEntero = true;
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número entero.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        }
        scanner.nextLine();
        return entero;
    }

    public static void main(String[] args) {

        AVLTree tree = new AVLTree();
        int navigationKey, data;
        Persona persona;
        int choice = 0;

        System.out.println("PROGRAMA ARBOL BINARIO DE BUSQUEDA");

        while (choice!= 7) {
            System.out.println("\nMenu:");
            System.out.println("1. Insertar Nueva Persona");
            System.out.println("2. Mostrar Arbol (Recorrido Inorden Ascendente)");
            System.out.println("3. Borrar Persona");
            System.out.println("4. Encontrar Minimo");
            System.out.println("5. Encontrar Maximo");
            System.out.println("6. Buscar en el arbol");
            System.out.println("7. Salir");
            System.out.print("Ingrese su eleccion: ");
            choice = obtenerEntero();

            // Validar eleccion
            if (choice < 1 || choice > 7) {
                System.out.println("Eleccion invalida. Por favor ingrese un numero entre 1 y 8.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Ingrese la Clave de la nueva Persona");
                    navigationKey = obtenerEntero();
                    System.out.println("A continuación ingrese los datos de la Persona: ");
                    persona = Persona.ingresarPersona(scanner);
                    tree.insert(new Node(navigationKey, persona));
                    break;
                case 2:
                    tree.display();
                    break;
                case 3:
                    System.out.print("Ingrese la Clave de la Persona a eliminar: ");
                    data = obtenerEntero();
                    tree.remove(data);
                    break;
                case 4:
                    tree.findMinimum();
                    break;
                case 5:
                    tree.findMaximum();
                    break;
                case 6:
                    System.out.print("Ingrese la Clave de la Persona que quiere buscar: ");
                    data = obtenerEntero();
                    if(tree.search(data)){
                        System.out.println("La persona con la Clave " + data + " EXISTE en el arbol");
                    } else {
                        System.out.println("La persona con la Clave " + data + " NO EXISTE en el arbol");
                    };
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Error.");
            }
        }

        scanner.close();
        System.out.println("Programa Finalizado");
    }
}