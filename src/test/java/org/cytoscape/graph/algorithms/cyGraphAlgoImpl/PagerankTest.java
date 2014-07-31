/**
 * 
 */
package org.cytoscape.graph.algorithms.cyGraphAlgoImpl;

import static org.junit.Assert.*;

import org.cytoscape.graph.algorithms.api.Pagerank;
import org.cytoscape.graph.algorithms.api.PagerankResults;
import org.cytoscape.graph.algorithms.impl.PagerankImpl;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.NetworkTestSupport;
import org.junit.Test;

/**
 * @author Jimmy
 *
 */
public class PagerankTest {

	private CyNetwork network;
	
	@SuppressWarnings("unused")
	@Test
	public void testGetPageranks(){
		
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
		
		CyEdge edge1 = network.addEdge(node1, node2, true);
		CyEdge edge2 = network.addEdge(node1, node3, true);
		CyEdge edge3 = network.addEdge(node2, node4, true);
		CyEdge edge4 = network.addEdge(node4, node2, true);
		CyEdge edge5 = network.addEdge(node3, node2, true);
		CyEdge edge6 = network.addEdge(node3, node5, true);
		CyEdge edge7 = network.addEdge(node4, node5, true);
		CyEdge edge8 = network.addEdge(node4, node6, true);
		CyEdge edge9 = network.addEdge(node5, node6, true);
		CyEdge edge10 = network.addEdge(node5, node7, true);
		CyEdge edge11 = network.addEdge(node5, node8, true);
		CyEdge edge12 = network.addEdge(node7, node5, true);
		CyEdge edge13 = network.addEdge(node7, node8, true);
		CyEdge edge14 = network.addEdge(node6, node8, true);
		CyEdge edge15 = network.addEdge(node8, node6, true);
		CyEdge edge16 = network.addEdge(node8, node7, true);
		
		Pagerank pagerank = new PagerankImpl();
		PagerankResults pagerankResults = pagerank.getPageranks(network, null, true);
		assertEquals(0.019,pagerankResults.getPagerank(node1),0.001);
		assertEquals(0.057,pagerankResults.getPagerank(node2),0.001);
		assertEquals(0.027,pagerankResults.getPagerank(node3),0.001);
		assertEquals(0.067,pagerankResults.getPagerank(node4),0.001);
		assertEquals(0.129,pagerankResults.getPagerank(node5),0.001);
		assertEquals(0.206,pagerankResults.getPagerank(node6),0.001);
		assertEquals(0.187,pagerankResults.getPagerank(node7),0.001);
		assertEquals(0.309,pagerankResults.getPagerank(node8),0.001);
		
	}
}
