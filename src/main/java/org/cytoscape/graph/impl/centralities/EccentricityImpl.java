/**
 * 
 */
package org.cytoscape.graph.impl.centralities;

import org.cytoscape.graph.algorithms.cyGraphAlgo.DijkstraShortestPathFinder;
import org.cytoscape.graph.algorithms.cyGraphAlgo.DijkstraStats;
import org.cytoscape.graph.algorithms.cyGraphAlgo.WeightFunction;
import org.cytoscape.graph.algorithms.impl.cyGraphAlgo.DijkstraShortestPathFinderImpl;
import org.cytoscape.graph.api.centralities.Eccentricity;
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
