package br.com.fidelity.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ponto {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	@JoinColumn(name = "idAssoc", referencedColumnName = "id", nullable = false)
	private Associacao idAssoc;

	private String data;

	private String hora;

	private int valor;

	public Associacao getIdAssoc() {
		return idAssoc;
	}

	public void setIdAssoc(Associacao idAssoc) {
		this.idAssoc = idAssoc;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
