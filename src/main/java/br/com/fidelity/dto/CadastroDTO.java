package br.com.fidelity.dto;

public class CadastroDTO {

	private String usuario;

	private String senha;

	private String tipo;

	private boolean habilitado;
	
	private String nome;
	
	private String diasFuncionamento;
	
	private String horarioFuncionamento;

	private int categoria;

	private int tipoEstab;

	private int validadeCartao;

	private String telefone;

	private String endereco;

	private String bairro;

	private String cidade;

	private String uf;

	private int validade;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDiasFuncionamento() {
		return diasFuncionamento;
	}

	public void setDiasFuncionamento(String diasFuncionamento) {
		this.diasFuncionamento = diasFuncionamento;
	}

	public String getHorarioFuncionamento() {
		return horarioFuncionamento;
	}

	public void setHorarioFuncionamento(String horarioFuncionamento) {
		this.horarioFuncionamento = horarioFuncionamento;
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

	public int getValidade() {
		return validade;
	}

	public void setValidade(int validade) {
		this.validade = validade;
	}

	public void setTipoEstab(int tipoEstab) {
		this.tipoEstab = tipoEstab;
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
