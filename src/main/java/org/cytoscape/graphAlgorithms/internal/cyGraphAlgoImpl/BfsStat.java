/**
 * 
 */
package org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public class BfsStat {
	
	
	private Map<CyNode, CyNode> predecessorMap;
	
	private Map<CyNode, Integer> distanceMap;
	
	public BfsStat(Map<CyNode, CyNode> predecessorMap, Map<CyNode, Integer> distanceMap){
		
		this.predecessorMap = predecessorMap;
		this.distanceMap = distanceMap;
		
	}
	public CyNode getPredecssor(CyNode node){
	
			return predecessorMap.get(node);
	}
	
	public int getDistance(CyNode target){
		
		return distanceMap.get(target);
	}
	
	public CyNode getFarthestNode(){
		
		int max = 0, i = 0;
		CyNode maxNode = null;
		
		for(CyNode node : distanceMap.keySet()){
			
			int temp = distanceMap.get(node);
			
			if(i==0){
				maxNode = node;
				max = temp;
			}	
			else if(temp > max){
				max = temp;
				maxNode = node; 
			}
			i++;
		}
		
		return maxNode;
	}
	
	public CyNode getClosestNode(){
		
		int min = 0, i = 0;
		CyNode minNode = null;
		for(CyNode node : distanceMap.keySet()){
			
			int temp = distanceMap.get(node);
			if(i==0){
				minNode = node;
				min = temp;
			}	
			else if(temp < min){
				min = temp;
				minNode = node; 
			}
			i++;
		}	
		return minNode;
	}
	
	public Iterator<CyNode> getShortestPath(CyNode target){
		
		List<CyNode> shortestPath = new ArrayList<CyNode>();
		CyNode previous = predecessorMap.get(target);
		
		shortestPath.add(target);
		while (previous != null) {
			shortestPath.add(previous);
			previous = predecessorMap.get(target);
		}
		
		Collections.reverse(shortestPath);
		Iterator<CyNode> iterator = shortestPath.iterator();
		
		return iterator;
	}

}
