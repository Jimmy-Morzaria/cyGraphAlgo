/**
 * 
 */
package org.cytoscape.graph.algorithms.api;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * @author Jimmy
 * 
 */
public interface GraphAlgorithms {

	/**
	 * @param network
	 *            CyNetwork containing the source node from which the user wants
	 *            to perform breadth first search.
	 * @param source
	 *            Source CyNode.
	 * @param directed
	 *            True if the network is to be treated as directed and false
	 *            otherwise.
	 * @param callback
	 *            Callback function
	 * @return
	 */
	public BfsStats breadthFirstSearch(CyNetwork network, CyNode source,
			boolean directed, Callback callback);

	/**
	 * @param network
	 *            CyNetwork containing the source node from which the user wants
	 *            to perform depth first search.
	 * @param source
	 *            Source CyNode.
	 * @param directed
	 *            True if the network is to be treated as directed and false
	 *            otherwise.
	 * @param callback
	 *            Callback function
	 * @return
	 */
	public DfsStats depthFirstSearch(CyNetwork network, CyNode source,
			boolean directed, Callback callback);

	/**
	 * @param network
	 *            CyNetwork
	 * @param directed
	 *            True if the network is to be treated as directed and false
	 *            otherwise.
	 * @param function
	 *            WeightFunction mapping each edge to a weight.
	 * @return
	 */
	public FloydWarshallStats findAllPairShortestPath(CyNetwork network,
			boolean directed, WeightFunction function);

	/**
	 * @param network
	 *            CyNetwork
	 * @param function
	 *            WeightFunction mapping each edge to a weight.
	 * @return
	 */
	public KruskalMSTStats findKruskalTree(CyNetwork network,
			WeightFunction function);

	/**
	 * @param network
	 *            CyNetwork
	 * @param source
	 *            Source CyNode.
	 * @param directed
	 *            True if the network is to be treated as directed and false
	 *            otherwise.
	 * @param function
	 *            WeightFunction mapping each edge to a weight.
	 * @return
	 */
	public DijkstraStats findPath(CyNetwork network, CyNode source,
			boolean directed, WeightFunction function);

	/**
	 * @param network
	 *            CyNetwork for which the user wants to find the minimum
	 *            spanning tree.
	 * @param function
	 *            WeightFunction mapping each edge to a weight.
	 * @return
	 */
	public PrimMSTStats findPrimTree(CyNetwork network, WeightFunction function);

	/**
	 * @param network
	 *            CyNetwork containing the source node and the other nodes to
	 *            which the user wants to find the shortest path.
	 * @param source
	 *            Source CyNode.
	 * @param directed
	 *            True if the network is to be treated as directed and false
	 *            otherwise.
	 * @param function
	 *            WeightFunction mapping each edge to a weight.
	 * @return
	 */
	public BellmanFordStats findShortestPath(CyNetwork network, CyNode source,
			boolean directed, WeightFunction function);

	/**
	 * @param network
	 *            CyNetwork containing the nodes for which the user wants to
	 *            compute HITS scores.
	 * @param directed
	 *            True if the network is to be treated as directed and false
	 *            otherwise.
	 * @return An instance of HITSResults that provides methods to access the
	 *         hub and authority values of a node.
	 */
	public HITSResults getHITSScores(CyNetwork network, boolean directed);

	/**
	 * @param network
	 *            CyNetwork containing the nodes for which the user wants to
	 *            compute pagerank scores.
	 * @param function
	 *            WeightFunction mapping each edge to a weight.
	 * @param directed
	 *            True if the network is to be treated as directed and false
	 *            otherwise.
	 * @return An instance of PagerankResults that provides methods to access
	 *         the pagerank score.
	 */
	public PagerankResults getPageranks(CyNetwork network,
			WeightFunction function, boolean directed);

	/**
	 * @param network
	 *            CyNetwork containing the nodes for which the user wants to
	 *            compute pagerank scores.
	 * @param function
	 *            WeightFunction mapping each edge to a weight.
	 * @param directed
	 *            True if the network is to be treated as directed and false
	 *            otherwise.
	 * @param probability
	 *            Probability value alpha to be used in the computation of
	 *            pagerank scores. The default is 0.85.
	 * @param epsilon
	 *            Epsilon value to be used in pagerank computation. The default
	 *            is 0.001.
	 * @return An instance of PagerankResults that provides methods to access
	 *         the pagerank score.
	 */
	public PagerankResults getPageranks(CyNetwork network,
			WeightFunction function, boolean directed, double probability,
			double epsilon);

}
