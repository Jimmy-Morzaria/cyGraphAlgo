/**
 * 
 */
package org.cytoscape.graph.algorithms.cyGraphAlgoImpl;

import static org.junit.Assert.*;

import org.cytoscape.graph.algorithms.api.HITS;
import org.cytoscape.graph.algorithms.api.HITSResults;
import org.cytoscape.graph.algorithms.impl.HITSImpl;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.NetworkTestSupport;
import org.junit.Test;

/**
 * @author Jimmy
 *
 */
public class HITSTest {

	private CyNetwork network;
	
	@SuppressWarnings("unused")
	@Test
	public void testCompute(){
		
		NetworkTestSupport networkTestSupport = new NetworkTestSupport();
		network = networkTestSupport.getNetwork();
		
		CyNode node1 = network.addNode();
		CyNode node2 = network.addNode();
		CyNode node3 = network.addNode();
		CyNode node4 = network.addNode();
		
		CyEdge edge1 = network.addEdge(node1, node3, true);
		CyEdge edge2 = network.addEdge(node2, node3, true);
		CyEdge edge3 = network.addEdge(node1, node4, true);
		CyEdge edge4 = network.addEdge(node3, node4, true);
		
		
		HITS hits = new HITSImpl();
		HITSResults hitsResults = hits.compute(network, true);
		assertEquals(0,hitsResults.getAuthorityValue(node1),0.001);
		assertEquals(0,hitsResults.getAuthorityValue(node2),0.001);
		assertEquals(0.707,hitsResults.getAuthorityValue(node3),0.001);
		assertEquals(0.707,hitsResults.getAuthorityValue(node4),0.001);
		
		assertEquals(0.816,hitsResults.getHubValue(node1),0.001);
		assertEquals(0.408,hitsResults.getHubValue(node2),0.001);
		assertEquals(0.408,hitsResults.getHubValue(node3),0.001);
		assertEquals(0.0,hitsResults.getHubValue(node4),0.001);
		
	}
}
