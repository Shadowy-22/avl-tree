import javax.swing.JFrame;

public class AVLTree {

    Node root;

    /* Todos los metodos Helper son auxiliares para la funcion deseada y
    hacer mas simple la recursión. */

    // Obtener la altura de un nodo
    private int getHeight(Node node) {
        if (node == null) {
            return 0; // Height of null node is 0
        } else {
            int leftHeight = getHeight(node.left); // Height of left subtree
            int rightHeight = getHeight(node.right); // Height of right subtree
            return Math.max(leftHeight, rightHeight) + 1; // Max height of subtrees + 1 for the current node
        }
    }


    // Obtener el factor de balanceo de un nodo
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return getHeight(N.right) - getHeight(N.left);
    }

    // Método público que se utiliza para la operación de Insertar un elemento
    public void insert(Node newNode, int newKey, Persona personData){
        if(search(newNode.navigationKey)){
            System.out.println("\nLa persona con el codigo " + newNode.navigationKey + " ya existe, por favor ingrese otra");
        } else {
            newNode.navigationKey = newKey;
            newNode.data = personData;
            root = insertHelper(root, newNode);
            System.out.println("\nElemento " + newNode.navigationKey + " insertado en el arbol");
        }
    }

    private Node insertHelper(Node root, Node newNode){

        // Caso base, encontramos donde está el espacio disponible y cuál es el lugar correcto a insertarlo, que se asignara en el insert()
        if(root == null){
            root = newNode;
            return root;
        }
        
        if(newNode.navigationKey < root.navigationKey){
            // Se recorre recursivamente el arbol para encontrar el lugar
            // correcto donde insertarlo (Dependiendo si el valor a insertar
            // es menor o mayor al actual nodo).
            root.left = insertHelper(root.left, newNode);
        } else if(newNode.navigationKey > root.navigationKey) {
            root.right = insertHelper(root.right, newNode);
        } 

        return root;
    }

    // Método público que se utiliza para la operación de Mostrar
    public void display(){
        if (isEmpty()) {
            System.out.println("\nArbol inexistente, ingrese elementos para mostrar");
        } else {
            System.out.println("\nArbol actual con Recorrido Inorden Ascendente:");
            displayHelper(root);
            JFrame frame = new JFrame("Visualizacion del Arbol Actual");
            TreeVisualizer panel = new TreeVisualizer(this);  // Pass 'this' to TreeVisualizer
            frame.add(panel);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        }
    }
    private void displayHelper(Node root){
        // Se recorre en inorden el arbol, de menor a mayor y se muestra en
        // pantalla.
        if(root != null){
            displayHelper(root.left);
            System.out.println(root.data);
            displayHelper(root.right);
        }
    }

    // Método público que se utiliza para la operación de Buscar un elemento
    public boolean search(int navigationKey){
        // Retornamos si se encontró o no.
        return searchHelper(root, navigationKey);
    }
    private boolean searchHelper(Node root, int navigationKey){
        // Casos bases
        if(root == null){
            return false;
        } else if(root.navigationKey == navigationKey) {
            return true;
        } else if (navigationKey < root.navigationKey){
            // Buscamos recursivamente si el nodo es mayor o menor
            return searchHelper(root.left, navigationKey);
        } else {
            return searchHelper(root.right, navigationKey);
        }
    }

    // Método público que se utiliza para la operación de Eliminar un elemento
    public void remove(int data) {
        // Validamos que existe el dato para eliminar.
        if(search(data)){
            removeHelper(root, data);
            System.out.println("\nElemento " + data + " eliminado");
        } else {
            System.out.println("\n" + data + " no se encontró en el arbol");
        }
    }
    private Node removeHelper(Node root, int data){

        // Caso base
        if(root == null){
            return root;
        } else if(data < root.data){
            // Utilizamos esto para recorrer recursivamente el arbol
            // hasta encontrar el nodo
            root.left = removeHelper(root.left, data);
        } else if(data > root.data){
            root.right = removeHelper(root.right, data);
        } else {
            // Se encontró el nodo
            // Este nodo no tiene hijos, por tanto es una hoja
            if(root.left == null && root.right == null){
                root = null;
            } else if(root.right != null){
                // Existe un hijo derecho, por tanto encontramos un sucesor
                // para reemplazar el nodo.
                root.data = successor(root);
                root.right = removeHelper(root.right, root.data);
            } else {
                // Si no existe hijo derecho buscamos un predecesor
                root.data = predecessor(root);
                root.left = removeHelper(root.left, root.data);
            }
        }

        return root;
    }

    // Se buscara el menor valor por debajo del hijo derecho
    // de este Nodo Raiz.
    private int successor(Node root){
        // Auxiliar para recorrer
        root = root.right;
        while(root.left != null){
            root = root.left;
        }
        return root.data;
    }

    // Se buscara el mayor valor por debajo del hijo izquierdo
    // de este Nodo Raiz.
    private int predecessor(Node root){
        root = root.left;
        while(root.right != null){
            root = root.right;
        }
        return root.data;
    }

    public void findMinimum() {     
        if(isEmpty()){
            System.out.println("Aun no existe el arbol");
        } else {
            // Auxiliar para recorrer
            Node current = root;
            while(current.left != null){
                current = current.left;
            }
            System.out.println("\nEl nodo mínimo tiene el valor de: " + current.data);
        }
    }

    public void findMaximum() {
        if(isEmpty()){
            System.out.println("\nAun no existe el arbol");
        } else {
            Node current = root;
            while(current.right != null){
                current = current.right;
            }
        System.out.println("\nEl nodo máximo tiene el valor de: " + current.data);
        }
    }

    private boolean isEmpty(){
        return root == null;
    }
}