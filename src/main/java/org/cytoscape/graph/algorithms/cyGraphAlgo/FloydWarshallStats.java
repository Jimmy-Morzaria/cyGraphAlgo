/**
 * 
 */
package org.cytoscape.graph.algorithms.cyGraphAlgo;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface FloydWarshallStats {

	public boolean hasNegativeCycle();
	
	public boolean hasPath(CyNode source, CyNode target);
	
	public double getDistance(CyNode source, CyNode target);
	
	public Iterable<CyEdge> getPath(CyNode source, CyNode target);
	
	public Iterable<CyEdge> getNegativeCycle();
}
