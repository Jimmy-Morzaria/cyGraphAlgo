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
public class KruskalMSTStats {

	private Queue<CyEdge> mst;
	
	private double weight;
	
	public KruskalMSTStats(Queue<CyEdge> mst, double weight){
		
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
