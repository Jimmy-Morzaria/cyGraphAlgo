/**
 * 
 */
package org.cytoscape.graph.impl.centralities;

import java.util.List;

import org.cytoscape.graph.api.centralities.ClusteringCoefficient;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public class ClusteringCoefficientImpl implements ClusteringCoefficient{

	public double getClusteringCoefficient(CyNetwork network, CyNode node, boolean directed){
		
		List<CyNode> neighborList = network.getNeighborList(node, CyEdge.Type.ANY);
		int edgeCount = 0;
		for(CyNode neighbor1 : neighborList){
			
			for(CyNode neighbor2 : neighborList){
				if(neighbor1 == neighbor2)
					continue;
				else if(network.getConnectingEdgeList(neighbor1, neighbor2, CyEdge.Type.ANY).size()!=0){
					edgeCount++;
				}
			}
		}
		return edgeCount/(neighborList.size()*(neighborList.size()-1));
	}
}
