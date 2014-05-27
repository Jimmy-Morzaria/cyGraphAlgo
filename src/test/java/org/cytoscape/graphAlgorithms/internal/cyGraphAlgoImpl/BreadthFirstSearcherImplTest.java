package org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl;


import static org.junit.Assert.*;

import org.cytoscape.graphAlgorithms.internal.cyGraphAlgo.BreadthFirstSearcher;
import org.cytoscape.graphAlgorithms.internal.cyGraphAlgo.Callback;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.NetworkTestSupport;
import org.junit.Test;

public class BreadthFirstSearcherImplTest {

		
	private CyNetwork network;
			
	@Test
	public void testSearch(){
				
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
		
		CyEdge edge1 = network.addEdge(node1, node2, false);
		CyEdge edge2 = network.addEdge(node2, node3, false);
		CyEdge edge3 = network.addEdge(node3, node4, false);
		CyEdge edge4 = network.addEdge(node4, node5, false);
		CyEdge edge5 = network.addEdge(node4, node6, false);
		CyEdge edge6 = network.addEdge(node5, node6, false);
		CyEdge edge7 = network.addEdge(node5, node8, false);
		CyEdge edge8 = network.addEdge(node6, node8, false);
		CyEdge edge9 = network.addEdge(node6, node7, false);
		CyEdge edge10 = network.addEdge(node7, node8, false);
		
		BreadthFirstSearcher bfsSearcher = new BreadthFirstSearcherImpl();
		Callback callback = new BfsCallback(node3);
		BfsStat bfsStat = bfsSearcher.search(network, node3, false, callback);
		assertEquals(2,bfsStat.getDistance(node1));
		
	}

}




