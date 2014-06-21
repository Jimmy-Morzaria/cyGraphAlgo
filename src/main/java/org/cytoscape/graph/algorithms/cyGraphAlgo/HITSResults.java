/**
 * 
 */
package org.cytoscape.graph.algorithms.cyGraphAlgo;

import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface HITSResults {

	public double getHubValue(CyNode node);
	
	public double getAuthorityValue(CyNode node);
}
