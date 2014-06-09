/**
 * 
 */
package org.cytoscape.graphAlgorithms.internal.cyGraphAlgo;

import java.util.Map;

import org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl.DijkstraStats;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 * 
 */
public interface DijkstraShortestPathFinder {

	public DijkstraStats findPath(CyNetwork network, CyNode source,
			boolean directed, Map<CyEdge, Double> weightMap);
}
