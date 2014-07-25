/**
 * 
 */
package org.cytoscape.graph.algorithms.impl.cyGraphAlgo;

import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.cytoscape.graph.algorithms.cyGraphAlgo.DijkstraShortestPathFinder;
import org.cytoscape.graph.algorithms.cyGraphAlgo.WeightFunction;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 * 
 */
public class DijkstraShortestPathFinderImpl implements
		DijkstraShortestPathFinder {

	public DijkstraStatsImpl findPath(CyNetwork network, CyNode source,
			boolean directed, WeightFunction function) {

		Map<CyNode, MetaNode> nodeToMetaNodeMap = new IdentityHashMap<CyNode, MetaNode>();

		Queue<MetaNode> pq = new PriorityQueue<MetaNode>(
				network.getNodeCount(), new DijkstraComparator());

		for (CyNode node : network.getNodeList()) {

			MetaNode metaNode = new MetaNode(node, Double.POSITIVE_INFINITY,
					null);
			nodeToMetaNodeMap.put(node, metaNode);

		}

		nodeToMetaNodeMap.get(source).setDistance(0.0);
		pq.add(nodeToMetaNodeMap.get(source));

		while (!pq.isEmpty()) {
			MetaNode metaNode = pq.remove();
			List<CyEdge> edgeList = network.getAdjacentEdgeList(
					metaNode.getNode(), CyEdge.Type.OUTGOING);
			if (directed) {
				edgeList = network.getAdjacentEdgeList(metaNode.getNode(),
						CyEdge.Type.OUTGOING);
			} else {
				edgeList = network.getAdjacentEdgeList(metaNode.getNode(),
						CyEdge.Type.ANY);
			}
			for (CyEdge edge : edgeList) {
				MetaNode neighborMetaNode;
				if(edge.getTarget() == metaNode.getNode()){
					neighborMetaNode = nodeToMetaNodeMap.get(edge
							.getSource());
				}
				else{
					neighborMetaNode = nodeToMetaNodeMap.get(edge
							.getTarget());
				}
				if (neighborMetaNode.getDistance() > metaNode.getDistance()
						+ function.getWeight(edge)) {
					neighborMetaNode.setDistance(metaNode.getDistance()
							+ function.getWeight(edge));
					neighborMetaNode.setPredecessor(metaNode.getNode());
					pq.add(neighborMetaNode);
				}
			}

		}
		return new DijkstraStatsImpl(source, nodeToMetaNodeMap);

	}

}

class MetaNode {

	private CyNode node;

	private double distance;

	private CyNode predecessor;

	public MetaNode(CyNode node, double distance, CyNode predecessor) {

		this.node = node;
		this.distance = distance;
		this.predecessor = predecessor;
	}

	public void setPredecessor(CyNode node) {
		// TODO Auto-generated method stub
		this.predecessor = node;
	}

	public CyNode getPredecessor() {

		return this.predecessor;
	}

	public double getDistance() {
		// TODO Auto-generated method stub
		return this.distance;
	}

	public CyNode getNode() {
		// TODO Auto-generated method stub
		return this.node;
	}

	public void setDistance(double distance) {
		// TODO Auto-generated method stub
		this.distance = distance;
	}
}

class DijkstraComparator implements Comparator<MetaNode> {

	@Override
	public int compare(MetaNode o1, MetaNode o2) {
		// TODO Auto-generated method stub
		return o1.getDistance() > o2.getDistance() ? 1 : o1.getDistance() == o2
				.getDistance() ? 0 : -1;
	}

}