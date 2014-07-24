/**
 * 
 */
package org.cytoscape.graph.algorithms.impl.cyGraphAlgo;

import java.util.IdentityHashMap;
import java.util.Map;

import org.cytoscape.graph.algorithms.cyGraphAlgo.FloydWarshallShortestPathFinder;
import org.cytoscape.graph.algorithms.cyGraphAlgo.FloydWarshallStats;
import org.cytoscape.graph.algorithms.cyGraphAlgo.WeightFunction;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 * 
 */
public class FloydWarshallShortestPathFinderImpl implements
		FloydWarshallShortestPathFinder {

	@Override
	public FloydWarshallStats findPath(CyNetwork network, CyNode source,
			boolean directed, WeightFunction function) {
		// TODO Auto-generated method stub

		boolean negativeCycle = false;
		
		int nodeCount = network.getNodeCount();

		double distTo[][] = new double[nodeCount][nodeCount];
		CyEdge edgeTo[][] = new CyEdge[nodeCount][nodeCount];

		Map<CyNode, Integer> nodeToIndexMap = new IdentityHashMap<CyNode, Integer>();

		int i = 0;

		for (CyNode node : network.getNodeList()) {
			nodeToIndexMap.put(node, i++);
		}

		for (i = 0; i < nodeCount; i++) {

			for (int j = 0; j < nodeCount; j++) {
				distTo[i][j] = Double.POSITIVE_INFINITY;
			}

		}

		for (CyNode node : network.getNodeList()) {

			int nodeIndex = nodeToIndexMap.get(node);
			for (CyEdge edge : network.getAdjacentEdgeList(node,
					CyEdge.Type.ANY)) {

				int sourceIndex = nodeToIndexMap.get(edge.getSource());
				int targetIndex = nodeToIndexMap.get(edge.getTarget());
				distTo[sourceIndex][targetIndex] = function.getWeight(edge);
				edgeTo[sourceIndex][targetIndex] = edge;

			}

			if (distTo[nodeIndex][nodeIndex] >= 0.0) {
				distTo[nodeIndex][nodeIndex] = 0.0;
				edgeTo[nodeIndex][nodeIndex] = null;
			}
		}

		for (CyNode node : network.getNodeList()) {

			int nodeIndex = nodeToIndexMap.get(node);

			for (CyNode node1 : network.getNodeList()) {

				int node1Index = nodeToIndexMap.get(node1);
				if (edgeTo[node1Index][nodeIndex] == null)
					continue;

				for (CyNode node2 : network.getNodeList()) {

					int node2Index = nodeToIndexMap.get(node2);
					if (distTo[node1Index][node2Index] > distTo[node1Index][nodeIndex]
							+ distTo[nodeIndex][node2Index]) {

						distTo[node1Index][node2Index] = distTo[node1Index][nodeIndex]
								+ distTo[nodeIndex][node2Index];
						edgeTo[node1Index][node2Index] = edgeTo[nodeIndex][node2Index];
					}
					
					if(distTo[node1Index][node1Index] < 0.0){
						negativeCycle = true;
					}
				}
			}
		}
		return new FloydWarshallStatsImpl(nodeToIndexMap, distTo, edgeTo, negativeCycle);
	}

}
