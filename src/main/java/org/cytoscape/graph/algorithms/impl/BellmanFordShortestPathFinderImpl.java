/**
 * 
 */
package org.cytoscape.graph.algorithms.impl;

import java.util.IdentityHashMap;
import java.util.Map;

import org.cytoscape.graph.algorithms.api.BellmanFordShortestPathFinder;
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
public class BellmanFordShortestPathFinderImpl implements
		BellmanFordShortestPathFinder {

	public BellmanFordStatsImpl findPath(CyNetwork network, CyNode source,
			boolean directed, WeightFunction function) {

		Map<CyNode, MetaNode> nodeToMetaNodeMap = new IdentityHashMap<CyNode, MetaNode>();

		boolean negativeCycle = true;

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
						+ function.getWeight(edge)) {
					targetMetaNode.setDistance(sourceMetaNode.getDistance()
							+ function.getWeight(edge));
					targetMetaNode.setPredecessor(sourceMetaNode.getNode());
				}
			}
		}

		for (CyEdge edge : network.getEdgeList()) {

			MetaNode sourceMetaNode = nodeToMetaNodeMap.get(edge.getSource());
			MetaNode targetMetaNode = nodeToMetaNodeMap.get(edge.getTarget());
			if (targetMetaNode.getDistance() > sourceMetaNode.getDistance()
					+ function.getWeight(edge))
				negativeCycle = false;
		}

		return new BellmanFordStatsImpl(source, nodeToMetaNodeMap,
				negativeCycle);
	}
}
