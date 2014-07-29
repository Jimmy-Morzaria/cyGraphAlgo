/**
 * 
 */
package org.cytoscape.graph.centralities.api;

import org.cytoscape.graph.algorithms.api.WeightFunction;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface Eccentricity {

	public double getEccentricity(CyNetwork network, CyNode node, boolean directed, WeightFunction function);
}
