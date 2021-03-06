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
import org.cytoscape.graph.centralities.api.Radiality;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public class RadialityImpl implements Radiality{

	public double getRadiality(CyNetwork network, CyNode node, boolean directed, WeightFunction function){
		
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
		
		return getRadiality(network, diameter, nodeDistanceMap.get(node));
	}
	
	public double getRadiality(CyNetwork network, double diameter, DijkstraStats dStats){
		
		
		double radiality = 0.0;
		for(CyNode tempNode : network.getNodeList()){
			if(dStats.getSource() != tempNode)
				radiality = radiality + (diameter + 1 - dStats.getDistanceTo(tempNode))/diameter;
		}
		return radiality/(network.getNodeCount()-1);
	}
}
