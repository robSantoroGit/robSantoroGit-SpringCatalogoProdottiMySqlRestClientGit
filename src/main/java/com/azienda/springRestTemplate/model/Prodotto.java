package com.azienda.springRestTemplate.model;

import java.io.Serializable;

public class Prodotto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id = null;
	
	private String nome = null;
	private Float prezzo = null;
	
	public Prodotto()
	{
		this(null,null);
	}
	
	public Prodotto(String nome,Float prezzo)
	{
		setNome(nome);
		setPrezzo(prezzo);
	}

	public Prodotto(Integer id,String nome,Float prezzo)
	{
		this.id = id;
		this.nome = nome;
		this.prezzo = prezzo;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prodotto other = (Prodotto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Prodotto [id=" + id + ", nome=" + nome + ", prezzo=" + prezzo + "]";
	}
	
}