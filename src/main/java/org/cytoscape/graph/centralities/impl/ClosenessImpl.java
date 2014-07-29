/**
 * 
 */
package org.cytoscape.graph.centralities.impl;

import org.cytoscape.graph.algorithms.api.DijkstraShortestPathFinder;
import org.cytoscape.graph.algorithms.api.DijkstraStats;
import org.cytoscape.graph.algorithms.api.WeightFunction;
import org.cytoscape.graph.algorithms.impl.DijkstraShortestPathFinderImpl;
import org.cytoscape.graph.centralities.api.Closeness;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public class ClosenessImpl implements Closeness{

	public double getCloseness(CyNetwork network, CyNode node, boolean directed, WeightFunction function){
		
		DijkstraShortestPathFinder dPathFinder = new DijkstraShortestPathFinderImpl();
		DijkstraStats dStats = dPathFinder.findPath(network, node, directed, function);
		
		return getCloseness(network, dStats);
	}
	
	public double getCloseness(CyNetwork network, DijkstraStats dStats){
		
		double sum = 0.0;
		for(CyNode tempNode : network.getNodeList()){
			sum += dStats.getDistanceTo(tempNode);
		}
		return (network.getNodeCount()-1)/sum;
	}
}
