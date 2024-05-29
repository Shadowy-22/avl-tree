public class Node {

    int navigationKey;
    Persona data;
    Node left, right;
    int height;
    int BF; // Balance Factor (Factor de Equilibrio)

    public Node(int navigationKey, Persona data) {
        this.navigationKey = navigationKey;
        this.data = data;
        this.height = 0; // Consideramos que la altura de un nuevo nodo hoja es inicialmente 0.
        this.BF = 0; // Consideramos que la altura de un nuevo nodo hoja es inicialmente 0.
    }
}
