/**
 * 
 */
package org.cytoscape.graph.algorithms.cyGraphAlgoImpl;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.cytoscape.graph.algorithms.cyGraphAlgo.Pagerank;
import org.cytoscape.graph.algorithms.cyGraphAlgo.PagerankResults;
import org.cytoscape.graph.algorithms.cyGraphAlgo.WeightFunction;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 * 
 */
public class PagerankImpl implements Pagerank {

	private Map<CyNode, Integer> nodeIndexMap;

	private double pageranks[];

	private double probability = 0.85;

	private double epsilon = 0.001;

	private boolean directed;

	private double weights[];

	public PagerankResults getPageranks(CyNetwork network,
			WeightFunction function, boolean directed) {
		
		return getPageranks(network, function, directed, this.probability, this.epsilon);
	}

	public PagerankResults getPageranks(CyNetwork network,
			WeightFunction function, boolean directed, double probability,
			double epsilon) {

		this.directed = directed;
		this.probability = probability;
		this.epsilon = epsilon;

		nodeIndexMap = new IdentityHashMap<CyNode, Integer>();

		int nodeIndex = 0;
		for (CyNode node : network.getNodeList()) {

			nodeIndexMap.put(node, nodeIndex++);
		}

		int nodeCount = network.getNodeCount();

		pageranks = new double[nodeCount];

		double[] temp = new double[nodeCount];
		weights = new double[nodeCount];

		// set initial values
		nodeIndex = 0;
		for (CyNode node : network.getNodeList()) {

			pageranks[nodeIndex] = 1.0f / nodeCount;
			if (function != null) {

				double sum = 0.0;
				List<CyEdge> edgeList;
				if (directed) {
					edgeList = network.getAdjacentEdgeList(node,
							CyEdge.Type.OUTGOING);
				} else {
					edgeList = network.getAdjacentEdgeList(node,
							CyEdge.Type.ANY);
				}

				for (CyEdge edge : edgeList) {

					sum += function.getWeight(edge);
				}
				weights[nodeIndex] = sum;
			}
			nodeIndex++;
		}

		while (true) {

			double r = 0.0;
			for (CyNode node : network.getNodeList()) {

				int s_index = nodeIndexMap.get(node);
				boolean out;
				if (directed) {
					out = network.getAdjacentEdgeList(node,
							CyEdge.Type.OUTGOING).size() > 0;
				} else {
					out = network.getAdjacentEdgeList(node, CyEdge.Type.ANY)
							.size() > 0;
				}

				if (out) {
					r += (1.0 - probability) * (pageranks[s_index] / nodeCount);
				} else {
					r += (pageranks[s_index] / nodeCount);
				}
			}

			boolean completed = true;

			for (CyNode node : network.getNodeList()) {

				int s_index = nodeIndexMap.get(node);
				temp[s_index] = updateValueForNode(network, node, r, function);

				if ((temp[s_index] - pageranks[s_index]) / pageranks[s_index] >= epsilon) {
					completed = false;
				}
			}
			pageranks = temp;
			temp = new double[nodeCount];
			if (completed) {
				break;
			}
		}

		return new PagerankResultsImpl(nodeIndexMap, pageranks);
	}

	private double updateValueForNode(CyNetwork network, CyNode node, double r,
			WeightFunction function) {

		double res = r;

		List<CyEdge> edgeList;
		if (directed) {
			edgeList = network.getAdjacentEdgeList(node, CyEdge.Type.INCOMING);
		} else {
			edgeList = network.getAdjacentEdgeList(node, CyEdge.Type.ANY);
		}

		for (CyEdge edge : edgeList) {

			CyNode neighbor;
			if (node == edge.getSource()) {
				neighbor = edge.getTarget();
			} else {
				neighbor = edge.getSource();
			}

			int neighborIndex = nodeIndexMap.get(neighbor);
			int normalize;

			if (directed) {
				normalize = network.getAdjacentEdgeList(neighbor,
						CyEdge.Type.OUTGOING).size();
			} else {
				normalize = network.getAdjacentEdgeList(neighbor,
						CyEdge.Type.ANY).size();
			}

			if (function != null) {
				double weight = function.getWeight(edge)
						/ weights[neighborIndex];
				res += probability * pageranks[neighborIndex] * weight;
			} else {
				res += probability * (pageranks[neighborIndex] / normalize);
			}
		}

		return res;
	}
}
