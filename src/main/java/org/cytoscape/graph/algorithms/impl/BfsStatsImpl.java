/**
 * 
 */
package org.cytoscape.graph.algorithms.impl;

import java.util.Map;
import java.util.Stack;

import org.cytoscape.graph.algorithms.api.BfsStats;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 * 
 */
public class BfsStatsImpl implements BfsStats{

	private boolean marked[];

	private int distTo[];

	private int edgeTo[];

	@SuppressWarnings("unused")
	private CyNetwork network;

	private CyNode source;

	private Map<CyNode, Integer> nodeToIndexMap;

	private Map<Integer, CyNode> indexToNodeMap;

	public BfsStatsImpl(CyNode source, CyNetwork network, boolean marked[],
			int distTo[], int edgeTo[], Map<CyNode, Integer> nodeToIndexMap,
			Map<Integer, CyNode> indexToNodeMap) {

		this.source = source;
		this.marked = marked;
		this.distTo = distTo;
		this.edgeTo = edgeTo;
		this.nodeToIndexMap = nodeToIndexMap;
		this.indexToNodeMap = indexToNodeMap;
		this.network = network;
	}

	public int getDistanceTo(CyNode target) {

		return distTo[nodeToIndexMap.get(target)];
	}

	public CyNode getSource() {

		return source;
	}

	public boolean hasPathTo(CyNode target) {

		return marked[nodeToIndexMap.get(target)];

	}

	public Iterable<CyNode> getPathTo(CyNode target) {

		if (!hasPathTo(target))
			return null;

		Stack<CyNode> path = new Stack<CyNode>();
		int x;
		for (x = nodeToIndexMap.get(target); distTo[x] != 0; x = edgeTo[x])
			path.push(indexToNodeMap.get(x));
		path.push(indexToNodeMap.get(x));
		return path;
	}

}
