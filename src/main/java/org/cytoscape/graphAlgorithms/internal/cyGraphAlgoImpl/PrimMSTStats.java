/**
 * 
 */
package org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl;

import java.util.Queue;

import org.cytoscape.model.CyEdge;

/**
 * @author Jimmy
 * 
 */
public class PrimMSTStats {

	private double weight;

	private Queue<CyEdge> mst;

	public PrimMSTStats(Queue<CyEdge> mst, double weight) {

		this.mst = mst;
		this.weight = weight;
	}

	public double getWeight() {

		return this.weight;

	}

	public Iterable<CyEdge> getMST() {

		return this.mst;

	}
}
