/**
 * 
 */
package org.cytoscape.graph.impl.centralities;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import org.cytoscape.graph.api.centralities.BetweennessStress;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 * 
 */
public class BetweennessStressImpl implements BetweennessStress{

	public Map<CyIdentifiable, List<Double>> getBetweennessStress(CyNetwork network,
			boolean directed) {

		Map<CyIdentifiable, List<Double>> betweennessStressMap = new IdentityHashMap<CyIdentifiable, List<Double>>();

		Map<CyEdge, Double> edgeBetweennessMap = new IdentityHashMap<CyEdge, Double>();
		Map<CyNode, NodeMetaData> nodeToNodeMetaDataMap = new IdentityHashMap<CyNode, NodeMetaData>();

		for (CyNode node : network.getNodeList()) {
			List<Double> list = new ArrayList<Double>();
			list.add(0.0);
			list.add(0.0);
			betweennessStressMap.put(node, list);
			nodeToNodeMetaDataMap.put(node, new NodeMetaData(node, 0.0, -1,
					0.0, 0.0));

		}

		for (CyNode node : network.getNodeList()) {

			Map<CyEdge, Double> edgeDependencyMap = new IdentityHashMap<CyEdge, Double>();
			for (CyNode node2 : network.getNodeList()) {
				nodeToNodeMetaDataMap.get(node2).resetAll();
			}

			Stack<CyNode> nodeStack = new Stack<CyNode>();
			Queue<CyNode> nodeQueue = new LinkedList<CyNode>();
			nodeQueue.add(node);
			nodeToNodeMetaDataMap.get(node).setDistance(0.0);
			nodeToNodeMetaDataMap.get(node).setShortestPaths(1.0);

			while (!nodeQueue.isEmpty()) {
				CyNode node2 = nodeQueue.remove();
				nodeStack.push(node2);
				NodeMetaData m2Node = nodeToNodeMetaDataMap.get(node2);
				List<CyNode> neighborList;
				if (directed) {
					neighborList = network.getNeighborList(node2,
							CyEdge.Type.OUTGOING);
				} else {
					neighborList = network.getNeighborList(node2,
							CyEdge.Type.ANY);
				}
				for (CyNode node3 : neighborList) {
					List<CyEdge> edges = network.getConnectingEdgeList(node2,
							node3, CyEdge.Type.ANY);
					NodeMetaData m3Node = nodeToNodeMetaDataMap.get(node3);
					if (m3Node.getDistance() < 0) {
						nodeQueue.add(node3);
						m3Node.setDistance(m2Node.getDistance() + 1.0);

					}

					if (m3Node.getDistance() == (m2Node.getDistance() + 1.0)) {
						m3Node.setShortestPaths(m3Node.getShortestPaths()
								+ m2Node.getShortestPaths());

						m3Node.getPredecessors().add(node2);

						for (CyEdge edge : edges) {
							m2Node.getConnectingEdges().add(edge);

						}
					}

					for (CyEdge edge : network.getConnectingEdgeList(node2,
							node3, CyEdge.Type.ANY)) {
						if (!edgeDependencyMap.containsKey(edge)) {
							edgeDependencyMap.put(edge, 0.0);
						}
					}

				}

			}
			while (!nodeStack.isEmpty()) {
				CyNode node3 = nodeStack.pop();
				NodeMetaData m3Node = nodeToNodeMetaDataMap.get(node3);
				for (CyNode node4 : m3Node.getPredecessors()) {

					NodeMetaData m4Node = nodeToNodeMetaDataMap.get(node4);

					m4Node.setBetweennessDependency(m4Node
							.getBetweennessDependency()
							+ ((m4Node.getShortestPaths() / m3Node
									.getShortestPaths()) * (1 + m3Node
									.getBetweennessDependency())));

					m4Node.setStressDependency(m4Node.getStressDependency() + 1
							+ m3Node.getStressDependency());

					List<CyEdge> edges = network.getConnectingEdgeList(node4,
							node3, CyEdge.Type.ANY);
					
					if (edges.size() != 0) {

						CyEdge compedge = edges.get(0);
						List<CyEdge> outEdges = m3Node.getConnectingEdges();
						double oldbetweenness = 0.0;
						double newbetweenness = 0.0;
						for (CyEdge edge : edges) {
							if (edgeBetweennessMap.containsKey(edge)) {
								oldbetweenness = edgeBetweennessMap.get(edge);
								break;
							}
						}
						if (outEdges.size() == 0) {
							newbetweenness = m4Node.getShortestPaths()
									/ m3Node.getShortestPaths();
						} else {
							double neighborbetweenness = 0.0;
							for (CyEdge neighbor : outEdges) {
								if (!edges.contains(neighbor)) {
									neighborbetweenness += edgeDependencyMap
											.get(neighbor);
								}
							}

							newbetweenness = (1 + neighborbetweenness)
									* (m4Node.getShortestPaths() / m3Node
											.getShortestPaths());
						}
						edgeDependencyMap.put(compedge, newbetweenness);
						for (CyEdge edge : edges) {
							edgeBetweennessMap.put(edge, newbetweenness
									+ oldbetweenness);
						}
					}

				}
				if (node3 != node) {
					List<Double> list = betweennessStressMap.get(node3);
					double temp = list.remove(0);
					list.add(0, temp + m3Node.getBetweennessDependency());
					temp = list.remove(1);
					list.add(
							1,
							temp + m3Node.getShortestPaths()
									* m3Node.getStressDependency());
					betweennessStressMap.put(node3, list);

				}
			}
		}
		int n = network.getNodeCount();
		for (CyNode node : network.getNodeList()) {
			List<Double> list = betweennessStressMap.get(node);
			double temp = list.remove(0);
			list.add(0, temp / ((n - 1) * (n - 2)));
			betweennessStressMap.put(node, list);
		}

		for (CyEdge edge : network.getEdgeList()) {
			List<Double> list = new ArrayList<Double>();
			list.add(edgeBetweennessMap.get(edge));
			betweennessStressMap.put(edge, list);
		}
		return betweennessStressMap;
	}
}

class NodeMetaData {

	private CyNode node;

	private double stressDependency;

	private double betweennessDependency;

	private double distance;

	private double shortestPaths;

	private List<CyNode> predecessors;

	private List<CyEdge> connectingEdges;

	public NodeMetaData(CyNode node, double betweennessDependency,
			double distance, double stressDependency, double shortestPaths) {
		this.node = node;
		this.betweennessDependency = betweennessDependency;
		this.distance = distance;
		this.stressDependency = stressDependency;
		this.shortestPaths = shortestPaths;
		this.predecessors = new ArrayList<CyNode>();
		this.connectingEdges = new ArrayList<CyEdge>();
	}

	public void resetAll() {
		this.betweennessDependency = 0.0;
		this.distance = -1;
		this.stressDependency = 0.0;
		this.shortestPaths = 0.0;
		this.predecessors = new ArrayList<CyNode>();
		this.connectingEdges = new ArrayList<CyEdge>();

	}

	public double getStressDependency() {

		return this.stressDependency;
	}

	public double getDistance() {

		return this.distance;

	}

	public CyNode getCyNode() {
		return this.node;
	}

	public double getBetweennessDependency() {

		return this.betweennessDependency;
	}

	public double getShortestPaths() {

		return this.shortestPaths;
	}

	public List<CyNode> getPredecessors() {

		return this.predecessors;
	}

	public List<CyEdge> getConnectingEdges() {

		return this.connectingEdges;

	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setShortestPaths(double sp) {
		this.shortestPaths = sp;
	}

	public void setBetweennessDependency(double bd) {
		this.betweennessDependency = bd;
	}

	public void setStressDependency(double sd) {

		this.stressDependency = sd;
	}
}
