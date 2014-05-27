/**
 * 
 */
package org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl;

import java.util.Map;

import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public class DfsStat {

	public Map<CyNode, CyNode> predecessorMap;

	public Map<CyNode, Integer> discoverTimeMap;
	
	public Map<CyNode, Integer> finishTimeMap;
	
	public DfsStat(Map<CyNode, CyNode> predecessorMap, Map<CyNode, Integer> discoverTimeMap, Map<CyNode, Integer> finishTimeMap){
		
		this.predecessorMap = predecessorMap;
		this.discoverTimeMap = discoverTimeMap;
		this.finishTimeMap = finishTimeMap;
	}
	public int getDiscoveryTime(CyNode node){
		
		return discoverTimeMap.get(node);
	}
	
	public int getFinishingTime(CyNode node){
		
		return finishTimeMap.get(node);
	}
}
