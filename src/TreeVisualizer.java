import javax.swing.*;
import java.awt.*;  

public class TreeVisualizer extends JPanel {

    private AVLTree tree;

    public TreeVisualizer(AVLTree tree) {
        this.tree = tree;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g, tree.root, getWidth() / 2, 30, getWidth() / 4);
    }

    private void drawTree(Graphics g, Node node, int x, int y, int xOffset) {
        if (node == null) return;

        g.setColor(Color.BLACK);
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(node.data), x - 7, y + 4);

        if (node.left != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x - 15, y + 15, x - xOffset, y + 50 - 15);
            drawTree(g, node.left, x - xOffset, y + 50, xOffset / 2);
        }

        if (node.right != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x + 15, y + 15, x + xOffset, y + 50 - 15);
            drawTree(g, node.right, x + xOffset, y + 50, xOffset / 2);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(new Node(50));
        tree.insert(new Node(30));
        tree.insert(new Node(70));
        tree.insert(new Node(20));
        tree.insert(new Node(40));
        tree.insert(new Node(60));
        tree.insert(new Node(80));

        JFrame frame = new JFrame("Binary Search Tree Visualization");
        TreeVisualizer panel = new TreeVisualizer(tree);
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}