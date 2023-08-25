package com.ti2cc;

import java.sql.*;

public class DAO {
	private Connection conexao;

	public DAO() {
		conexao = null;
	}

	public boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydatabase = "Ex2";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
		String username = "postgres";
		String password = "9115";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}

	public boolean close() {
		boolean status = false;

		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	public boolean inserirArma(Arma arma) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO arma (codigo, calibre, capmag, ano, nome) "
					+ "VALUES (" + arma.getCodigo() + ", '" + arma.getcalibre() + "', '"
					+ arma.getcapmag() + "', '" + arma.getano() + "', '" + arma.getnome() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarArma(Arma arma) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE arma SET calibre = '" + arma.getcalibre() + "', capmag = '"
					+ arma.getcapmag() + "', capmag = '" + arma.getano() + "'" + "', nome = '" + arma.getnome() + "'"
					+ " WHERE codigo = " + arma.getCodigo();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirArma(int codigo) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM arma WHERE codigo = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public Arma[] getArmas() {
		Arma[] armas = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM arma");
			if (rs.next()) {
				rs.last();
				armas = new Arma[rs.getRow()];
				rs.beforeFirst();

				for (int i = 0; rs.next(); i++) {
					armas[i] = new Arma(rs.getInt("codigo"), rs.getString("calibre"),
							rs.getInt("capmag"), rs.getString("ano"), rs.getString("nome"));
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return armas;
	}

	public Arma[] getArmasMasculinos() {
		Arma[] armas = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM arma WHERE arma.capmag LIKE 'M'");
			if (rs.next()) {
				rs.last();
				armas = new Arma[rs.getRow()];
				rs.beforeFirst();

				for (int i = 0; rs.next(); i++) {
					armas[i] = new Arma(rs.getInt("codigo"), rs.getString("calibre"),
							rs.getInt("capmag"), rs.getString("ano"), rs.getString("nome"));
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return armas;
	}
}