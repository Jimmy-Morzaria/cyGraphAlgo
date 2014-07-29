/**
 * 
 */
package org.cytoscape.graph.algorithms.impl;

import java.util.Queue;

import org.cytoscape.graph.algorithms.api.PrimMSTStats;
import org.cytoscape.model.CyEdge;

/**
 * @author Jimmy
 * 
 */
public class PrimMSTStatsImpl implements PrimMSTStats {

	private double weight;

	private Queue<CyEdge> mst;

	public PrimMSTStatsImpl(Queue<CyEdge> mst, double weight) {

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
