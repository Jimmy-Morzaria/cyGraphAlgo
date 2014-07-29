/**
 * 
 */
package org.cytoscape.graph.algorithms.api;

import org.cytoscape.model.CyNetwork;

/**
 * @author Jimmy
 *
 */
public interface HITS {

	public HITSResults compute(CyNetwork network, boolean directed);
}
