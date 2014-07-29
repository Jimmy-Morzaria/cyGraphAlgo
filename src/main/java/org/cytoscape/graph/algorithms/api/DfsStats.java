/**
 * 
 */
package org.cytoscape.graph.algorithms.api;

import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface DfsStats {

	public CyNode getSource();
	
	public boolean hasPathTo(CyNode target);
	
	public Iterable<CyNode> getPathTo(CyNode target);
	
	
}
