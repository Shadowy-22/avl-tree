import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class AVLTree {

    Node root;

    /* Todos los metodos Helper son auxiliares para la funcion deseada y
    hacer mas simple la recursión. */

    // Obtiene la altura
    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    // Actualiza la actura del nodo que se le pasa
    private void updateHeight(Node node) {
        if (node!= null) {
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    // Obtiene el Factor de Balanceo (BF)
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0; // Balance factor of null node is 0
        } else {
            return getHeight(node.left) - getHeight(node.right);
        }
    }

    // Actualizar el Factor de Balanceo (BF) del nodo que se le pasa
    private void updateBalanceFactor(Node node) {
        if (node != null) {
            node.BF = getBalanceFactor(node);
        }
    }

    private Node leftRotate(Node node) {
        Node newRoot = node.right;
        Node leftSubtreeOfNewRoot = newRoot.left;

        // Realizamos la rotacion
        newRoot.left = node;
        node.right = leftSubtreeOfNewRoot;

        // Luego de realizar la rotacion actualizamos la altura y el factor de balanceo de los nodos sobre los que se operó
        updateHeight(node);
        updateHeight(newRoot);
        updateBalanceFactor(node);
        updateBalanceFactor(newRoot);

        return newRoot;
    }

    private Node rightRotate(Node node) {
        Node newRoot = node.left;
        Node rightSubtreeOfNewRoot = newRoot.right;

        // Realizamos la rotacion
        newRoot.right = node;
        node.left = rightSubtreeOfNewRoot;

        // Luego de realizar la rotacion actualizamos la altura y el factor de balanceo de los nodos sobre los que se operó
        updateHeight(node);
        updateHeight(newRoot);
        updateBalanceFactor(node);
        updateBalanceFactor(newRoot);

        return newRoot;
    }

    // Utilizamos este modulo para manejar qué rotacion se realiza dependiendo donde se encuentra el peso del arbol.
    private Node handleRotation(Node node) {
        int balance = getBalanceFactor(node);

        // Subarbol Izquierdo con más Nodos
        if (balance >= 1) {
            if (getBalanceFactor(node.left) >= 0) {
                // Rotacion Simple a la Izquierda (Left-Left Case)
                return rightRotate(node);
            } else {
                // Rotación Doble a la Izquierda (Left-Right Case)
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        // Subarbol Derecho con más Nodos
        if (balance <= -1) {
            if (getBalanceFactor(node.right) <= 0) {
                // Rotacion Simple a la Derecha (Right-Right Case) 
                return leftRotate(node);
            } else {
                // Rotacion Doble a la Derecha (Right-Left Case)
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }
    


    // Método público que se utiliza para la operación de Insertar un elemento
    public void insert(Node newNode){
        if(search(newNode.navigationKey)){
            System.out.println("\nLa persona con el codigo " + newNode.navigationKey + " ya existe, por favor ingrese otra");
        } else {
            root = insertHelper(root, newNode);
            System.out.println("\nElemento " + newNode.navigationKey + " insertado en el arbol");
        }
    }

    private Node insertHelper(Node root, Node newNode){

        // Caso base, encontramos donde está el espacio disponible y cuál es el lugar correcto a insertarlo, que se asignara en el insert()
        if (root == null) {
            root = newNode;
        } else {
            // Se recorre recursivamente el arbol para encontrar el lugar
            // correcto donde insertarlo (Dependiendo si el valor a insertar
            // es menor o mayor al actual nodo).
            if (newNode.navigationKey < root.navigationKey) {
                root.left = insertHelper(root.left, newNode);
            } else if(newNode.navigationKey > root.navigationKey) {
                root.right = insertHelper(root.right, newNode);
            }
        }
        
        // Actualizamos la altura y el factor de balanceo luego de la insercion
        updateHeight(root);
        updateBalanceFactor(root);
        // Y comprobamos si hay que realizar una rotacion o no
        int currentBF = getBalanceFactor(root);
        if (currentBF > 1 || currentBF < -1) {
            root = handleRotation(root);
        }

        return root; // Return unchanged node pointer.
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
            frame.setSize(1280, 720);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        }
    }
    private void displayHelper(Node root){
        // Se recorre en inorden el arbol, de menor a mayor y se muestra en
        // pantalla.
        if(root != null){
            displayHelper(root.left);
            System.out.println(root.navigationKey);
            displayHelper(root.right);
        }
    }

    // Metodo para buscar y mostrar utilizado en main
    public void searchAndDisplay(int navigationKey) {
        Node result = searchHelper(root, navigationKey);
        if (result != null) {
            String message = "La persona con la Clave " + navigationKey + " es:\n" + result.data;
            JOptionPane.showMessageDialog(null, message, "Persona Encontrada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró la persona con la clave " + navigationKey, "Persona No Encontrada", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Método que se utiliza para buscar si el elemento existe antes de realizar la operacion
    private boolean search(int navigationKey) {
        return searchHelper(root, navigationKey) != null;
    }

    private Node searchHelper(Node root, int navigationKey) {
        // Casos bases
        if (root == null) {
            return null;
        } else if (root.navigationKey == navigationKey) {
            return root;
        } else if (navigationKey < root.navigationKey) {
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
        } else if(data < root.navigationKey){
            // Utilizamos esto para recorrer recursivamente el arbol
            // hasta encontrar el nodo
            root.left = removeHelper(root.left, data);
        } else if(data > root.navigationKey){
            root.right = removeHelper(root.right, data);
        } else {
            // Se encontró el nodo
            // Este nodo no tiene hijos, por tanto es una hoja
            if(root.left == null && root.right == null){
                root = null;
            } else if(root.right != null){
                // Existe un hijo derecho, por tanto encontramos un sucesor
                // para reemplazar el nodo.
                root.navigationKey = successor(root);
                root.right = removeHelper(root.right, root.navigationKey);
            } else {
                // Si no existe hijo derecho buscamos un predecesor
                root.navigationKey = predecessor(root);
                root.left = removeHelper(root.left, root.navigationKey);
            }
        }

         // Si solo tenia un unico nodo, retornamos.
        if (root == null) {
            return root;
        }
        

        // Actualizamos la altura y el factor de balanceo luego del borrado
        updateHeight(root);
        updateBalanceFactor(root);
        // Y comprobamos si hay que realizar una rotacion o no
        int currentBF = getBalanceFactor(root);
        if (currentBF > 1 || currentBF < -1) {
            root = handleRotation(root);
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
        return root.navigationKey;
    }

    // Se buscara el mayor valor por debajo del hijo izquierdo
    // de este Nodo Raiz.
    private int predecessor(Node root){
        root = root.left;
        while(root.right != null){
            root = root.right;
        }
        return root.navigationKey;
    }

    public void findMinimum() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aun no existe el arbol", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Node current = root;
            while (current.left != null) {
                current = current.left;
            }
            String message = "La persona con la Clave Mínima es: " + "\nDatos:\n \tClave: " + current.navigationKey + "\n" + current.data;

            // Estilado del Text Area para formatear el texto
            JTextArea textArea = new JTextArea(message);
            textArea.setEditable(false);
            textArea.setColumns(30); // Set the number of columns
            textArea.setRows(10); // Set the number of rows
            textArea.setLineWrap(true); // Enable line wrapping
            textArea.setWrapStyleWord(true); // Wrap at word boundaries
            JScrollPane scrollPane = new JScrollPane(textArea);

            JOptionPane.showMessageDialog(null, scrollPane, "Nodo Mínimo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void findMaximum() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aun no existe el arbol", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Node current = root;
            while (current.right != null) {
                current = current.right;
            }
            String message = "La persona con la Clave Maxima es: " + "\nDatos:\n \tClave: " + current.navigationKey + "\n" + current.data;

            // Estilado del Text Area para formatear el texto
            JTextArea textArea = new JTextArea(message);
            textArea.setEditable(false);
            textArea.setColumns(30); // Set the number of columns
            textArea.setRows(10); // Set the number of rows
            textArea.setLineWrap(true); // Enable line wrapping
            textArea.setWrapStyleWord(true); // Wrap at word boundaries
            JScrollPane scrollPane = new JScrollPane(textArea);

            JOptionPane.showMessageDialog(null, scrollPane, "Nodo Máximo", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private boolean isEmpty(){
        return root == null;
    }
}