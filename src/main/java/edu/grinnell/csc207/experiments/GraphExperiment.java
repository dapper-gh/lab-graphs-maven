package edu.grinnell.csc207.experiments;

import edu.grinnell.csc207.util.Graph;

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
