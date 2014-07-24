/**
 * 
 */
package org.cytoscape.graph.algorithms.impl.cyGraphAlgo;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.cytoscape.graph.algorithms.cyGraphAlgo.Callback;
import org.cytoscape.graph.algorithms.cyGraphAlgo.DepthFirstSearcher;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * Ref: Section 4.4 of Algorithms, 4th Edition by Robert Sedgewick and Kevin
 * Wayne.
 * 
 * @author Jimmy
 * 
 */
public class DepthFirstPathSearcherImpl implements DepthFirstSearcher {

	public DfsStatsImpl search(CyNetwork network, CyNode source,
			boolean directed, Callback callback) {

		Map<CyNode, Integer> nodeToIndexMap = new IdentityHashMap<CyNode, Integer>();
		Map<Integer, CyNode> indexToNodeMap = new HashMap<Integer, CyNode>();

		int nodeCount = network.getNodeCount();

		boolean marked[] = new boolean[nodeCount];
		int edgeTo[] = new int[nodeCount];

		int i = 0;
		for (CyNode node : network.getNodeList()) {
			indexToNodeMap.put(i, node);
			nodeToIndexMap.put(node, i);
			i++;
		}

		dfs(network, source, callback, nodeToIndexMap, marked, edgeTo, directed);

		return new DfsStatsImpl(source, network, marked, edgeTo,
				nodeToIndexMap, indexToNodeMap);
	}

	private void dfs(CyNetwork network, CyNode source, Callback callback,
			Map<CyNode, Integer> nodeToIndexMap, boolean marked[],
			int edgeTo[], boolean directed) {

		int sourceIndex = nodeToIndexMap.get(source);

		marked[sourceIndex] = true;

		List<CyNode> nodeList;

		if (directed) {

			nodeList = network.getNeighborList(source, CyEdge.Type.OUTGOING);
		} else {

			nodeList = network.getNeighborList(source, CyEdge.Type.ANY);
		}

		for (CyNode node : nodeList) {

			int neighborIndex = nodeToIndexMap.get(node);
			if (!marked[neighborIndex]) {

				edgeTo[neighborIndex] = sourceIndex;
				// TODO include callback
				dfs(network, node, callback, nodeToIndexMap, marked, edgeTo,
						directed);
			}
		}
	}
}
