package com.devsuperior.uri2990.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departamentos")
public class Departamento2 {

    @Id
    private Integer dnumero;
    private String dnome;

    @OneToMany(mappedBy = "departamento2")
    private List<Empregado2> empregados2 = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cpf_gerente")
    private Empregado2 gerente2;

    @OneToMany(mappedBy = "departamento2")
    private List<Projeto2> projetos2 = new ArrayList<>();

    public Departamento2() {
    }

    public Integer getDnumero() {
        return dnumero;
    }

    public void setDnumero(Integer dnumero) {
        this.dnumero = dnumero;
    }

    public String getDnome() {
        return dnome;
    }

    public void setDnome(String dnome) {
        this.dnome = dnome;
    }

    public Empregado2 getGerente2() {
        return gerente2;
    }

    public void setGerente2(Empregado2 gerente2) {
        this.gerente2 = gerente2;
    }

    public List<Empregado2> getEmpregados2() {
        return empregados2;
    }

    public List<Projeto2> getProjetos2() {
        return projetos2;
    }
}
