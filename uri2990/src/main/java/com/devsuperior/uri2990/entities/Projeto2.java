package com.devsuperior.uri2990.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projetos")
public class Projeto2 {

    @Id
    private Integer pnumero;
    private String pnome;

    @ManyToOne
    @JoinColumn(name = "dnumero")
    private Departamento2 departamento2;

    @ManyToMany(mappedBy = "projetosOndeTrabalha2")
    private Set<Empregado2> empregados2 = new HashSet<>();

    public Projeto2() {
    }

    public Integer getPnumero() {
        return pnumero;
    }

    public void setPnumero(Integer pnumero) {
        this.pnumero = pnumero;
    }

    public String getPnome() {
        return pnome;
    }

    public void setPnome(String pnome) {
        this.pnome = pnome;
    }

    public Departamento2 getDepartamento2() {
        return departamento2;
    }

    public void setDepartamento2(Departamento2 departamento2) {
        this.departamento2 = departamento2;
    }

    public Set<Empregado2> getEmpregados2() {
        return empregados2;
    }
}
