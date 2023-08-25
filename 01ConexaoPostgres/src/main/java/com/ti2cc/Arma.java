package com.ti2cc;

public class Arma {
	private int codigo;
	private String calibre;
	private int capmag;
	private String ano;
	private String nome;

	
	public Arma() {
		this.codigo = -1;
		this.calibre = "";
		this.capmag = 0;
		this.ano = "";
		this.nome = "";
	}
	
	public Arma(int codigo, String calibre, int capmag, String ano, String nome) {
		this.codigo = codigo;
		this.calibre = calibre;
		this.capmag = capmag;
		this.ano = ano;
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getcalibre() {
		return calibre;
	}

	public void setcalibre(String calibre) {
		this.calibre = calibre;
	}

	public int getcapmag() {
		return capmag;
	}

	public void setcapmag(int capmag) {
		this.capmag = capmag;
	}

	public String getano() {
		return ano;
	}

	public void setano(String ano) {
		this.ano = ano;
	}

	public String getnome() {
		return nome;
	}

	public void setnome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Arma [codigo=" + codigo + ", calibre=" + calibre + ", capmag=" + capmag + ", ano=" + ano + ", Nome=" + nome + "]";
	}	
}
