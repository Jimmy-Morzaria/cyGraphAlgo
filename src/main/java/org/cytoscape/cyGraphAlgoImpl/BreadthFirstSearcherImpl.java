package org.cytoscape.cyGraphAlgoImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


import org.cytoscape.cyGraphAlgo.BreadthFirstSearcher;
import org.cytoscape.cyGraphAlgo.Callback;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

public class BreadthFirstSearcherImpl implements BreadthFirstSearcher{

	@Override
	public BfsStat search(CyNetwork network, CyNode source, boolean directed, Callback callback) {
		// TODO Auto-generated method stub
		
		Map<CyNode, Integer> distanceMap = new HashMap<CyNode, Integer>();
		Map<CyNode, Integer> colorMap = new HashMap<CyNode, Integer>();
		Map<CyNode, CyNode> previousNodeMap = new HashMap<CyNode, CyNode>();
		
		
		for(CyNode node : network.getNodeList()){
			colorMap.put(node, -1);
			distanceMap.put(node, -1);
			previousNodeMap.put(node, null);
		}
		
		colorMap.put(source, 0);
		distanceMap.put(source, 0);
		
		Queue<CyNode> queue = new LinkedList<CyNode>();
		
		queue.add(source);
		
		while(!queue.isEmpty()){
			
			CyNode node = queue.poll();
			
			for(CyNode neighbor : network.getNeighborList(node, CyEdge.Type.OUTGOING)){
				
				if(colorMap.get(neighbor)==-1){
					
					colorMap.put(neighbor, 0);
					distanceMap.put(neighbor, distanceMap.get(node)+1);
					previousNodeMap.put(node,neighbor);
					queue.add(neighbor);
				}
				colorMap.put(node, 1);
			}
		}
		
		return new BfsStat(previousNodeMap, distanceMap);
	}
}

