/**
 * 
 */
package org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl;

import java.util.IdentityHashMap;
import java.util.Map;

import org.cytoscape.graphAlgorithms.internal.cyGraphAlgo.BellmanFordShortestPathFinder;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 * 
 */
public class BellmanFordShortestPathFinderImpl implements BellmanFordShortestPathFinder{

	private Map<CyNode, MetaNode> nodeToMetaNodeMap;

	private boolean negativeCycle = true;

	public BellmanFordStats findPath(CyNetwork network, CyNode source, boolean directed,
			Map<CyEdge, Double> weightMap) {

		nodeToMetaNodeMap = new IdentityHashMap<CyNode, MetaNode>();

		for (CyNode node : network.getNodeList()) {

			MetaNode metaNode = new MetaNode(node, Double.POSITIVE_INFINITY,
					null);
			nodeToMetaNodeMap.put(node, metaNode);

		}

		nodeToMetaNodeMap.get(source).setDistance(0.0);

		for (int i = 0; i < network.getNodeCount() - 1; i++) {

			for (CyEdge edge : network.getEdgeList()) {

				MetaNode sourceMetaNode = nodeToMetaNodeMap.get(edge
						.getSource());
				MetaNode targetMetaNode = nodeToMetaNodeMap.get(edge
						.getTarget());

				if (targetMetaNode.getDistance() > sourceMetaNode.getDistance()
						+ weightMap.get(edge)) {
					targetMetaNode.setDistance(sourceMetaNode.getDistance()
							+ weightMap.get(edge));
					targetMetaNode.setPredecessor(sourceMetaNode.getNode());
				}
			}
		}

		for (CyEdge edge : network.getEdgeList()) {

			MetaNode sourceMetaNode = nodeToMetaNodeMap.get(edge.getSource());
			MetaNode targetMetaNode = nodeToMetaNodeMap.get(edge.getTarget());
			if (targetMetaNode.getDistance() > sourceMetaNode.getDistance()
					+ weightMap.get(edge))
				negativeCycle = false;
		}
		
		return new BellmanFordStats(source, nodeToMetaNodeMap, this.negativeCycle);
	}
}
