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
    private static boolean testDataInserted = false;

    // Method for inserting test data into the tree
    
    private static void insertTestData(AVLTree tree) {
         // Insertar datos de prueba
         Persona[] personas = {
            new Persona("12345678A", "John", "Doe", "123 Main St", "555-1234"),
            new Persona("87654321B", "Jane", "Smith", "456 Elm St", "555-5678"),
            new Persona("12349876C", "Alice", "Johnson", "789 Oak St", "555-6789"),
            new Persona("87651234D", "Bob", "Brown", "101 Pine St", "555-2345"),
            new Persona("12983465E", "Charlie", "Davis", "102 Maple St", "555-3456"),
            new Persona("89765432F", "Diana", "Clark", "103 Birch St", "555-4567"),
            new Persona("23456789G", "Edward", "Wilson", "104 Cedar St", "555-5678"),
            new Persona("34567890H", "Fiona", "Taylor", "105 Walnut St", "555-6789"),
            new Persona("45678901I", "George", "Anderson", "106 Chestnut St", "555-7890"),
            new Persona("56789012J", "Hannah", "Moore", "107 Hickory St", "555-8901"),
            new Persona("67890123K", "Ivy", "Martinez", "108 Spruce St", "555-9012"),
            new Persona("78901234L", "Jack", "Hernandez", "109 Redwood St", "555-0123"),
            new Persona("89012345M", "Karen", "Lopez", "110 Dogwood St", "555-1235"),
            new Persona("90123456N", "Liam", "Gonzalez", "111 Fir St", "555-2346"),
            new Persona("01234567O", "Mia", "Perez", "112 Palm St", "555-3457"),
            new Persona("23456789P", "Noah", "Morris", "113 Magnolia St", "555-4568"),
            new Persona("34567890Q", "Olivia", "Hall", "114 Sequoia St", "555-5679")
        };

        // Insert the nodes into the tree
        for (int i = 0; i < personas.length; i++) {
            tree.insert(new Node(i + 1, personas[i]));
        }
    }

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
            System.out.println("0. Ingresar Datos de Prueba (Sólo 1 vez)");
            System.out.println("1. Insertar Nueva Persona (Datos Manuales)");
            System.out.println("2. Mostrar Arbol (Recorrido Inorden Ascendente)");
            System.out.println("3. Borrar Persona");
            System.out.println("4. Encontrar Minimo");
            System.out.println("5. Encontrar Maximo");
            System.out.println("6. Buscar en el arbol");
            System.out.println("7. Salir");
            System.out.println("0. Insertar datos de prueba");
            System.out.print("Ingrese su eleccion: ");
            choice = obtenerEntero();

            // Validar eleccion
            if (choice < 0 || choice > 7) {
                System.out.println("Eleccion invalida. Por favor ingrese un numero entre 0 y 7.");
                continue;
            }

            switch (choice) {
                case 0: 
                    // Se ingresan datos de prueba
                    if (!testDataInserted) {
                        insertTestData(tree);
                        testDataInserted = true;
                    } else {
                        System.out.println("Los datos de prueba ya se han insertado.");
                    }
                    break;
                case 1:
                    // Se ingresan datos manualmente
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
                    tree.searchAndDisplay(data);
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                case 99:

                default:
                    System.out.println("Error.");
            }
        }

        scanner.close();
        System.out.println("Programa Finalizado");
    }
}