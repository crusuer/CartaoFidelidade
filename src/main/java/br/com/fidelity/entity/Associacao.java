package br.com.fidelity.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Associacao", uniqueConstraints = {@UniqueConstraint(name = "ASSOC_UK", columnNames = {"usuarioCli", "usuarioEstab"})})
public class Associacao {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuarioCli", referencedColumnName = "usuario", nullable = false)
    private Cliente usuarioCli;

    @ManyToOne
    @JoinColumn(name = "usuarioEstab", referencedColumnName = "usuario", nullable = false)
    private Estabelecimento usuarioEstab;

    private int tipoEstab;

    private String apelido;

    private String data;

    public Cliente getUsuarioCli() {
        return usuarioCli;
    }

    public void setUsuarioCli(Cliente usuarioCli) {
        this.usuarioCli = usuarioCli;
    }

    public Estabelecimento getUsuarioEstab() {
        return usuarioEstab;
    }

    public void setUsuarioEstab(Estabelecimento usuarioEstab) {
        this.usuarioEstab = usuarioEstab;
    }

    public int getTipoEstab() {
        return tipoEstab;
    }

    public void setTipoEstab(int tipoEstab) {
        this.tipoEstab = tipoEstab;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

}
