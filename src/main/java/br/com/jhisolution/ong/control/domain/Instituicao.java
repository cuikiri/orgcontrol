package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Instituicao.
 */
@Entity
@Table(name = "instituicao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Instituicao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Size(max = 100)
    @Column(name = "razao_social", length = 100)
    private String razaoSocial;

    @Size(max = 20)
    @Column(name = "cnpj", length = 20)
    private String cnpj;

    @Size(max = 50)
    @Column(name = "site", length = 50)
    private String site;

    @Size(max = 50)
    @Column(name = "blog", length = 50)
    private String blog;

    @OneToMany(mappedBy = "instituicao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Unidade> unidades = new HashSet<>();
    @OneToMany(mappedBy = "instituicao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Eleicao> eleicaos = new HashSet<>();
    @OneToMany(mappedBy = "instituicao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Colaborador> colaboradors = new HashSet<>();
    @OneToMany(mappedBy = "instituicao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CalendarioInstituicao> calendarioInstituicaos = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Instituicao nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public Instituicao razaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
        return this;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Instituicao cnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSite() {
        return site;
    }

    public Instituicao site(String site) {
        this.site = site;
        return this;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getBlog() {
        return blog;
    }

    public Instituicao blog(String blog) {
        this.blog = blog;
        return this;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public Set<Unidade> getUnidades() {
        return unidades;
    }

    public Instituicao unidades(Set<Unidade> unidades) {
        this.unidades = unidades;
        return this;
    }

    public Instituicao addUnidade(Unidade unidade) {
        this.unidades.add(unidade);
        unidade.setInstituicao(this);
        return this;
    }

    public Instituicao removeUnidade(Unidade unidade) {
        this.unidades.remove(unidade);
        unidade.setInstituicao(null);
        return this;
    }

    public void setUnidades(Set<Unidade> unidades) {
        this.unidades = unidades;
    }

    public Set<Eleicao> getEleicaos() {
        return eleicaos;
    }

    public Instituicao eleicaos(Set<Eleicao> eleicaos) {
        this.eleicaos = eleicaos;
        return this;
    }

    public Instituicao addEleicao(Eleicao eleicao) {
        this.eleicaos.add(eleicao);
        eleicao.setInstituicao(this);
        return this;
    }

    public Instituicao removeEleicao(Eleicao eleicao) {
        this.eleicaos.remove(eleicao);
        eleicao.setInstituicao(null);
        return this;
    }

    public void setEleicaos(Set<Eleicao> eleicaos) {
        this.eleicaos = eleicaos;
    }

    public Set<Colaborador> getColaboradors() {
        return colaboradors;
    }

    public Instituicao colaboradors(Set<Colaborador> colaboradors) {
        this.colaboradors = colaboradors;
        return this;
    }

    public Instituicao addColaborador(Colaborador colaborador) {
        this.colaboradors.add(colaborador);
        colaborador.setInstituicao(this);
        return this;
    }

    public Instituicao removeColaborador(Colaborador colaborador) {
        this.colaboradors.remove(colaborador);
        colaborador.setInstituicao(null);
        return this;
    }

    public void setColaboradors(Set<Colaborador> colaboradors) {
        this.colaboradors = colaboradors;
    }

    public Set<CalendarioInstituicao> getCalendarioInstituicaos() {
        return calendarioInstituicaos;
    }

    public Instituicao calendarioInstituicaos(Set<CalendarioInstituicao> calendarioInstituicaos) {
        this.calendarioInstituicaos = calendarioInstituicaos;
        return this;
    }

    public Instituicao addCalendarioInstituicao(CalendarioInstituicao calendarioInstituicao) {
        this.calendarioInstituicaos.add(calendarioInstituicao);
        calendarioInstituicao.setInstituicao(this);
        return this;
    }

    public Instituicao removeCalendarioInstituicao(CalendarioInstituicao calendarioInstituicao) {
        this.calendarioInstituicaos.remove(calendarioInstituicao);
        calendarioInstituicao.setInstituicao(null);
        return this;
    }

    public void setCalendarioInstituicaos(Set<CalendarioInstituicao> calendarioInstituicaos) {
        this.calendarioInstituicaos = calendarioInstituicaos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Instituicao instituicao = (Instituicao) o;
        if (instituicao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), instituicao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Instituicao{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", razaoSocial='" + getRazaoSocial() + "'" +
            ", cnpj='" + getCnpj() + "'" +
            ", site='" + getSite() + "'" +
            ", blog='" + getBlog() + "'" +
            "}";
    }
}
