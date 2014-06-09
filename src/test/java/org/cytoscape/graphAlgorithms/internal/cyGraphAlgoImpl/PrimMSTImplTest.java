/**
 * 
 */
package org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.NetworkTestSupport;
import org.junit.Test;

/**
 * @author Jimmy
 *
 */
public class PrimMSTImplTest {

	private CyNetwork network;
	
	@Test
	public void testFindTree(){
		
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
		
		Map<CyEdge, Double> weightMap = new IdentityHashMap<CyEdge, Double>();
		weightMap.put(edge1, 4.0);
		weightMap.put(edge2, 8.0);
		weightMap.put(edge3, 7.0);
		weightMap.put(edge4, 9.0);
		weightMap.put(edge5, 10.0);
		weightMap.put(edge6, 2.0);
		weightMap.put(edge7, 1.0);
		weightMap.put(edge8, 8.0);
		weightMap.put(edge9, 11.0);
		weightMap.put(edge10, 14.0);
		weightMap.put(edge11, 4.0);
		weightMap.put(edge12, 7.0);
		weightMap.put(edge13, 6.0);
		weightMap.put(edge14, 2.0);
		
		PrimMSTImpl prim = new PrimMSTImpl();
		PrimMSTStats primStats = prim.findTree(network, weightMap);
		assertEquals(37, primStats.getWeight(), 0.01);
		
		Iterable<CyEdge> edgeList = primStats.getMST();
		Set<CyEdge> primEdges = new HashSet<CyEdge>((Collection<CyEdge>) edgeList);
		
		assertEquals(true, primEdges.contains(edge1));
		assertEquals(true, primEdges.contains(edge3));
		assertEquals(true, primEdges.contains(edge6));
		assertEquals(true, primEdges.contains(edge14));
		assertEquals(true, primEdges.contains(edge4));
		assertEquals(true, primEdges.contains(edge7));
		assertEquals(true, primEdges.contains(edge8));
		assertEquals(true, primEdges.contains(edge11));
		assertEquals(false, primEdges.contains(edge2));
		assertEquals(false, primEdges.contains(edge5));
		assertEquals(false, primEdges.contains(edge9));
		assertEquals(false, primEdges.contains(edge10));
		assertEquals(false, primEdges.contains(edge12));
		assertEquals(false, primEdges.contains(edge13));
		
	}
}
