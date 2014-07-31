/**
 * 
 */
package org.cytoscape.graph.centralities;

import static org.junit.Assert.*;

import org.cytoscape.graph.centralities.api.ClusteringCoefficient;
import org.cytoscape.graph.centralities.impl.ClusteringCoefficientImpl;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.NetworkTestSupport;
import org.junit.Test;

/**
 * @author Jimmy
 *
 */
public class ClusteringCoefficientImplTest {

	private CyNetwork network, network1;
	
	@Test
	public void testGetClusteringCoefficient(){
		
		NetworkTestSupport networkTestSupport = new NetworkTestSupport();
		network = networkTestSupport.getNetwork();
		network1 = networkTestSupport.getNetwork();
		
		CyNode node1 = network.addNode();
		CyNode node2 = network.addNode();
		CyNode node3 = network.addNode();
		CyNode node4 = network.addNode();
		
		CyEdge edge1 = network.addEdge(node1, node2, false);
		CyEdge edge2 = network.addEdge(node1, node3, false);
		CyEdge edge3 = network.addEdge(node2, node4, false);
		CyEdge edge4 = network.addEdge(node3, node4, false);
		CyEdge edge5 = network.addEdge(node2, node3, false);
		
		ClusteringCoefficient cCoefficient = new ClusteringCoefficientImpl();
		
		assertEquals(1.0, cCoefficient.getClusteringCoefficient(network, node1, false),0.01);
		assertEquals(0.66, cCoefficient.getClusteringCoefficient(network, node2, false),0.01);
		assertEquals(0.66, cCoefficient.getClusteringCoefficient(network, node3, false),0.01);
		assertEquals(1.0, cCoefficient.getClusteringCoefficient(network, node4, false),0.01);
		
		CyNode node_1 = network1.addNode();
		CyNode node_2 = network1.addNode();
		CyNode node_3 = network1.addNode();
		CyNode node_4 = network1.addNode();
		
		CyEdge edge_1 = network1.addEdge(node_1, node_2, true);
		CyEdge edge_2 = network1.addEdge(node_1, node_3, true);
		CyEdge edge_3 = network1.addEdge(node_4, node_2, true);
		CyEdge edge_4 = network1.addEdge(node_3, node_4, true);
		CyEdge edge_5 = network1.addEdge(node_2, node_3, true);
		
		assertEquals(0.5, cCoefficient.getClusteringCoefficient(network1, node_1, true),0.01);
		assertEquals(0.33, cCoefficient.getClusteringCoefficient(network1, node_2, true),0.01);
		assertEquals(0.33, cCoefficient.getClusteringCoefficient(network1, node_3, true),0.01);
		assertEquals(0.5, cCoefficient.getClusteringCoefficient(network1, node_4, true),0.01);
		
	}
}
