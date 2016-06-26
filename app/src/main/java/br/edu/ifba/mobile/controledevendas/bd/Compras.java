package br.edu.ifba.mobile.controledevendas.bd;

import java.util.ArrayList;
import java.util.List;

public class Compras {
	private long codigo = -1;
	private String cliente;
	private String produto;
	private String preco;
	private String data;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return produto + " ( " + data + " )" ;
	}
}
