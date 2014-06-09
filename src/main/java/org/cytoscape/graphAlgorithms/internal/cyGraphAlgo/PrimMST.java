/**
 * 
 */
package org.cytoscape.graphAlgorithms.internal.cyGraphAlgo;

import java.util.Map;

import org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl.PrimMSTStats;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;

/**
 * @author Jimmy
 *
 */
public interface PrimMST {

	public PrimMSTStats findTree(CyNetwork network, Map<CyEdge, Double> weightMap);
	
}
