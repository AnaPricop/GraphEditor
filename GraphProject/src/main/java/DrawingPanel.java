
import Model.Edge;
import Model.Node;
import Model.Graph;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author anna
 */
public final class DrawingPanel extends JPanel implements MouseListener, ComponentListener, MouseMotionListener {

    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image
    private Graph graph;
    private boolean mouseLeftButton = false;
    private boolean mouseRightButton = false;
    private Node node_1;
    private Edge edge_1;
    String text1;
    private int selectex1;
    private int selectey1;
    private Node edge_A;
    private Node edge_B;
    private List<Node> nodes;
    private boolean choose_B = false;
    JMenuBar menubar;
    JButton tree;
    int aux = 1;
    int j = 0;
    int aux1 = 1;
    public Node node1;
    public Node node2;

    enum Color1 {
        WHITE, RED, GREEN
    }

    private LinkedList<Integer> adj[];
    private LinkedList<Integer> adjList[];
    //public int matrix[][] = ma
    public int[][] matrix = new int[100][100];

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;

        createOffscreenImage();
        init();
    }

    private void init() {
        aux = 1;
        j = 0;
        aux1 = 1;
        setPreferredSize(new Dimension(W, H));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEtchedBorder());
        if (graph == null) {
            graph = new Graph("Graf");
        } else {
            setGraph(graph);
        }

        adj = new LinkedList[50];
        adjList = new LinkedList[50];
        addComponentListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocus();
        addMouseMotionListener(this);

    }

    public void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    public void showTree() {
        graph = new Graph("Example");
        int i, j;
        //aux = 13;
        Node t = new Node(360, 100, Color.RED, 20, "1", 1);
        Node b = new Node(550, 100, Color.GREEN, 20, "2", 2);
        Node bb = new Node(550, 180, Color.GREEN, 20, "3", 3);
        Node c = new Node(550, 400, Color.MAGENTA, 20, "4", 4);
        Node d = new Node(250, 400, Color.ORANGE, 20, "5", 5);
        Node e = new Node(300, 360, Color.RED, 20, "6", 6);
        Node f = new Node(450, 200, Color.GREEN, 20, "7", 7); //root	
        Node g = new Node(450, 300, Color.MAGENTA, 20, "8", 8);
        Node cc = new Node(550, 320, Color.MAGENTA, 20, "9", 9);
        Node h1 = new Node(350, 500, Color.ORANGE, 20, "10", 10);
        Node hh = new Node(210, 530, Color.ORANGE, 20, "11", 11);

        adj[t.getAux()] = new LinkedList();
        adj[b.getAux()] = new LinkedList();
        adj[bb.getAux()] = new LinkedList();
        adj[c.getAux()] = new LinkedList();
        adj[d.getAux()] = new LinkedList();
        adj[e.getAux()] = new LinkedList();
        adj[f.getAux()] = new LinkedList();
        adj[g.getAux()] = new LinkedList();
        adj[cc.getAux()] = new LinkedList();
        adj[h1.getAux()] = new LinkedList();
        adj[hh.getAux()] = new LinkedList();

        adjList[t.getAux()] = new LinkedList();
        adjList[b.getAux()] = new LinkedList();
        adjList[bb.getAux()] = new LinkedList();
        adjList[c.getAux()] = new LinkedList();
        adjList[d.getAux()] = new LinkedList();
        adjList[e.getAux()] = new LinkedList();
        adjList[f.getAux()] = new LinkedList();
        adjList[g.getAux()] = new LinkedList();
        adjList[cc.getAux()] = new LinkedList();
        adjList[h1.getAux()] = new LinkedList();
        adjList[hh.getAux()] = new LinkedList();

        Edge ab1 = new Edge(f, t);
        adj[f.getAux()].add(t.getAux());
        adj[t.getAux()].add(f.getAux());
        adjList[f.getAux()].add(t.getAux());
        adjList[t.getAux()].add(f.getAux());

        Edge ab11 = new Edge(f, bb);
        adj[f.getAux()].add(bb.getAux());
        adj[bb.getAux()].add(f.getAux());
        adjList[f.getAux()].add(t.getAux());
        adjList[t.getAux()].add(f.getAux());

        Edge fg = new Edge(f, g);
        adj[f.getAux()].add(g.getAux());
        adj[g.getAux()].add(f.getAux());
        adjList[f.getAux()].add(t.getAux());
        adjList[t.getAux()].add(f.getAux());

        Edge gh = new Edge(g, h1);
        adj[g.getAux()].add(h1.getAux());
        adj[h1.getAux()].add(g.getAux());
        adjList[f.getAux()].add(t.getAux());
        adjList[t.getAux()].add(f.getAux());

        Edge he = new Edge(h1, e);
        adj[h1.getAux()].add(e.getAux());
        adj[e.getAux()].add(h1.getAux());
        adjList[f.getAux()].add(t.getAux());
        adjList[t.getAux()].add(f.getAux());

        Edge he1 = new Edge(g, cc);
        adj[cc.getAux()].add(g.getAux());
        adj[g.getAux()].add(cc.getAux());
        adjList[f.getAux()].add(t.getAux());
        adjList[t.getAux()].add(f.getAux());

        Edge bf = new Edge(b, f);
        adj[f.getAux()].add(b.getAux());
        adj[b.getAux()].add(f.getAux());
        adjList[f.getAux()].add(t.getAux());
        adjList[t.getAux()].add(f.getAux());

        Edge cg = new Edge(c, g);
        adj[c.getAux()].add(g.getAux());
        adj[g.getAux()].add(c.getAux());
        adjList[f.getAux()].add(t.getAux());
        adjList[t.getAux()].add(f.getAux());

        Edge dh = new Edge(d, h1);
        adj[d.getAux()].add(h1.getAux());
        adj[h1.getAux()].add(d.getAux());
        adjList[f.getAux()].add(t.getAux());
        adjList[t.getAux()].add(f.getAux());

        Edge hhh = new Edge(hh, h1);
        adj[hh.getAux()].add(h1.getAux());
        adj[h1.getAux()].add(hh.getAux());
        adjList[f.getAux()].add(t.getAux());
        adjList[t.getAux()].add(f.getAux());

        graph.addNode(t);
        graph.addNode(hh);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(f);
        graph.addNode(g);
        graph.addNode(h1);
        graph.addNode(bb);
        graph.addNode(cc);

        graph.addEdge(ab11);
        graph.addEdge(hhh);
        graph.addEdge(fg);
        graph.addEdge(gh);
        graph.addEdge(he);
        graph.addEdge(ab1);
        graph.addEdge(bf);
        graph.addEdge(cg);
        graph.addEdge(dh);
        graph.addEdge(he1);
        aux = 12;
        repaint();
    }

    public void randomGraph() {
        graph = new Graph("Random");
        aux = 1;
        // Object[] array= new Object[10];
        int exists = 0;
//List<Object> list= Arrays.asList(array);
        String text = JOptionPane.showInputDialog(this, "Nodes:");
        //graph.setNodes(new ArrayList<Node>());
        int nr = Integer.parseInt(text);
        // System.out.println(nr);
        int max_limit = nr * (nr - 1);
        int edges = 1 + (int) (Math.random() * ((max_limit - 1) + 1));
        // System.out.println(edges);
        while (nr > 0) {
            Random rand = new Random();
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            Color randomColor = new Color(r, g, b);
            Node node3 = new Node(80 + (int) (Math.random() * ((720 - 80) + 1)), 80 + (int) (Math.random() * ((520 - 80) + 1)), randomColor, 20, " ", aux);
            graph.addNode(node3);
            // array[aux] = node3;
            //nodes.add(aux, node3);
            adj[aux] = new LinkedList();
            adjList[aux] = new LinkedList<>();
            aux++;
            nr--;
            repaint();
        }

        List<Node> list = graph.getNodes();
        Random random1 = new Random();
        for (int i = 1; i <= edges; i++) {
            // randomly select two vertices to
            // create an edge between them
            int v = 1 + (int) (Math.random() * ((aux - 1 - 1) + 1));
            int w = 1 + (int) (Math.random() * ((aux - 1 - 1) + 1));
            if (v != w) {
                for (Node node : list) {
                    if (node.getAux() == v) {
                        node1 = node;
                    }

                }

                for (Node node : list) {
                    if (node.getAux() == w) {
                        node2 = node;
                    }

                }

                // add an edge between them
                Edge ep = new Edge(node1, node2);

                List<Edge> list1 = graph.getEdges();

                for (Edge edge : list1) {

                    if ((ep.nodeA == edge.nodeA && ep.nodeB == edge.nodeB) || (ep.nodeA == edge.nodeB && ep.nodeB == edge.nodeA)) {
                        exists = 1;
                    }

                }

                if (exists == 0) {
                    adj[v].add(w);
                    adj[w].add(v);
                    adjList[v].add(w);
                    adjList[w].add(v);
                    graph.addEdge(ep);
                    System.out.println(ep);
                    repaint();
                }
            }
            exists = 0;
        }
        //repaint();
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        if (graph == null) {
            this.graph = new Graph("Graph");
        } else {
            this.graph = graph;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (graph != null) {
            graph.draw(g);
        }
    }

    public void createNewGraph() {
        setGraph(new Graph("NewGraph"));
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (mouseLeftButton) {
            moveG(e.getX(), e.getY());
        } else {
            setcursor(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        setcursor(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseLeftButton = true;
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            mouseRightButton = true;
        }

        setcursor(e);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseLeftButton = false;
            addEdge2();
            addEdge();
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            mouseRightButton = false;
            choose_B = false;
            if (node_1 != null) {
                nodeMenu(e, node_1);
                // addEdge();
            } else if (edge_1 != null) {
                edgeMenu(e, edge_1);
                //addEdge();
            } else {
                Menu(e);
            }
        }
        setcursor(e);
    }

    void addEdge() {
        int i, j;
        i = edge_A.getAux();
        j = edge_B.getAux();
        adj[i].add(j);
        adj[j].add(i);
        adjList[i].addFirst(j);
        adjList[i].addFirst(j);
    }

    void addEdgeR(int v, int w) {
        // int i, j;
        // i = edge_A.getAux();
        // j = edge_B.getAux();
        adj[v].add(w);
        adj[w].add(v);
        adjList[v].addFirst(w);
        adjList[w].addFirst(v);
    }

    boolean isCyclicUtil(int v, boolean visited[], int parent) {
        // Mark the current node as visited 
        visited[v] = true;
        j = 1;
        Integer i;

        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()) {
            i = it.next();

            if (!visited[i]) {
                if (isCyclicUtil(i, visited, v)) {
                    return true;
                }
            } else if (i != parent) {
                return true;
            }
        }
        return false;
    }

    boolean isTree1() {

        boolean visited[] = new boolean[aux + 1];
        for (int i = 1; i < aux; i++) {
            visited[i] = false;
        }

        if (isCyclicUtil(1, visited, 0)) {
            return false;
        }

        for (int u = 1; u < aux; u++) {
            if (!visited[u]) {
                return false;
            }
        }

        return true;

    }

    public void isTree() {
        if (isTree1()) {
            JOptionPane.showMessageDialog(this, "The graph is a tree.");
            System.out.println("Graph is Tree");
        } else {
            System.out.println("Graph is not Tree");
            JOptionPane.showMessageDialog(this, "The graph is not a tree.");
        }
    }

    public boolean isBipartite() {
        //check if graph is empty
        if (aux - 1 == 0) {
            return true;
        }

        Color1 colors[] = new Color1[aux];

        for (int i = 1; i < colors.length; i++) {
            colors[i] = Color1.WHITE;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int source = 0; source < aux - 1; source++) {

            if (colors[source] == Color1.WHITE) {
                colors[source] = Color1.RED;

                queue.add(source);

                while (!queue.isEmpty()) {
                    int v = queue.remove();
                    for (int i = 0; i <= adjList[v].size(); i++) {
                        int dest = adjList[v].get(i);

                        if (colors[dest] == Color1.WHITE) {

                            if (colors[v] == Color1.RED) {

                                colors[dest] = Color1.GREEN;
                            } else if (colors[v] == Color1.GREEN) {

                                colors[dest] = Color1.RED;
                            }

                            queue.add(dest);
                        } else if (colors[v] == colors[dest]) {

                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void verifyBipartite() {
        if (isBipartite()) {
            JOptionPane.showMessageDialog(this, "The graph is bipartite.");
            // System.out.println("Graph is Tree");
        } else {
            //System.out.println("Graph is not Tree");
            JOptionPane.showMessageDialog(this, "The graph is not bipartite.");
        }
    }

    private void Menu(MouseEvent e) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem item1 = new JMenuItem("New node");
        popupMenu.add(item1);
        item1.addActionListener((action) -> {
            createNewNode(e.getX(), e.getY());
        });
        popupMenu.show(e.getComponent(), e.getX(), e.getY());

    }

    private void nodeMenu(MouseEvent e, Node n) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem remove1 = new JMenuItem("Remove node");
        popupMenu.add(remove1);
        remove1.addActionListener((action) -> {
            removeNode(n);
        });

        popupMenu.addSeparator();

        JMenuItem item2 = new JMenuItem("Add edge");
        popupMenu.add(item2);
        item2.addActionListener((action) -> {
            initializeAddEdge(n);
        });

        popupMenu.show(e.getComponent(), e.getX(), e.getY());

    }

    private void edgeMenu(MouseEvent event, Edge e) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem remove1 = new JMenuItem("Remove edge");
        popupMenu.add(remove1);
        remove1.addActionListener((action) -> {
            removeEdge(e);
        });
    }

    public void setcursor(MouseEvent e) {
        if (e != null) {
            node_1 = graph.findnode_1(e.getX(), e.getY());
            if (node_1 == null) {
                edge_1 = graph.findedge_1(e.getX(), e.getY());
            }
            selectex1 = e.getX();
            selectey1 = e.getY();
        }
        int cursor;
        if (node_1 != null) {
            cursor = Cursor.HAND_CURSOR;
        } else if (mouseLeftButton) {
            cursor = Cursor.MOVE_CURSOR;
        } else if (choose_B) {
            cursor = Cursor.WAIT_CURSOR;
        } else if (edge_1 != null) {
            cursor = Cursor.CROSSHAIR_CURSOR;
        } else {
            cursor = Cursor.DEFAULT_CURSOR;
        }
        setCursor(Cursor.getPredefinedCursor(cursor));

    }

    private void moveG(int x, int y) {

        int x1 = x - selectex1;
        int y1 = y - selectey1;

        if (node_1 != null) {
            node_1.move(x1, y1);
        } else if (edge_1 != null) {
            edge_1.move(x1, y1);
        } else {
            graph.moveGraph(x1, y1);
        }

        selectex1 = x;
        selectey1 = y;
        repaint();
    }

    private void createNewNode(int x, int y) {
        try {
            Color color = JColorChooser.showDialog(this, "Choose color", Color.GRAY);
            int radius = ((Integer) JOptionPane.showInputDialog(this, "Choose size", "New node", JOptionPane.DEFAULT_OPTION, null, Node.RADIUS_VALUES, Node.RADIUS_VALUES[0]));
            text1 = JOptionPane.showInputDialog(this, "New node");
            adj[aux] = new LinkedList();
            adjList[aux] = new LinkedList<>();
            graph.addNode(new Node(x, y, color, radius, text1, aux));
            aux++;
            //System.out.println(node.getAux());

            repaint();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Operation canceled.");
        }

    }

    private void removeNode(Node n) {
        graph.removeNode(n);
        repaint();
    }

    private void removeEdge(Edge e) {
        graph.removeEdge(e);
        repaint();
    }

    private void initializeAddEdge(Node n) {
        if (node_1 != null) {
            edge_A = n;
            choose_B = true;
            setcursor(null);
        }
        //addEdge();
    }

    private void addEdge2() {
        if (choose_B) {
            if (node_1 != null) {
                if (node_1.equals(edge_A)) {
                    JOptionPane.showMessageDialog(this, "Choose a different node.");
                } else {
                    try {
                        //addEdge();
                        edge_B = node_1;
                        Color color = JColorChooser.showDialog(this, "Choose color", Color.BLACK);
                        int stroke = ((Integer) JOptionPane.showInputDialog(this, "Choose the size you want", "New edge", JOptionPane.DEFAULT_OPTION, null, Edge.STROKE_VALUES, Edge.STROKE_VALUES[0]));
                        graph.addEdge(new Edge(edge_A, edge_B, color, stroke));
                        repaint();
                    } catch (NullPointerException e) {
                        JOptionPane.showMessageDialog(this, "Operation canceled.");
                    }

                }
            }
            choose_B = false;
        }
    }

    public void showNodesList() {
        String nodesList = graph.getListOfNodes();
        JOptionPane.showMessageDialog(this, nodesList, "Nodes list", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showEdgesList() {
        String nodesList = graph.getListOfEdges();
        JOptionPane.showMessageDialog(this, nodesList, "Edges list", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentResized(ComponentEvent ce) {

    }
}
