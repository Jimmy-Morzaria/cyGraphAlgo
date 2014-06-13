/**
 * 
 */
package org.cytoscape.graph.algorithms.cyGraphAlgoImpl;

import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.cytoscape.graph.algorithms.cyGraphAlgo.PrimMST;
import org.cytoscape.graph.algorithms.cyGraphAlgo.WeightFunction;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 * 
 */
public class PrimMSTImpl implements PrimMST{

	private double weight;

	private Queue<CyEdge> mst;

	private boolean marked[];

	private Queue<MetaEdge> pq;

	private Map<CyNode, Integer> nodeToIndexMap;

	private WeightFunction function;
	
	public PrimMSTStatsImpl findTree(CyNetwork network, WeightFunction function) {

		this.function = function;
		
		int nodeCount = network.getNodeCount();

		int edgeCount = network.getEdgeCount();

		mst = new LinkedList<CyEdge>();

		pq = new PriorityQueue<MetaEdge>(edgeCount, new CyEdgeComparator());

		marked = new boolean[nodeCount];

		nodeToIndexMap = new IdentityHashMap<CyNode, Integer>();

		int i = 0;
		for (CyNode node : network.getNodeList()) {

			nodeToIndexMap.put(node, i);
			i++;
		}

		for (CyNode node : network.getNodeList()) {

			int nodeIndex = nodeToIndexMap.get(node);

			if (!marked[nodeIndex]) {

				scan(network, node, nodeIndex);

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
						scan(network, metaEdge.getCyEdge().getSource(), v);
					if(!marked[w])
						scan(network, metaEdge.getCyEdge().getTarget(), w);
				}

			}
		}
		
		return new PrimMSTStatsImpl(mst, weight);
	}

	private void scan(CyNetwork network, CyNode node, int nodeIndex) {

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
