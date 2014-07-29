/**
 * 
 */
package org.cytoscape.graph.centralities.api;

import java.util.List;
import java.util.Map;

import org.cytoscape.graph.algorithms.api.WeightFunction;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 * 
 */
public interface Centralities {

	/**
	 * @param network
	 *            The CyNetwork for which the user wants to compute centrality
	 *            measures.
	 * @param directed
	 *            True if the network is to be treated as directed and false
	 *            otherwise.
	 * @param function
	 *            WeightFunction mapping each edge to a weight.
	 * @return
	 */
	public NetworkStatsResults computeAll(CyNetwork network, boolean directed,
			WeightFunction function);

	/**
	 * Returns a Map from CyNode -> (Betweenness, Stress) and CyEdge ->
	 * (Betweenness)
	 * 
	 * @param network
	 *            The CyNetwork for which the user wants to compute node, edge
	 *            betweenness and node stress.
	 * @param directed
	 *            True if the network is to be treated as directed and false
	 *            otherwise.
	 * @return Map of CyNode -> (Betweenness, Stress), CyEdge -> (Betweenness)
	 */
	public Map<CyIdentifiable, List<Double>> getBetweennessStress(
			CyNetwork network, boolean directed);

	/**
	 * @param network
	 *            The CyNetwork containing the node for which the user wants to
	 *            compute the closeness measure.
	 * @param node
	 *            The CyNode for which the user wants to compute the closeness
	 *            measure.
	 * @param directed
	 *            True if the network is to be treated as directed and false
	 *            otherwise.
	 * 
	 * @param function
	 *            WeightFunction mapping each edge to a weight.
	 * @return The closeness value of the node.
	 */
	public double getCloseness(CyNetwork network, CyNode node,
			boolean directed, WeightFunction function);

	/**
	 * @param network
	 *            The CyNetwork containing the node for which the user wants to
	 *            compute the clustering coefficient.
	 * @param node
	 *            The CyNode for which the user wants to compute the clustering
	 *            coefficient.
	 * @param directed
	 *            True if the network is to be treated as directed and false
	 *            otherwise.
	 * @return The clustering coefficient value of node.
	 */
	public double getClusteringCoefficient(CyNetwork network, CyNode node,
			boolean directed);

	/**
	 * @param network
	 *            The CyNetwork containing the node for which the user wants to
	 *            compute the degree.
	 * @param node
	 *            The CyNode for which the user wants to compute the degree.
	 * @return The degree.
	 */
	public int getDegree(CyNetwork network, CyNode node);

	/**
	 * @param network
	 *            The CyNetwork containing the node for which the user wants to
	 *            compute the in-degree.
	 * @param node
	 *            The CyNode for which the user wants to compute the in-degree.
	 * @return The in-degree.
	 */
	public int getIndegree(CyNetwork network, CyNode node);

	/**
	 * @param network
	 *            The CyNetwork containing the node for which the user wants to
	 *            compute the out-degree.
	 * @param node
	 *            The CyNode for which the user wants to compute the out-degree.
	 * @return The out-degree.
	 */
	public int getOutdegree(CyNetwork network, CyNode node);

	/**
	 * @param network
	 *            The CyNetwork containing the node for which the user wants to
	 *            compute the eccentricity measure.
	 * @param node
	 *            The CyNode for which the user wants to compute the
	 *            eccentricity.
	 * @param directed
	 *            True if the network is to be treated as directed and false
	 *            otherwise.
	 * @param function
	 *            WeightFunction mapping each edge to a weight.
	 * @return The eccentricity measure of the node.
	 */
	public double getEccentricity(CyNetwork network, CyNode node,
			boolean directed, WeightFunction function);

	/**
	 * @param network
	 *            The CyNetwork containing the node for which the user wants to
	 *            compute the radiality measure.
	 * @param node
	 *            The CyNode for which the user wants to compute the radiality
	 *            measure.
	 * @param directed
	 *            True if the network is to be treated as directed and false
	 *            otherwise.
	 * @param function
	 *            WeightFunction mapping each edge to a weight.
	 * @return The radiality measure of the node.
	 */
	public double getRadiality(CyNetwork network, CyNode node,
			boolean directed, WeightFunction function);
}
