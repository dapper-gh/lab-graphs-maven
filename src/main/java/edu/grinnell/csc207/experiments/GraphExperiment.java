package edu.grinnell.csc207.experiments;

import edu.grinnell.csc207.util.Graph;
import edu.grinnell.csc207.util.Edge;

import java.io.PrintWriter;
import java.util.Iterator;


/**
 * A quick experiment with graphs.
 *
 * @author David Stroud
 * @author Sarah Deschamps
 * @author Samuel A. Rebelsky
 */
public class GraphExperiment {

  /**
   * Run our experiments.
   *
   * @param args
   *   Command-line arguments (ignored).
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    // A small graph so that we can force it to expand.
    Graph g = new Graph(2);
    g.addVertex("a");
    g.addVertex("b");
    g.addVertex("c");
    g.dumpWithNames(pen);

    // Add a few edges
    g.addEdge("a", "b", 1);
    g.addEdge("a", "c", 2);
    g.addEdge("b", "c", 3);
    g.addEdge("b", "a", 4);
    g.dumpWithNames(pen);

    // Remove a vertex
    g.removeVertex("b");
    g.dumpWithNames(pen);

    // Add another vertex
    g.addVertex("d");
    g.addEdge("a", "d", 5);
    g.addEdge("d", "a", 6);
    g.dumpWithNames(pen);

    // And another (hopefully, this will replace the old b)
    g.addVertex("e");
    g.addEdge("e", "a", 7);
    g.dumpWithNames(pen);

    pen.println("Reachable:");
    for (Integer i : g.reachable(0)) {
      pen.println(i);
    } // for

    pen.println("Shortest path:");
    for (Edge edge : g.shortestPath(g.vertexNumber("e"), g.vertexNumber("d"))) {
      pen.println(g.vertexName(edge.source()) + " -> " + g.vertexName(edge.target()) + " (" + edge.weight() + ")");
    } // for

    Graph g2 = new Graph();
    g2.addVertex("A");
    g2.addVertex("B");
    g2.addVertex("C");
    g2.addVertex("D");
    g2.addVertex("E");
    g2.addVertex("F");
    g2.addVertex("G");
    
    g2.addEdge("A","F",1);
    g2.addEdge("F","C",3);
    g2.addEdge("C","E",0);
    g2.addEdge("E","D",2);
    g2.addEdge("D","G",3);
    g2.addEdge("G","D",1);
    g2.addEdge("B","G",1);
    g2.addEdge("A","B",1);
    g2.addEdge("B","D",4);

    pen.println("Shortest path:");
    for (Edge edge : g2.shortestPath(g2.vertexNumber("A"), g2.vertexNumber("D"))) {
      pen.println(g2.vertexName(edge.source()) + " -> " + g2.vertexName(edge.target()) + " (" + edge.weight() + ")");
    } // for

    // Make sure the vertices iterator doesn't loop forever
    pen.println("Iterator:");
    Iterator<Integer> iter = g.vertices().iterator();
    int count = 0;
    while (iter.hasNext() && count < 20) {
      iter.next();
      pen.println(count++);
    } // while

    // And another (hopefully, this will expand the graph again)
    g.addVertex("f");
    g.addEdge("c", "f", 8);
    g.addEdge("f", "c", 9);
    g.dumpWithNames(pen);

    // Add an invalid edge
    try {
      g.addEdge("c",  "g", 0);
      pen.println("Surprisingly, added an edge from c to nonexistant g");
    } catch (Exception e) {
      pen.println("Correctly failed to add an edge from c to g.");
    } // try/catch

    // Add/replace a bunch of edges
    for (int i = 1; i <= 4; i++) {
      g.addEdge(0, i, i * 10);
    } // for
    g.dumpWithNames(pen);

    // Remove an edge
    g.removeEdge("a", "c");
    g.dumpWithNames(pen);
  } // main(String[])

} // class GraphExperiment
