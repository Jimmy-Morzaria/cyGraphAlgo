/**
 * 
 */
package org.cytoscape.cyGraphAlgo;

import org.cytoscape.cyGraphAlgoImpl.DfsStat;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface DepthFirstSearcher {

	public DfsStat search(CyNetwork network, CyNode source, boolean directed, Callback callback);
	
}
