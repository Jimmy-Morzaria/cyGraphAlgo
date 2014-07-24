/**
 * 
 */
package org.cytoscape.graph.algorithms.impl.cyGraphAlgo;

import java.util.Map;

import org.cytoscape.graph.algorithms.cyGraphAlgo.PagerankResults;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public class PagerankResultsImpl implements PagerankResults{

	private Map<CyNode, Integer> nodeIndexMap;

	private double pageranks[];
	
	public PagerankResultsImpl(Map<CyNode, Integer> nodeIndexMap, double pageranks[]){
		
		this.nodeIndexMap = nodeIndexMap;
		this.pageranks = pageranks;
	}
	@Override
	public double getPagerank(CyNode node) {
		// TODO Auto-generated method stub
		return pageranks[nodeIndexMap.get(node)];
	}

	
}
