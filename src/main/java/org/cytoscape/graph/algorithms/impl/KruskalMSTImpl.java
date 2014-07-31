/**
 * 
 */
package org.cytoscape.graph.algorithms.impl;

import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.cytoscape.graph.algorithms.api.KruskalMST;
import org.cytoscape.graph.algorithms.api.WeightFunction;
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
public class KruskalMSTImpl implements KruskalMST {

	public KruskalMSTStatsImpl findTree(CyNetwork network,
			WeightFunction function) {

		double weight = 0;
		Queue<MetaEdge> pq = new PriorityQueue<MetaEdge>(
				network.getEdgeCount(), CyEdgeComparator.getInstance());

		Map<CyEdge, MetaEdge> edgeToMetaEdgeMap = new IdentityHashMap<CyEdge, MetaEdge>();

		Map<CyNode, Integer> nodeToIndexMap = new IdentityHashMap<CyNode, Integer>();

		Queue<CyEdge> mst = new LinkedList<CyEdge>();

		int k = 0;
		for (CyNode node : network.getNodeList()) {

			nodeToIndexMap.put(node, k);
			k++;
		}
		for (CyEdge edge : network.getEdgeList()) {

			MetaEdge metaEdge = new MetaEdge(edge, function.getWeight(edge));
			edgeToMetaEdgeMap.put(edge, metaEdge);
			pq.add(metaEdge);
		}

		UnionFind uf = new UnionFind(network.getNodeCount());

		while (!pq.isEmpty() && mst.size() < network.getNodeCount() - 1) {

			MetaEdge metaEdge = pq.poll();
			int i = nodeToIndexMap.get(metaEdge.getCyEdge().getSource());
			int j = nodeToIndexMap.get(metaEdge.getCyEdge().getTarget());

			if (!uf.isConnected(i, j)) {
				uf.union(i, j);
				mst.add(metaEdge.getCyEdge());
				weight += metaEdge.getWeight();
			}
		}

		return new KruskalMSTStatsImpl(mst, weight);
	}
}
