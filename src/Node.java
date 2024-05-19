public class Node {

    int navigationKey;
    Persona data;
    Node left, right;
    int height;

    public Node(int navigationKey, Persona data) {
        this.navigationKey = navigationKey;
        this.data = data;
        this.height = 0; // La altura de un nuevo nodo hoja es inicialmente 0.
    }
}
