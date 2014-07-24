/**
 * 
 */
package org.cytoscape.graph.api.centralities;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface Degree {

	public int getDegree(CyNetwork network, CyNode node);
	
	public int getIndegree(CyNetwork network, CyNode node);
	
	public int getOutdegree(CyNetwork network, CyNode node);
}
