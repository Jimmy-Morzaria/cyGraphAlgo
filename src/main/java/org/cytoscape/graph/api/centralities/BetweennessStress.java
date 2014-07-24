/**
 * 
 */
package org.cytoscape.graph.api.centralities;

import java.util.List;
import java.util.Map;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;

/**
 * @author Jimmy
 *
 */
public interface BetweennessStress {

	public Map<CyIdentifiable, List<Double>> getBetweennessStress(CyNetwork network,
			boolean directed);
}
