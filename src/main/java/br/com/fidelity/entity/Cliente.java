package br.com.fidelity.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Cliente", uniqueConstraints = { @UniqueConstraint(name = "CLI_UK", columnNames = "usuario") })
public class Cliente implements Serializable {

	private static final long serialVersionUID = -1415667788803076886L;

	@Id
	@GeneratedValue
	private int id;

	@OneToOne
	@JoinColumn(name = "usuario", nullable = false)
	private Membro usuario;

	private String telefone;

	public Membro getUsuario() {
		return usuario;
	}

	public void setUsuario(Membro usuario) {
		this.usuario = usuario;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
