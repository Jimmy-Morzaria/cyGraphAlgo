/**
 * 
 */
package org.cytoscape.graph.algorithms.impl.cyGraphAlgo;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.cytoscape.graph.algorithms.cyGraphAlgo.HITS;
import org.cytoscape.graph.algorithms.cyGraphAlgo.HITSResults;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * Ref: Jon M. Kleinberg, Authoritative Sources in a Hyperlinked Environment, in
 * Journal of the ACM 46 (5): 604–632 (1999)
 * 
 * @author Jimmy
 * 
 */
public class HITSImpl implements HITS {

	private final double epsilon = 0.0001;

	@Override
	public HITSResults compute(CyNetwork network, boolean directed) {

		int nodeCount = network.getNodeCount();

		double authority[] = new double[nodeCount];

		double hubs[] = new double[nodeCount];

		Map<CyNode, Integer> nodeIndexMap = new IdentityHashMap<CyNode, Integer>();

		int index = 0;
		for (CyNode node : network.getNodeList()) {
			nodeIndexMap.put(node, index);
			index++;
		}

		double tempHubs[] = new double[nodeCount];

		double tempAuthority[] = new double[nodeCount];

		for (int i = 0; i < nodeCount; i++) {

			authority[i] = tempAuthority[i] = 1.0;
			hubs[i] = tempAuthority[i] = 1.0;
		}

		while (true) {

			boolean completed = true;
			updateAuthorityValue(network, tempAuthority, hubs, directed,
					nodeIndexMap);
			updateHubValue(network, tempHubs, authority, directed, nodeIndexMap);

			completed = checkDifference(authority, tempAuthority, epsilon)
					&& checkDifference(hubs, tempHubs, epsilon);

			System.arraycopy(tempAuthority, 0, authority, 0, nodeCount);
			System.arraycopy(tempHubs, 0, hubs, 0, nodeCount);

			if (completed)
				break;
		}
		return new HITSResultsImpl(authority, hubs, nodeIndexMap);
	}

	private void updateHubValue(CyNetwork network, double tempHubs[],
			double authority[], boolean directed,
			Map<CyNode, Integer> nodeIndexMap) {

		double norm = 0.0;
		for (CyNode node : network.getNodeList()) {

			int nodeIndex = nodeIndexMap.get(node);

			double hub = 0.0;
			List<CyNode> nodeList;
			if (directed) {
				nodeList = network.getNeighborList(node, CyEdge.Type.OUTGOING);
			} else {
				nodeList = network.getNeighborList(node, CyEdge.Type.ANY);
			}

			for (CyNode neighbor : nodeList) {

				hub += authority[nodeIndexMap.get(neighbor)];
			}

			tempHubs[nodeIndex] = hub;

			norm += tempHubs[nodeIndex] * tempHubs[nodeIndex];

		}
		norm = Math.sqrt(norm);
		if (norm > 0) {
			for (int i = 0; i < tempHubs.length; i++) {
				tempHubs[i] = tempHubs[i] / norm;
			}
		}
	}

	private void updateAuthorityValue(CyNetwork network,
			double tempAuthority[], double hubs[], boolean directed,
			Map<CyNode, Integer> nodeIndexMap) {

		double norm = 0.0;
		for (CyNode node : network.getNodeList()) {
			int nodeIndex = nodeIndexMap.get(node);
			double auth = 0.0;
			List<CyNode> nodeList;
			if (directed) {
				nodeList = network.getNeighborList(node, CyEdge.Type.INCOMING);
			} else {
				nodeList = network.getNeighborList(node, CyEdge.Type.ANY);
			}

			for (CyNode neighbor : nodeList) {
				auth += hubs[nodeIndexMap.get(neighbor)];
			}

			tempAuthority[nodeIndex] = auth;

			norm += tempAuthority[nodeIndex] * tempAuthority[nodeIndex];

		}
		norm = Math.sqrt(norm);
		if (norm > 0) {

			for (int i = 0; i < tempAuthority.length; i++) {
				tempAuthority[i] = tempAuthority[i] / norm;
			}
		}
	}

	private boolean checkDifference(double[] oldValues, double[] newValues,
			double epsilon) {

		for (int i = 0; i < oldValues.length; i++) {
			if (oldValues[i] > 0
					&& ((newValues[i] - oldValues[i]) / oldValues[i]) >= epsilon) {
				return false;
			}
		}
		return true;
	}
}
