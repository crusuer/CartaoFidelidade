package br.com.fidelity.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Estabelecimento", uniqueConstraints = { @UniqueConstraint(name = "ESTAB_UK", columnNames = "usuario") })
public class Estabelecimento {

	@Id
	@GeneratedValue
	private int id;

	@OneToOne
	@JoinColumn(name = "usuario", nullable = false)
	private Membro usuario;

	private int categoria;

	private int tipoEstab;

	private int validadeCartao;

	private String telefone;

	private String endereco;

	private String bairro;

	private String cidade;

	private String uf;

	private int validade;

	public Membro getUsuario() {
		return usuario;
	}

	public void setUsuario(Membro usuario) {
		this.usuario = usuario;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public int getTipoEstab() {
		return tipoEstab;
	}

	public void setTipoEstab(int tipoEstab) {
		this.tipoEstab = tipoEstab;
	}

	public int getValidade() {
		return validade;
	}

	public void setValidade(int validade) {
		this.validade = validade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public int getValidadeCartao() {
		return validadeCartao;
	}

	public void setValidadeCartao(int validadeCartao) {
		this.validadeCartao = validadeCartao;
	}

}
