/**
 * 
 */
package org.cytoscape.graph.algorithms.api;

import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface HITSResults {

	public double getHubValue(CyNode node);
	
	public double getAuthorityValue(CyNode node);
}
