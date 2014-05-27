/**
 * 
 */
package org.cytoscape.cyGraphAlgo;

import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public interface Callback {

	public boolean notify(CyNode node, int time, int dist);
}
