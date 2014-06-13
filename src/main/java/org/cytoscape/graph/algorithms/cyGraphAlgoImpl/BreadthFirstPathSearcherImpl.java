/**
 * 
 */
package org.cytoscape.graph.algorithms.cyGraphAlgoImpl;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.cytoscape.graph.algorithms.cyGraphAlgo.Callback;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 * 
 */
public class BreadthFirstPathSearcherImpl {

	private static final int INFINITY = Integer.MAX_VALUE;

	private boolean marked[];

	private int edgeTo[];

	private int distTo[];

	private Map<CyNode, Integer> nodeToIndexMap;

	private Map<Integer, CyNode> indexToNodeMap;

	@SuppressWarnings("unused")
	private boolean directed;

	// breadth-first search from a single source
	public BfsStatsImpl search(CyNetwork network, CyNode source, boolean directed,
			Callback callback) {

		this.directed = directed;

		nodeToIndexMap = new IdentityHashMap<CyNode, Integer>();
		indexToNodeMap = new HashMap<Integer, CyNode>();

		int nodeCount = network.getNodeCount();

		marked = new boolean[nodeCount];
		distTo = new int[nodeCount];
		edgeTo = new int[nodeCount];

		int i = 0;
		for (CyNode node : network.getNodeList()) {
			indexToNodeMap.put(i, node);
			nodeToIndexMap.put(node, i);
			distTo[i] = INFINITY;
			i++;
		}

		int index = nodeToIndexMap.get(source);
		distTo[index] = 0;
		marked[index] = true;

		Queue<CyNode> q = new LinkedList<CyNode>();

		q.add(source);

		while (!q.isEmpty()) {
			CyNode v = q.poll();

			List<CyNode> nodeList;
			if (directed) {
				nodeList = network.getNeighborList(v, CyEdge.Type.OUTGOING);
			} else {
				nodeList = network.getNeighborList(v, CyEdge.Type.ANY);
			}
			int time = 0;
			for (CyNode node : nodeList) {
				index = nodeToIndexMap.get(node);
				if (!marked[index]) {
					edgeTo[index] = nodeToIndexMap.get(v);
					distTo[index] = distTo[nodeToIndexMap.get(v)] + 1;
					marked[index] = true;
					if (callback != null) {
						if (callback.notify(node, ++time, distTo[index]))
							break;
					}
					q.add(node);
				}
			}
		}
		return new BfsStatsImpl(source, network, marked, distTo, edgeTo,
				nodeToIndexMap, indexToNodeMap);
	}

}
