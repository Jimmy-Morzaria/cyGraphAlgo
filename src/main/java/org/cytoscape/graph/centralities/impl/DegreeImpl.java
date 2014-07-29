/**
 * 
 */
package org.cytoscape.graph.centralities.impl;

import org.cytoscape.graph.centralities.api.Degree;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public class DegreeImpl implements Degree{

	public int getDegree(CyNetwork network, CyNode node){
		return network.getNeighborList(node, CyEdge.Type.ANY).size();
	}
	
	public int getIndegree(CyNetwork network, CyNode node){
		return network.getNeighborList(node, CyEdge.Type.INCOMING).size();
	}
	
	public int getOutdegree(CyNetwork network, CyNode node){
		return network.getNeighborList(node, CyEdge.Type.OUTGOING).size();
	}
	
}
