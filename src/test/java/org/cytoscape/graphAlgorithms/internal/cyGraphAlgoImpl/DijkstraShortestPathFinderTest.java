/**
 * 
 */
package org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl;

import static org.junit.Assert.*;

import java.util.IdentityHashMap;
import java.util.Map;

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
		
		Map<CyEdge, Double> weightMap = new IdentityHashMap<CyEdge, Double>();
		weightMap.put(edge1, 10.0);
		weightMap.put(edge2, 5.0);
		weightMap.put(edge3, 1.0);
		weightMap.put(edge4, 2.0);
		weightMap.put(edge5, 7.0);
		weightMap.put(edge6, 2.0);
		weightMap.put(edge7, 3.0);
		weightMap.put(edge8, 9.0);
		weightMap.put(edge9, 4.0);
		weightMap.put(edge10, 6.0);
		
		DijkstraShortestPathFinderImpl dijkstraSPF = new DijkstraShortestPathFinderImpl();
		DijkstraStats dijkstraStats = dijkstraSPF.findPath(network, node1, true, weightMap);
		
		
		assertEquals(8.0, dijkstraStats.getDistanceTo(node2),0.01);
		assertEquals(7.0, dijkstraStats.getDistanceTo(node5),0.01);
		assertEquals(9.0, dijkstraStats.getDistanceTo(node4),0.01);
		assertEquals(5.0, dijkstraStats.getDistanceTo(node3),0.01);
		assertEquals(0.0, dijkstraStats.getDistanceTo(node1),0.01);
		
	}
}
