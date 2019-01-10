package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Eleicao.
 */
@Entity
@Table(name = "eleicao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Eleicao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @NotNull
    @Column(name = "ano_eleicao", nullable = false)
    private Integer anoEleicao;

    @Column(name = "data_pleito")
    private LocalDate dataPleito;

    @Column(name = "total_eleitores")
    private Integer totalEleitores;

    @Column(name = "tota_votos")
    private Integer totaVotos;

    @Size(max = 100)
    @Column(name = "jhi_local", length = 100)
    private String local;

    @Size(max = 5)
    @Column(name = "hora", length = 5)
    private String hora;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private Chapa chapaGanhadora;

    @OneToMany(mappedBy = "eleicao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Chapa> chapas = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("eleicaos")
    private Instituicao instituicao;

    @ManyToOne
    @JsonIgnoreProperties("eleicaos")
    private Unidade unidade;

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

    public Eleicao nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public Eleicao dataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
        return this;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Integer getAnoEleicao() {
        return anoEleicao;
    }

    public Eleicao anoEleicao(Integer anoEleicao) {
        this.anoEleicao = anoEleicao;
        return this;
    }

    public void setAnoEleicao(Integer anoEleicao) {
        this.anoEleicao = anoEleicao;
    }

    public LocalDate getDataPleito() {
        return dataPleito;
    }

    public Eleicao dataPleito(LocalDate dataPleito) {
        this.dataPleito = dataPleito;
        return this;
    }

    public void setDataPleito(LocalDate dataPleito) {
        this.dataPleito = dataPleito;
    }

    public Integer getTotalEleitores() {
        return totalEleitores;
    }

    public Eleicao totalEleitores(Integer totalEleitores) {
        this.totalEleitores = totalEleitores;
        return this;
    }

    public void setTotalEleitores(Integer totalEleitores) {
        this.totalEleitores = totalEleitores;
    }

    public Integer getTotaVotos() {
        return totaVotos;
    }

    public Eleicao totaVotos(Integer totaVotos) {
        this.totaVotos = totaVotos;
        return this;
    }

    public void setTotaVotos(Integer totaVotos) {
        this.totaVotos = totaVotos;
    }

    public String getLocal() {
        return local;
    }

    public Eleicao local(String local) {
        this.local = local;
        return this;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getHora() {
        return hora;
    }

    public Eleicao hora(String hora) {
        this.hora = hora;
        return this;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getObs() {
        return obs;
    }

    public Eleicao obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Chapa getChapaGanhadora() {
        return chapaGanhadora;
    }

    public Eleicao chapaGanhadora(Chapa chapa) {
        this.chapaGanhadora = chapa;
        return this;
    }

    public void setChapaGanhadora(Chapa chapa) {
        this.chapaGanhadora = chapa;
    }

    public Set<Chapa> getChapas() {
        return chapas;
    }

    public Eleicao chapas(Set<Chapa> chapas) {
        this.chapas = chapas;
        return this;
    }

    public Eleicao addChapa(Chapa chapa) {
        this.chapas.add(chapa);
        chapa.setEleicao(this);
        return this;
    }

    public Eleicao removeChapa(Chapa chapa) {
        this.chapas.remove(chapa);
        chapa.setEleicao(null);
        return this;
    }

    public void setChapas(Set<Chapa> chapas) {
        this.chapas = chapas;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public Eleicao instituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
        return this;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public Eleicao unidade(Unidade unidade) {
        this.unidade = unidade;
        return this;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
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
        Eleicao eleicao = (Eleicao) o;
        if (eleicao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eleicao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Eleicao{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", dataCadastro='" + getDataCadastro() + "'" +
            ", anoEleicao=" + getAnoEleicao() +
            ", dataPleito='" + getDataPleito() + "'" +
            ", totalEleitores=" + getTotalEleitores() +
            ", totaVotos=" + getTotaVotos() +
            ", local='" + getLocal() + "'" +
            ", hora='" + getHora() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
