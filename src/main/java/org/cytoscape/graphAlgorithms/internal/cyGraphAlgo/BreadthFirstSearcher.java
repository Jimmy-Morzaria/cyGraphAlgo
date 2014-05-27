/**
 * 
 */
package org.cytoscape.graphAlgorithms.internal.cyGraphAlgo;

import org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl.BfsStat;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface BreadthFirstSearcher {

		public BfsStat search(CyNetwork network, CyNode source, boolean directed, Callback callback);
			
}
