/**
 * 
 */
package org.cytoscape.graph.algorithms.cyGraphAlgo;

import org.cytoscape.model.CyEdge;

/**
 * @author Jimmy
 *
 */
public interface KruskalMSTStats {

	public double getWeight();
	
	public Iterable<CyEdge> getMST();
}
