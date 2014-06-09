/**
 * 
 */
package org.cytoscape.graphAlgorithms.internal.cyGraphAlgo;

import java.util.Map;

import org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl.KruskalMSTStats;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;

/**
 * @author Jimmy
 *
 */
public interface KruskalMST {

	public KruskalMSTStats findTree(CyNetwork network, Map<CyEdge, Double> weightMap);
	
}
