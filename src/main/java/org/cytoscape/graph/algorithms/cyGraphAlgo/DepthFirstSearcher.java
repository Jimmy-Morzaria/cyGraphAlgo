/**
 * 
 */
package org.cytoscape.graph.algorithms.cyGraphAlgo;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface DepthFirstSearcher {

	public DfsStats search(CyNetwork network, CyNode source, boolean directed, Callback callback);
	
}
