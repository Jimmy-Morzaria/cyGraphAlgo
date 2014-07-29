/**
 * 
 */
package org.cytoscape.graph.centralities.impl;

import java.util.IdentityHashMap;
import java.util.Map;

import org.cytoscape.graph.algorithms.api.DijkstraShortestPathFinder;
import org.cytoscape.graph.algorithms.api.DijkstraStats;
import org.cytoscape.graph.algorithms.api.WeightFunction;
import org.cytoscape.graph.algorithms.impl.DijkstraShortestPathFinderImpl;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public class DiameterImpl {

	public double getDiameter(CyNetwork network, boolean directed, WeightFunction function){
		
		Map<CyNode, DijkstraStats> nodeDistanceMap = new IdentityHashMap<CyNode, DijkstraStats>();
		DijkstraShortestPathFinder dPathFinder = new DijkstraShortestPathFinderImpl();
		
		double diameter = 0.0;
		double eccentricity = 0.0;
		for(CyNode tempNode : network.getNodeList()){
			
			DijkstraStats tempStats = dPathFinder.findPath(network, tempNode, directed, function);
			nodeDistanceMap.put(tempNode, tempStats);
			eccentricity = tempStats.getEccentricity();
			if(eccentricity > diameter){
				diameter = eccentricity;
			}
		}
		return diameter;
	}
}
