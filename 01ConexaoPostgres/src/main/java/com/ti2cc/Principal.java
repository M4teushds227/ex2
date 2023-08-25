package com.ti2cc;

public class Principal {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();

		
		Arma arma = new Arma(1,".50",7,"2007","Deseart Eagle");
		if(dao.inserirArma(arma) == true) {
			System.out.println("Inserção com sucesso -> " + arma.toString());
		}
		
		System.out.println("==== Mostrar usuários do sexo masculino === ");
		Arma[] armas = dao.getArmasMasculinos();
		for(int i = 0; i < armas.length; i++) {
			System.out.println(armas[i].toString());
		}

		System.out.println("==== Mostrar usuários === ");
		armas = dao.getArmas();
		for(int i = 0; i < armas.length; i++) {
			System.out.println(armas[i].toString());
		}
		
		dao.excluirArma(arma.getCodigo());
		
		armas = dao.getArmas();
		System.out.println("==== Mostrar usuários === ");		
		for(int i = 0; i < armas.length; i++) {
			System.out.println(armas[i].toString());
		}
		
		dao.close();
	}
}