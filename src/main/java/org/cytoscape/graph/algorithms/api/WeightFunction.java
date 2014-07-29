/**
 * 
 */
package org.cytoscape.graph.algorithms.api;

import org.cytoscape.model.CyEdge;

/**
 * @author Jimmy
 *
 */
public interface WeightFunction {

	public double getWeight(CyEdge edge);
}
