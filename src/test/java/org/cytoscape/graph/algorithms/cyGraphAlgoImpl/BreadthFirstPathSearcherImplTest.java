package org.cytoscape.graph.algorithms.cyGraphAlgoImpl;


import static org.junit.Assert.*;

import java.util.Stack;

import org.cytoscape.graph.algorithms.api.Callback;
import org.cytoscape.graph.algorithms.impl.BfsCallback;
import org.cytoscape.graph.algorithms.impl.BfsStatsImpl;
import org.cytoscape.graph.algorithms.impl.BreadthFirstPathSearcherImpl;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.NetworkTestSupport;
import org.junit.Test;

public class BreadthFirstPathSearcherImplTest {

		
	private CyNetwork network;
			
	@SuppressWarnings("unused")
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
		
		CyEdge edge1 = network.addEdge(node1, node2, true);
		CyEdge edge2 = network.addEdge(node2, node3, true);
		CyEdge edge3 = network.addEdge(node3, node4, true);
		CyEdge edge4 = network.addEdge(node4, node5, true);
		CyEdge edge5 = network.addEdge(node4, node6, true);
		CyEdge edge6 = network.addEdge(node5, node6, true);
		CyEdge edge7 = network.addEdge(node5, node8, true);
		CyEdge edge8 = network.addEdge(node6, node8, true);
		CyEdge edge9 = network.addEdge(node6, node7, true);
		CyEdge edge10 = network.addEdge(node7, node8, true);
		
		Callback callback = new BfsCallback(node8);
		
		BreadthFirstPathSearcherImpl bfsearcher = new BreadthFirstPathSearcherImpl();
		BfsStatsImpl bfsStats = bfsearcher.search(network, node3, false, callback);
		assertEquals(0,bfsStats.getDistanceTo(node3));
		assertEquals(1,bfsStats.getDistanceTo(node4));
		assertEquals(1,bfsStats.getDistanceTo(node2));
		assertEquals(2,bfsStats.getDistanceTo(node1));
		assertEquals(2,bfsStats.getDistanceTo(node5));
		assertEquals(2,bfsStats.getDistanceTo(node6));
		assertEquals(3,bfsStats.getDistanceTo(node7));
		assertEquals(3,bfsStats.getDistanceTo(node8));
		
		
		BreadthFirstPathSearcherImpl bfsearcher2 = new BreadthFirstPathSearcherImpl();
		BfsStatsImpl bfsStats2 = bfsearcher2.search(network, node1, true, callback);
		assertEquals(1,bfsStats2.getDistanceTo(node2));
		assertEquals(3,bfsStats2.getDistanceTo(node4));
		assertEquals(4,bfsStats2.getDistanceTo(node5));
		
		Stack<CyNode> s = (Stack<CyNode>) bfsStats2.getPathTo(node6);
		assertEquals(node1,s.pop());
		assertEquals(node2,s.pop());
		assertEquals(node3,s.pop());
		assertEquals(node4,s.pop());
		assertEquals(node6,s.pop());
	}

}