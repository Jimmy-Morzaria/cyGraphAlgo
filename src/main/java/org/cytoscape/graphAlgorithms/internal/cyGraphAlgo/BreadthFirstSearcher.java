/**
 * 
 */
package org.cytoscape.graphAlgorithms.internal.cyGraphAlgo;

import org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl.BfsStats;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface BreadthFirstSearcher {

		public BfsStats search(CyNetwork network, CyNode source, boolean directed, Callback callback);
			
}
