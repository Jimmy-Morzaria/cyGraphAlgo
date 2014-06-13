/**
 * 
 */
package org.cytoscape.graph.algorithms.cyGraphAlgoImpl;

import static org.junit.Assert.*;

import org.cytoscape.graph.algorithms.cyGraphAlgo.DijkstraShortestPathFinder;
import org.cytoscape.graph.algorithms.cyGraphAlgo.DijkstraStats;
import org.cytoscape.graph.algorithms.cyGraphAlgo.WeightFunction;
import org.cytoscape.graph.algorithms.cyGraphAlgoImpl.DijkstraShortestPathFinderImpl;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.NetworkTestSupport;
import org.junit.Test;

/**
 * @author Jimmy
 *
 */
public class DijkstraShortestPathFinderTest {

	private CyNetwork network;
	
	@Test
	public void testFindPath(){
		
		NetworkTestSupport networkTestSupport = new NetworkTestSupport();
		network = networkTestSupport.getNetwork();
		
		CyNode node1 = network.addNode();
		CyNode node2 = network.addNode();
		CyNode node3 = network.addNode();
		CyNode node4 = network.addNode();
		CyNode node5 = network.addNode();
		
		CyEdge edge1 = network.addEdge(node1, node2, true);
		CyEdge edge2 = network.addEdge(node1, node3, true);
		CyEdge edge3 = network.addEdge(node2, node4, true);
		CyEdge edge4 = network.addEdge(node3, node5, true);
		CyEdge edge5 = network.addEdge(node5, node1, true);
		CyEdge edge6 = network.addEdge(node2, node3, true);
		CyEdge edge7 = network.addEdge(node3, node2, true);
		CyEdge edge8 = network.addEdge(node3, node4, true);
		CyEdge edge9 = network.addEdge(node4, node5, true);
		CyEdge edge10 = network.addEdge(node5, node4, true);
		
		network.getDefaultEdgeTable().createColumn("Weight", Double.class,
				false);
		
		network.getRow(edge1).set("Weight", 10.0);
		network.getRow(edge2).set("Weight", 5.0);
		network.getRow(edge3).set("Weight", 1.0);
		network.getRow(edge4).set("Weight", 2.0);
		network.getRow(edge5).set("Weight", 7.0);
		network.getRow(edge6).set("Weight", 2.0);
		network.getRow(edge7).set("Weight", 3.0);
		network.getRow(edge8).set("Weight", 9.0);
		network.getRow(edge9).set("Weight", 4.0);
		network.getRow(edge10).set("Weight", 6.0);
		
		DijkstraShortestPathFinder dijkstraSPF = new DijkstraShortestPathFinderImpl();
		DijkstraStats dijkstraStats = dijkstraSPF.findPath(network, node1, true, new WeightFunction() {
			public double getWeight(CyEdge edge) {
				return network.getRow(edge).get("Weight", Double.class);
			}
		});
		
		
		assertEquals(8.0, dijkstraStats.getDistanceTo(node2),0.01);
		assertEquals(7.0, dijkstraStats.getDistanceTo(node5),0.01);
		assertEquals(9.0, dijkstraStats.getDistanceTo(node4),0.01);
		assertEquals(5.0, dijkstraStats.getDistanceTo(node3),0.01);
		assertEquals(0.0, dijkstraStats.getDistanceTo(node1),0.01);
		
	}
}
