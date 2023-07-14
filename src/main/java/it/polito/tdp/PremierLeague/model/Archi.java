package it.polito.tdp.PremierLeague.model;

public class Archi {
	Integer idPlayer1;
	Integer idPlayer2;
	Double peso;
	
	public Archi(Integer idPlayer1, Integer idPlayer2, Double peso) {
		super();
		this.idPlayer1 = idPlayer1;
		this.idPlayer2 = idPlayer2;
		this.peso = peso;
	}

	public Integer getIdPlayer1() {
		return idPlayer1;
	}

	public void setIdPlayer1(Integer idPlayer1) {
		this.idPlayer1 = idPlayer1;
	}

	public Integer getIdPlayer2() {
		return idPlayer2;
	}

	public void setIdPlayer2(Integer idPlayer2) {
		this.idPlayer2 = idPlayer2;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	

}
