/**
 * 
 */
package org.cytoscape.graph.algorithms.api;

import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface BellmanFordStats {

	public boolean hasNegativeCycle();
	
	public CyNode getSource();
	
	public double getDistanceTo(CyNode target);
	
	public boolean hasPathTo(CyNode target);
	
	public CyNode getPredecessorTo(CyNode target);
	
	public Iterable<CyNode> getPathTo(CyNode target);
}
