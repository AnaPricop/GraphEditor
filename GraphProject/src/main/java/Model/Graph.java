package Model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author anna
 */
public class Graph {

    private String title;
    public List<Node> nodes;
    private List<Edge> edges;
    private int v;
    int aux1 = 1;
    int index = 1;
    int i, j;

    public Graph(String title) {

        setTitle(title);
        setNodes(new ArrayList<Node>());
        setEdges(new ArrayList<Edge>());
    }

    public void draw(Graphics g) {
        for (Edge edge : getEdges()) {
            edge.draw(g);
        }

        for (Node node : getNodes()) {
            node.draw(g);
        }
    }

    public void addNode(Node n) {
        nodes.add(n);
        System.out.println(n.getAux());
    }

    public void addEdge(Edge e) {

        for (Edge edge : edges) {
            if (e.equals(edge)) {
                return;
            }
        }
        edges.add(e);
        //  i = e.nodeA.getAux();
        //  j = e.nodeB.getAux();

    }

    public Node findnode_1(int mx, int my) {
        for (Node node : nodes) {
            if (node.isUnderCursor(mx, my)) {
                return node;
            }
        }
        return null;
    }

    public Edge findedge_1(int mx, int my) {
        for (Edge edge : edges) {
            if (edge.isUnderCursor(mx, my)) {
                return edge;
            }
        }
        return null;
    }

    public void removeNode(Node nodeUnderCursor) {
        removeEdge1(nodeUnderCursor);
        nodes.remove(nodeUnderCursor);
    }

    protected void removeEdge1(Node nodeUnderCursor) {
        edges.removeIf(e -> {
            return e.getNodeA().equals(nodeUnderCursor)
                    || e.getNodeB().equals(nodeUnderCursor);
        });
    }

    public void removeEdge(Edge edgeUnderCursor) {
        edges.remove(edgeUnderCursor);
    }

    public void moveGraph(int dx, int dy) {
        for (Node node : nodes) {
            node.move(dx, dy);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            title = "";
        } else {
            this.title = title;
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public String getListOfNodes() {

        int aux = 0;
        String list = "Number of nodes: " + Integer.toString(nodes.size()) + "\n";
        list += "N. [Type]: (position) {parameters}\n";
        for (Node node : nodes) {
            //list += aux++;
            // list += ". ";
            list += Integer.toString(index++);

            list += ". ";
            list += node.toString();
            list += "\n";
        }
        return list;
    }

    public String getListOfEdges() {
        int index = 1;
        String list = "Number of edges: " + Integer.toString(edges.size()) + "\n";
        list += "N. [Type]: (Node A) ===> (Node B) {parameters}\n";
        for (Edge edge : edges) {
            // list += index;
            list += Integer.toString(index++);
            list += ". ";
            list += edge.toString();
            list += "\n";
        }
        return list;
    }

    @Override
    public String toString() {
        return title + "(" + nodes.size() + " nodes, " + edges.size() + " edges)";
    }

}
