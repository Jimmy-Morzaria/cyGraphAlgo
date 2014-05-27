/**
 * 
 */
package org.cytoscape.graphAlgorithms.internal.cyGraphAlgo;

import org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl.DfsStat;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface DepthFirstSearcher {

	public DfsStat search(CyNetwork network, CyNode source, boolean directed, Callback callback);
	
}
