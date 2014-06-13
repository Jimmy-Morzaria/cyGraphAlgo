/**
 * 
 */
package org.cytoscape.graph.algorithms.cyGraphAlgo;

import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface DijkstraStats {

	public double getDistanceTo(CyNode target);
	
	public CyNode getSource();
	
	public boolean hasPathTo(CyNode target);
	
	public CyNode getPredecessorTo(CyNode target);
	
	public Iterable<CyNode> getPathTo(CyNode target);
}
