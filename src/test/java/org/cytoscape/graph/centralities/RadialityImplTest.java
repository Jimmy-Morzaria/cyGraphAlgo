/**
 * 
 */
package org.cytoscape.graph.centralities;

import static org.junit.Assert.assertEquals;

import org.cytoscape.graph.algorithms.api.WeightFunction;
import org.cytoscape.graph.centralities.impl.RadialityImpl;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.NetworkTestSupport;
import org.junit.Test;

/**
 * @author Jimmy
 *
 */
public class RadialityImplTest {

	private CyNetwork network;
	
	@Test
	public void testGetRadiality(){
		
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
		
				
		RadialityImpl radiality = new RadialityImpl();
		assertEquals(0.66, radiality.getRadiality(network, node1, false, new WeightFunction() {
			public double getWeight(CyEdge edge) {
				return network.getRow(edge).get("Weight", Double.class);
			}
		}),0.01);
		assertEquals(0.916, radiality.getRadiality(network, node2, false, new WeightFunction() {
			public double getWeight(CyEdge edge) {
				return network.getRow(edge).get("Weight", Double.class);
			}
		}),0.01);
		assertEquals(0.833, radiality.getRadiality(network, node3, false, new WeightFunction() {
			public double getWeight(CyEdge edge) {
				return network.getRow(edge).get("Weight", Double.class);
			}
		}),0.01);
		assertEquals(0.833, radiality.getRadiality(network, node4, false, new WeightFunction() {
			public double getWeight(CyEdge edge) {
				return network.getRow(edge).get("Weight", Double.class);
			}
		}),0.01);
		assertEquals(0.75, radiality.getRadiality(network, node5, false, new WeightFunction() {
			public double getWeight(CyEdge edge) {
				return network.getRow(edge).get("Weight", Double.class);
			}
		}),0.01);
	}
}
