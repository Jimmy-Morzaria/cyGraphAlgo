/**
 * 
 */
package org.cytoscape.graphAlgorithms.internal.cyGraphAlgoImpl;

import java.util.HashMap;
import java.util.Map;

import org.cytoscape.graphAlgorithms.internal.cyGraphAlgo.Callback;
import org.cytoscape.graphAlgorithms.internal.cyGraphAlgo.DepthFirstSearcher;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 *
 */
public class DepthFirstSearcherImpl implements DepthFirstSearcher{

	private int time = 0;
	
	private Map<CyNode, Integer> discoverTimeMap;
	
	private Map<CyNode, Integer> finishTimeMap;
	
	private Map<CyNode, Integer> colorMap;
	
	private Map<CyNode, CyNode> previousNodeMap;
	@Override
	public DfsStat search(CyNetwork network, CyNode source, boolean directed,
			Callback callback) {
		// TODO Auto-generated method stub
		
		discoverTimeMap = new HashMap<CyNode, Integer>();
		finishTimeMap = new HashMap<CyNode, Integer>();
		colorMap = new HashMap<CyNode, Integer>();
		previousNodeMap = new HashMap<CyNode, CyNode>();
		
		for(CyNode node : network.getNodeList()){
			
			colorMap.put(node, -1);
			previousNodeMap.put(node, null);
		}
				
		for(CyNode node : network.getNodeList()){
			
			if(colorMap.get(node)==-1)
				dfsVisit(network, node);
		}
		return new DfsStat(previousNodeMap, discoverTimeMap, finishTimeMap);
	}
	
	public void dfsVisit(CyNetwork network, CyNode node){
		
		time = time + 1;
		discoverTimeMap.put(node, time);
		colorMap.put(node, 0);
		
		for(CyNode neighbor : network.getNeighborList(node, CyEdge.Type.OUTGOING)){
			
			if(colorMap.get(neighbor)==-1){
				
				previousNodeMap.put(neighbor, node);
				dfsVisit(network, neighbor);
			}
		}
		colorMap.put(node, 1);
		time = time + 1;
		finishTimeMap.put(node, time);
	}

}
