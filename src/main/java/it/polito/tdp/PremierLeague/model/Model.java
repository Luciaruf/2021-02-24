package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	PremierLeagueDAO dao;
	Graph<Player, DefaultWeightedEdge> graph;
	
	
	public Model() {
		this.dao = new PremierLeagueDAO();
		this.graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
	}
	
	public Graph creaGrafo(int matchID) {
		this.graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.graph, this.dao.getPlayerVertices(matchID));
		
		for(Archi a : this.dao.getArchi(matchID)) {
			
			if(a.getPeso()>0) {
				Graphs.addEdgeWithVertices(this.graph, cerca(a.getIdPlayer1()), cerca(a.getIdPlayer2()), a.getPeso());
			}
			else{
				Graphs.addEdgeWithVertices(this.graph, cerca(a.getIdPlayer2()), cerca(a.getIdPlayer1()), Math.abs(a.getPeso()));
			}
		}
		
		return this.graph;
		
	}
	
	public List<Match> listaMatch(){
		return this.dao.listAllMatches();
	}
	
	public double getBilancio(Player a) {
		double bilancio = 0.0;
		
		List<DefaultWeightedEdge> edgesINDEfaultWeightedEdges = new ArrayList<>(this.graph.incomingEdgesOf(a)); //tutti gli edge che entrano in a
		List<DefaultWeightedEdge> edgesOUTDEfaultWeightedEdges = new ArrayList<>(this.graph.outgoingEdgesOf(a));
		
		
		for(DefaultWeightedEdge e1: edgesOUTDEfaultWeightedEdges) 
			bilancio+= this.graph.getEdgeWeight(e1);
		
		for(DefaultWeightedEdge e: edgesINDEfaultWeightedEdges) 
			bilancio -= this.graph.getEdgeWeight(e);
		
		return bilancio;	
	}
	
	public Player cerca(Integer playerId) {
		Player rest = null;
		
		for(Player p: this.graph.vertexSet()) {
			if(p.getPlayerID().compareTo(playerId)==0) {
				rest = p;
			}
		}
		
		return rest;
	}
	
}
