/**
 * 
 */
package org.cytoscape.graph.centralities.api;

import java.util.Map;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface NetworkStatsResults {

	public Map<CyNode, Double> getBetweennessMap();
	
	public Map<CyNode, Double> getCentroidMap();
	
	public Map<CyNode, Double> getClosenessMap();
	
	public Map<CyNode, Double> getClusteringCoefficientMap();
	
	public double getDiameter();
	
	public Map<CyNode, Double> getEccentricityMap();
	
	public Map<CyEdge, Double> getEdgeBetweennessMap();
	
	public Map<CyNode, Double> getEigenvectorMap();
	
	public Map<CyNode, Double> getReadialityMap();
	
	public Map<CyNode, Double> getStressMap();
	
}
