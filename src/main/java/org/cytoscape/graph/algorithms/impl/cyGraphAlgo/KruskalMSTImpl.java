/**
 * 
 */
package org.cytoscape.graph.algorithms.impl.cyGraphAlgo;

import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.cytoscape.graph.algorithms.cyGraphAlgo.KruskalMST;
import org.cytoscape.graph.algorithms.cyGraphAlgo.WeightFunction;
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
				network.getEdgeCount(), new CyEdgeComparator());

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

class UnionFind {

	private int id[];

	private int count;

	private int rank[];

	public UnionFind(int n) {

		id = new int[n];
		rank = new int[n];
		count = n;

		for (int i = 0; i < count; i++) {

			id[i] = i;
			rank[i] = 0;
		}
	}

	public int find(int p) {

		if (p < 0 || p >= id.length)
			throw new IndexOutOfBoundsException();
		while (p != id[p]) {

			id[p] = id[id[p]];
			p = id[p];
		}

		return p;
	}

	public int count() {

		return this.count;

	}

	public boolean isConnected(int p, int q) {

		return find(p) == find(q);

	}

	public void union(int p, int q) {

		int i = find(p);
		int j = find(q);

		if (i == j)
			return;

		if (rank[i] < rank[j])
			id[i] = j;
		else if (rank[i] > rank[j])
			id[j] = i;
		else {

			id[j] = i;
			rank[i]++;
		}
		count--;
	}
}

class MetaEdge {

	private CyEdge edge;

	private double weight;

	public MetaEdge(CyEdge edge, double weight) {

		this.edge = edge;
		this.weight = weight;

	}

	public CyEdge getCyEdge() {

		return edge;

	}

	public double getWeight() {

		return weight;

	}

}

class CyEdgeComparator implements Comparator<MetaEdge> {

	@Override
	public int compare(MetaEdge o1, MetaEdge o2) {
		// TODO Auto-generated method stub
		return o1.getWeight() > o2.getWeight() ? 1 : o1.getWeight() == o2
				.getWeight() ? 0 : -1;
	}

}