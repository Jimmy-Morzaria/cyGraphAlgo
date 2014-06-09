/**
 * 
 */
package org.cytoscape.graphAlgorithms.internal.cyGraphAlgo;

import org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl.DfsStats;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface DepthFirstSearcher {

	public DfsStats search(CyNetwork network, CyNode source, boolean directed, Callback callback);
	
}
