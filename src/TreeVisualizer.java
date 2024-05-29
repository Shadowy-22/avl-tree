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
            "\nDirección: " + persona.direccion + "\nTeléfono: " + persona.telefono + "\n\nDetalles del Nodo: " + "\nAltura del Nodo: " + node.height + "\nFactor de Equilibrio: " + node.BF,
            "Detalles del Nodo Persona", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}
