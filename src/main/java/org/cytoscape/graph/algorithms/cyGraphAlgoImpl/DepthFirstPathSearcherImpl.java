/**
 * 
 */
package org.cytoscape.graph.algorithms.cyGraphAlgoImpl;

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
 * Ref: Section 4.4 of Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne.
 * 
 * @author Jimmy
 * 
 */
public class DepthFirstPathSearcherImpl implements DepthFirstSearcher {

	private boolean marked[];

	private int edgeTo[];

	private Map<CyNode, Integer> nodeToIndexMap;

	private Map<Integer, CyNode> indexToNodeMap;

	private boolean directed;

	public DfsStatsImpl search(CyNetwork network, CyNode source, boolean directed,
			Callback callback) {

		this.directed = directed;

		nodeToIndexMap = new IdentityHashMap<CyNode, Integer>();
		indexToNodeMap = new HashMap<Integer, CyNode>();

		int nodeCount = network.getNodeCount();

		marked = new boolean[nodeCount];
		edgeTo = new int[nodeCount];

		int i = 0;
		for (CyNode node : network.getNodeList()) {
			indexToNodeMap.put(i, node);
			nodeToIndexMap.put(node, i);
			i++;
		}

		dfs(network, source, callback);

		return new DfsStatsImpl(source, network, marked, edgeTo, nodeToIndexMap,
				indexToNodeMap);
	}

	private void dfs(CyNetwork network, CyNode source, Callback callback) {

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
				dfs(network, node, callback);
			}
		}
	}
}
