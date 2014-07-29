/**
 * 
 */
package org.cytoscape.graph.algorithms.api;

import org.cytoscape.model.CyNetwork;

/**
 * @author Jimmy
 * 
 */
public interface Pagerank {

	public PagerankResults getPageranks(CyNetwork network,
			WeightFunction function, boolean directed);

	public PagerankResults getPageranks(CyNetwork network,
			WeightFunction function, boolean directed, double probability,
			double epsilon);
}
