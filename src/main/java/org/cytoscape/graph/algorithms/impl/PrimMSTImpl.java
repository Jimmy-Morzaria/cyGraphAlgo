/**
 * 
 */
package org.cytoscape.graph.algorithms.impl;

import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.cytoscape.graph.algorithms.api.PrimMST;
import org.cytoscape.graph.algorithms.api.WeightFunction;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * Ref: Section 4.4 of Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne.
 * 
 * @author Jimmy
 * 
 */
public class PrimMSTImpl implements PrimMST{

	public PrimMSTStatsImpl findTree(CyNetwork network, WeightFunction function) {

		double weight = 0.0;
		
		int nodeCount = network.getNodeCount();

		int edgeCount = network.getEdgeCount();

		Queue<CyEdge> mst = new LinkedList<CyEdge>();

		Queue<MetaEdge> pq = new PriorityQueue<MetaEdge>(edgeCount, CyEdgeComparator.getInstance());

		boolean marked[] = new boolean[nodeCount];

		Map<CyNode, Integer> nodeToIndexMap = new IdentityHashMap<CyNode, Integer>();

		int i = 0;
		List<CyNode> nodeList = network.getNodeList();
		
		for (CyNode node : nodeList) {

			nodeToIndexMap.put(node, i);
			i++;
		}

		for (CyNode node : nodeList) {

			int nodeIndex = nodeToIndexMap.get(node);

			if (!marked[nodeIndex]) {

				scan(network, node, nodeIndex, nodeToIndexMap, marked, pq, function);

				while (!pq.isEmpty()) {

					MetaEdge metaEdge = pq.poll();

					int v = nodeToIndexMap
							.get(metaEdge.getCyEdge().getSource());
					int w = nodeToIndexMap
							.get(metaEdge.getCyEdge().getTarget());

					if (marked[v] && marked[w])
						continue;

					mst.add(metaEdge.getCyEdge());

					weight += metaEdge.getWeight();
					
					if(!marked[v])
						scan(network, metaEdge.getCyEdge().getSource(), v, nodeToIndexMap, marked, pq, function);
					if(!marked[w])
						scan(network, metaEdge.getCyEdge().getTarget(), w, nodeToIndexMap, marked, pq, function);
				}
			}
		}
		return new PrimMSTStatsImpl(mst, weight);
	}

	private void scan(CyNetwork network, CyNode node, int nodeIndex, Map<CyNode, Integer> nodeToIndexMap, boolean marked[], Queue<MetaEdge> pq, WeightFunction function) {

		marked[nodeIndex] = true;
		for (CyEdge edge : network.getAdjacentEdgeList(node, CyEdge.Type.ANY)) {

			CyNode neighbor;
			if (edge.getSource() == node) {

				neighbor = edge.getTarget();
				
			} else {

				neighbor = edge.getSource();
			}

			int neighborIndex = nodeToIndexMap.get(neighbor);
			if (!marked[neighborIndex]) {

				pq.add(new MetaEdge(edge, function.getWeight(edge)));
			}
		}
	}
}
