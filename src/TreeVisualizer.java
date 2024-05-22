import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class TreeVisualizer extends JPanel {

    private AVLTree tree;
    private Map<Rectangle, Node> nodeLocations; // To store node locations and corresponding nodes
    private static final int NODE_DIAMETER = 40; // Diameter of the nodes
    private static final int NODE_RADIUS = NODE_DIAMETER / 2;

    public TreeVisualizer(AVLTree tree) {
        this.tree = tree;
        this.nodeLocations = new HashMap<>();
        setToolTipText(""); // Enable tooltips
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Node node = getNodeAtPosition(e.getX(), e.getY());
                if (node != null) {
                    showNodeDetails(node);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        nodeLocations.clear(); // Clear previous locations
        drawTree(g, tree.root, getWidth() / 2, 30, getWidth() / 4);
    }

    private void drawTree(Graphics g, Node node, int x, int y, int xOffset) {
        if (node == null) return;

        g.setColor(Color.BLACK);
        g.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_DIAMETER, NODE_DIAMETER);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(node.navigationKey), x - 10, y + 4);

        // Store node location
        nodeLocations.put(new Rectangle(x - NODE_RADIUS, y - NODE_RADIUS, NODE_DIAMETER, NODE_DIAMETER), node);

        if (node.left != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x - NODE_RADIUS, y + NODE_RADIUS, x - xOffset, y + 50 - NODE_RADIUS);
            drawTree(g, node.left, x - xOffset, y + 50, xOffset / 2);
        }

        if (node.right != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x + NODE_RADIUS, y + NODE_RADIUS, x + xOffset, y + 50 - NODE_RADIUS);
            drawTree(g, node.right, x + xOffset, y + 50, xOffset / 2);
        }
    }

    @Override
    public String getToolTipText(MouseEvent event) {
        Node node = getNodeAtPosition(event.getX(), event.getY());
        return node != null ? node.data.nombre + " " + node.data.apellido + " (DNI: " + node.data.dni + ")" : null;
    }

    private Node getNodeAtPosition(int x, int y) {
        for (Map.Entry<Rectangle, Node> entry : nodeLocations.entrySet()) {
            if (entry.getKey().contains(x, y)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private void showNodeDetails(Node node) {
        Persona persona = node.data;
        JOptionPane.showMessageDialog(this, 
            "DNI: " + persona.dni + "\nNombre: " + persona.nombre + "\nApellido: " + persona.apellido + 
            "\nDirección: " + persona.direccion + "\nTeléfono: " + persona.telefono, 
            "Detalles de la Persona", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Example data to visualize
        Persona p1 = new Persona("12345678A", "John", "Doe", "123 Main St", "555-1234");
        Persona p2 = new Persona("87654321B", "Jane", "Smith", "456 Elm St", "555-5678");
        Persona p3 = new Persona("12349876C", "Alice", "Johnson", "789 Oak St", "555-6789");
        Persona p4 = new Persona("87651234D", "Bob", "Brown", "101 Pine St", "555-2345");
        Persona p5 = new Persona("12983465E", "Charlie", "Davis", "102 Maple St", "555-3456");
        Persona p6 = new Persona("89765432F", "Diana", "Clark", "103 Birch St", "555-4567");
        Persona p7 = new Persona("23456789G", "Edward", "Wilson", "104 Cedar St", "555-5678");
        Persona p8 = new Persona("34567890H", "Fiona", "Taylor", "105 Walnut St", "555-6789");
        Persona p9 = new Persona("45678901I", "George", "Anderson", "106 Chestnut St", "555-7890");
        Persona p10 = new Persona("56789012J", "Hannah", "Moore", "107 Hickory St", "555-8901");

        tree.insert(new Node(50, p1));
        tree.insert(new Node(30, p2));
        tree.insert(new Node(70, p3));
        tree.insert(new Node(20, p4));
        tree.insert(new Node(40, p5));
        tree.insert(new Node(60, p6));
        tree.insert(new Node(80, p7));
        tree.insert(new Node(10, p8));
        tree.insert(new Node(25, p9));
        tree.insert(new Node(90, p10));

        JFrame frame = new JFrame("Binary Search Tree Visualization");
        TreeVisualizer panel = new TreeVisualizer(tree);
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
