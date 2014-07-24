/**
 * 
 */
package org.cytoscape.graph.algorithms.cyGraphAlgoImpl;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.cytoscape.graph.algorithms.cyGraphAlgo.WeightFunction;
import org.cytoscape.graph.algorithms.impl.cyGraphAlgo.KruskalMSTImpl;
import org.cytoscape.graph.algorithms.impl.cyGraphAlgo.KruskalMSTStatsImpl;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.NetworkTestSupport;
import org.junit.Test;

/**
 * @author Jimmy
 * 
 */
public class KruskalMSTImplTest {

	private CyNetwork network;

	@Test
	public void testFindTree() {

		NetworkTestSupport networkTestSupport = new NetworkTestSupport();
		network = networkTestSupport.getNetwork();

		CyNode node1 = network.addNode();
		CyNode node2 = network.addNode();
		CyNode node3 = network.addNode();
		CyNode node4 = network.addNode();
		CyNode node5 = network.addNode();
		CyNode node6 = network.addNode();
		CyNode node7 = network.addNode();
		CyNode node8 = network.addNode();
		CyNode node9 = network.addNode();

		CyEdge edge1 = network.addEdge(node1, node2, false);
		CyEdge edge2 = network.addEdge(node2, node3, false);
		CyEdge edge3 = network.addEdge(node3, node4, false);
		CyEdge edge4 = network.addEdge(node4, node5, false);
		CyEdge edge5 = network.addEdge(node5, node6, false);
		CyEdge edge6 = network.addEdge(node6, node7, false);
		CyEdge edge7 = network.addEdge(node7, node8, false);
		CyEdge edge8 = network.addEdge(node8, node1, false);
		CyEdge edge9 = network.addEdge(node8, node2, false);
		CyEdge edge10 = network.addEdge(node4, node6, false);
		CyEdge edge11 = network.addEdge(node3, node6, false);
		CyEdge edge12 = network.addEdge(node8, node9, false);
		CyEdge edge13 = network.addEdge(node7, node9, false);
		CyEdge edge14 = network.addEdge(node9, node3, false);

		network.getDefaultEdgeTable().createColumn("Weight", Double.class,
				false);
		network.getRow(edge1).set("Weight", 4.0);
		network.getRow(edge2).set("Weight", 8.0);
		network.getRow(edge3).set("Weight", 7.0);
		network.getRow(edge4).set("Weight", 9.0);
		network.getRow(edge5).set("Weight", 10.0);
		network.getRow(edge6).set("Weight", 2.0);
		network.getRow(edge7).set("Weight", 1.0);
		network.getRow(edge8).set("Weight", 8.0);
		network.getRow(edge9).set("Weight", 11.0);
		network.getRow(edge10).set("Weight", 14.0);
		network.getRow(edge11).set("Weight", 4.0);
		network.getRow(edge12).set("Weight", 7.0);
		network.getRow(edge13).set("Weight", 6.0);
		network.getRow(edge14).set("Weight", 2.0);
		
		KruskalMSTImpl kruskal = new KruskalMSTImpl();
		KruskalMSTStatsImpl kruskalStats = kruskal.findTree(network,
				new WeightFunction() {
					public double getWeight(CyEdge edge) {
						return network.getRow(edge).get("Weight", Double.class);
					}
				});
		assertEquals(37, kruskalStats.getWeight(), 0.01);

		Iterable<CyEdge> edgeList = kruskalStats.getMST();
		Set<CyEdge> mstEdges = new HashSet<CyEdge>(
				(Collection<CyEdge>) edgeList);

		assertEquals(true, mstEdges.contains(edge1));
		assertEquals(true, mstEdges.contains(edge3));
		assertEquals(true, mstEdges.contains(edge6));
		assertEquals(true, mstEdges.contains(edge14));
		assertEquals(true, mstEdges.contains(edge4));
		assertEquals(true, mstEdges.contains(edge7));
		assertEquals(true, mstEdges.contains(edge8));
		assertEquals(true, mstEdges.contains(edge11));
		assertEquals(false, mstEdges.contains(edge2));
		assertEquals(false, mstEdges.contains(edge5));
		assertEquals(false, mstEdges.contains(edge9));
		assertEquals(false, mstEdges.contains(edge10));
		assertEquals(false, mstEdges.contains(edge12));
		assertEquals(false, mstEdges.contains(edge13));
	}
}
