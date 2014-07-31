/**
 * 
 */
package org.cytoscape.graph.centralities;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.cytoscape.graph.centralities.impl.BetweennessStressImpl;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.NetworkTestSupport;
import org.junit.Test;

/**
 * @author Jimmy
 * 
 */
public class BetweennessImplTest {

	private CyNetwork network;

	@Test
	public void testGetBetweenness() {

		NetworkTestSupport networkTestSupport = new NetworkTestSupport();
		network = networkTestSupport.getNetwork();

		CyNode node1 = network.addNode();
		CyNode node2 = network.addNode();
		CyNode node3 = network.addNode();
		CyNode node4 = network.addNode();
		CyNode node5 = network.addNode();
		
		CyEdge edge1 = network.addEdge(node1, node2, false);
		CyEdge edge2 = network.addEdge(node2, node3, false);
		CyEdge edge3 = network.addEdge(node2, node4, false);
		CyEdge edge4 = network.addEdge(node3, node5, false);
		CyEdge edge5 = network.addEdge(node4, node5, false);
		

		BetweennessStressImpl betweenness = new BetweennessStressImpl();
		Map<CyIdentifiable, List<Double>> nodeBetweennessMap = betweenness.getBetweennessStress(network, false);
		assertEquals(0.0, nodeBetweennessMap.get(node1).get(0), 0.01);
		assertEquals(0.583, nodeBetweennessMap.get(node2).get(0), 0.01);
		assertEquals(0.166, nodeBetweennessMap.get(node3).get(0), 0.01);
		assertEquals(0.166, nodeBetweennessMap.get(node4).get(0), 0.01);
		assertEquals(0.083, nodeBetweennessMap.get(node5).get(0), 0.01);
		
		assertEquals(0.0, nodeBetweennessMap.get(node1).get(1), 0.01);
		assertEquals(10.0, nodeBetweennessMap.get(node2).get(1), 0.01);
		assertEquals(4.0, nodeBetweennessMap.get(node3).get(1), 0.01);
		assertEquals(4.0, nodeBetweennessMap.get(node4).get(1), 0.01);
		assertEquals(2.0, nodeBetweennessMap.get(node5).get(1), 0.01);	
		
		assertEquals(8.0, nodeBetweennessMap.get(edge1).get(0), 0.01);
		assertEquals(7.0, nodeBetweennessMap.get(edge2).get(0), 0.01);
		assertEquals(7.0, nodeBetweennessMap.get(edge3).get(0), 0.01);
		assertEquals(5.0, nodeBetweennessMap.get(edge4).get(0), 0.01);
		assertEquals(5.0, nodeBetweennessMap.get(edge5).get(0), 0.01);
	}
}
