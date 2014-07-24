/**
 * 
 */
package org.cytoscape.graph.centralities;

import static org.junit.Assert.*;

import org.cytoscape.graph.algorithms.cyGraphAlgo.WeightFunction;
import org.cytoscape.graph.impl.centralities.ClosenessImpl;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.NetworkTestSupport;
import org.junit.Test;

/**
 * @author Jimmy
 *
 */
public class ClosenessImplTest {

	private CyNetwork network;
	
	@Test
	public void testGetCloseness(){
		
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
		
		network.getDefaultEdgeTable().createColumn("Weight", Double.class,
				false);
		network.getRow(edge1).set("Weight", 1.0);
		network.getRow(edge2).set("Weight", 1.0);
		network.getRow(edge3).set("Weight", 1.0);
		network.getRow(edge4).set("Weight", 1.0);
		network.getRow(edge5).set("Weight", 1.0);
		
				
		ClosenessImpl closeness = new ClosenessImpl();
		assertEquals(0.5, closeness.getCloseness(network, node1, false, new WeightFunction() {
			public double getWeight(CyEdge edge) {
				return network.getRow(edge).get("Weight", Double.class);
			}
		}),0.01);
		assertEquals(0.8, closeness.getCloseness(network, node2, false, new WeightFunction() {
			public double getWeight(CyEdge edge) {
				return network.getRow(edge).get("Weight", Double.class);
			}
		}),0.01);
		assertEquals(0.667, closeness.getCloseness(network, node3, false, new WeightFunction() {
			public double getWeight(CyEdge edge) {
				return network.getRow(edge).get("Weight", Double.class);
			}
		}),0.01);
		assertEquals(0.667, closeness.getCloseness(network, node4, false, new WeightFunction() {
			public double getWeight(CyEdge edge) {
				return network.getRow(edge).get("Weight", Double.class);
			}
		}),0.01);
		assertEquals(0.5714, closeness.getCloseness(network, node5, false, new WeightFunction() {
			public double getWeight(CyEdge edge) {
				return network.getRow(edge).get("Weight", Double.class);
			}
		}),0.01);
	}
}
