/**
 * 
 */
package org.cytoscape.graph.impl.centralities;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.cytoscape.graph.algorithms.cyGraphAlgo.DijkstraShortestPathFinder;
import org.cytoscape.graph.algorithms.cyGraphAlgo.DijkstraStats;
import org.cytoscape.graph.algorithms.cyGraphAlgo.WeightFunction;
import org.cytoscape.graph.algorithms.impl.cyGraphAlgo.DijkstraShortestPathFinderImpl;
import org.cytoscape.graph.api.centralities.BetweennessStress;
import org.cytoscape.graph.api.centralities.Closeness;
import org.cytoscape.graph.api.centralities.ClusteringCoefficient;
import org.cytoscape.graph.api.centralities.NetworkStatistics;
import org.cytoscape.graph.api.centralities.NetworkStatsResults;
import org.cytoscape.graph.api.centralities.Radiality;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 * 
 */
public class NetworkStatisticsImpl implements NetworkStatistics {

	@Override
	public NetworkStatsResults computeAll(CyNetwork network, boolean directed,
			WeightFunction function) {

		Map<CyNode, DijkstraStats> nodeDistanceMap = new IdentityHashMap<CyNode, DijkstraStats>();
		DijkstraShortestPathFinder dPathFinder = new DijkstraShortestPathFinderImpl();

		List<CyNode> nodeList = network.getNodeList();
		
		Map<CyNode, Double> eccentricityMap = new IdentityHashMap<CyNode, Double>();
		Map<CyNode, Double> radialityMap = new IdentityHashMap<CyNode, Double>();
		Map<CyNode, Double> closenessMap = new IdentityHashMap<CyNode, Double>();
		Map<CyNode, Double> cCoefficientMap = new IdentityHashMap<CyNode, Double>();
		Map<CyNode, Double> nodeBetweennessMap = new IdentityHashMap<CyNode, Double>();
		Map<CyEdge, Double> edgeBetweennessMap = new IdentityHashMap<CyEdge, Double>();
		Map<CyNode, Double> nodeStressMap = new IdentityHashMap<CyNode, Double>();

		double diameter = 0.0;
		double eccentricity = 0.0;
		for (CyNode tempNode : nodeList) {

			DijkstraStats tempStats = dPathFinder.findPath(network, tempNode,
					directed, function);
			nodeDistanceMap.put(tempNode, tempStats);
			eccentricity = tempStats.getEccentricity();
			eccentricityMap.put(tempNode, eccentricity);
			if (eccentricity > diameter) {
				diameter = eccentricity;
			}
		}

		BetweennessStress bStress = new BetweennessStressImpl();
		Map<CyIdentifiable, List<Double>> bStressMap = bStress
				.getBetweennessStress(network, directed);

		Radiality rad = new RadialityImpl();
		Closeness clo = new ClosenessImpl();
		ClusteringCoefficient cCoefficient = new ClusteringCoefficientImpl();

		for (CyNode tempNode : nodeList) {

			closenessMap.put(tempNode,
					clo.getCloseness(network, nodeDistanceMap.get(tempNode)));
			radialityMap.put(tempNode, rad.getRadiality(network, diameter,
					eccentricity, nodeDistanceMap.get(tempNode)));
			cCoefficientMap.put(tempNode, cCoefficient
					.getClusteringCoefficient(network, tempNode, directed));
			nodeBetweennessMap.put(tempNode, bStressMap.get(tempNode).get(0));
			nodeStressMap.put(tempNode, bStressMap.get(tempNode).get(1));

		}

		for (CyEdge edge : network.getEdgeList()) {
			edgeBetweennessMap.put(edge, bStressMap.get(edge).get(0));
		}

		return new NetworkStatsResultsImpl(radialityMap, closenessMap,
				cCoefficientMap, nodeBetweennessMap, edgeBetweennessMap,
				nodeStressMap, eccentricityMap, diameter);
	}

}
