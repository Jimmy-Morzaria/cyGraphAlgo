/**
 * 
 */
package org.cytoscape.graph.centralities.impl;

import org.cytoscape.graph.algorithms.api.DijkstraShortestPathFinder;
import org.cytoscape.graph.algorithms.api.DijkstraStats;
import org.cytoscape.graph.algorithms.api.WeightFunction;
import org.cytoscape.graph.algorithms.impl.DijkstraShortestPathFinderImpl;
import org.cytoscape.graph.centralities.api.Eccentricity;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public class EccentricityImpl implements Eccentricity{

	public double getEccentricity(CyNetwork network, CyNode node, boolean directed, WeightFunction function){
		
		DijkstraShortestPathFinder dPathFinder = new DijkstraShortestPathFinderImpl();
		DijkstraStats dStats = dPathFinder.findPath(network, node, directed, function);
		double max = dStats.getDistanceTo(node);
		for(CyNode tempNode : network.getNodeList()){
			double temp = dStats.getDistanceTo(tempNode);
			if(temp > max){
				max = temp;
			}
		}
		return 1/max;
	}
}
