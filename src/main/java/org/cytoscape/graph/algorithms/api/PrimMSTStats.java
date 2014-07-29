/**
 * 
 */
package org.cytoscape.graph.algorithms.api;

import org.cytoscape.model.CyEdge;

/**
 * @author Jimmy
 *
 */
public interface PrimMSTStats {

	public double getWeight();
	
	public Iterable<CyEdge> getMST();
}
