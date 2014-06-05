/**
 * 
 */
package org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl;

import java.util.Map;
import java.util.Stack;

import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 * 
 */
public class DijkstraStats {

	private Map<CyNode, MetaNode> nodeToMetaNodeMap;

	private CyNode source;

	public DijkstraStats(CyNode source, Map<CyNode, MetaNode> nodeToMetaNodeMap) {

		this.nodeToMetaNodeMap = nodeToMetaNodeMap;
		this.source = source;
	}

	public double getDistanceTo(CyNode target) {

		return nodeToMetaNodeMap.get(target).getDistance();

	}

	public CyNode getSource() {

		return this.source;
	}

	public boolean hasPathTo(CyNode target) {

		return nodeToMetaNodeMap.get(target).getDistance() < Double.POSITIVE_INFINITY;

	}

	public CyNode getPredecessorTo(CyNode target) {

		return nodeToMetaNodeMap.get(target).getPredecessor();

	}

	public Iterable<CyNode> getPathTo(CyNode target) {

		if (!hasPathTo(target))
			return null;

		Stack<CyNode> path = new Stack<CyNode>();
		path.push(target);
		CyNode predecessor = nodeToMetaNodeMap.get(target).getPredecessor();
		while (predecessor != null) {
			path.push(predecessor);
			predecessor = nodeToMetaNodeMap.get(target).getPredecessor();
		}
		return path;

	}

}