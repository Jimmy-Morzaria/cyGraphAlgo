/**
 * 
 */
package org.cytoscape.graph.algorithms.impl;

import java.util.Queue;

import org.cytoscape.graph.algorithms.api.KruskalMSTStats;
import org.cytoscape.model.CyEdge;

/**
 * @author Jimmy
 *
 */
public class KruskalMSTStatsImpl implements KruskalMSTStats{

	private Queue<CyEdge> mst;
	
	private double weight;
	
	public KruskalMSTStatsImpl(Queue<CyEdge> mst, double weight){
		
		this.mst = mst;
		this.weight = weight;
	
	}
	
	public double getWeight(){
		
		return this.weight;
		
	}
	
	public Iterable<CyEdge> getMST(){
		
		return this.mst;
		
	}
}
