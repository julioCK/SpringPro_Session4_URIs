package com.devsuperior.uri2990.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "empregados")
public class Empregado2 {

    @Id
    private String cpf;
    private String enome;
    private Double salario;

    /* Essa entidade possui um auto-relacionamento
    *   Empregado pode ser tanto Empregado normal, como Empregado supervisor2 ou Empregado gerente
    *   Essa modelagem é adequada nos casos onde empregado normal, supervisor2 e gerente NÃO POSSUM ATRIBUTOS OU FUNÇÕES QUE OS DIFERENCIEM */


    /* Esse atributo só será preenchido se a instancia for empregado normal (não empregado supervisor2, nem gerente).
    *  Imagine ESSA instancia como empregado(empregado) se relacionando com outra instância empregado(supervisor2) separada.
    *  Aqui (empregado normal) seria o lado N, portanto o relacionamento desse lado seria @ManyToOne    */
    @ManyToOne
    @JoinColumn(name = "cpf_supervisor") // na tabela existe esse campo, mas aqui na classe não, pois o cpf do supervisor2 estara em supervisor2.cpf (acessado atraves de supervisor2.getCpf)
    private Empregado2 supervisor2;

    /* Agora imagine essa instancia como empregado(supervisor2) se relacionando com outra instancia empregado(empregado) separada.
    *  Aqui (empregado supervisor2) seria o lado 1, portanto o relacionamento desse lado seria @OneToMany    */
    @OneToMany(mappedBy = "supervisor2") //
    private List<Empregado2> supervisionados = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "dnumero")
    private Departamento2 departamento2;

    @ManyToMany
    @JoinTable(name = "trabalha",
        joinColumns = @JoinColumn(name = "cpf_emp"),
        inverseJoinColumns = @JoinColumn(name = "pnumero"))
    private Set<Projeto2> projetosOndeTrabalha2 = new HashSet<>();

    public Empregado2() {
    }

    public Empregado2(String cpf, String enome, Double salario) {
        this.cpf = cpf;
        this.enome = enome;
        this.salario = salario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEnome() {
        return enome;
    }

    public void setEnome(String enome) {
        this.enome = enome;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Empregado2 getSupervisor2() {
        return supervisor2;
    }

    public void setSupervisor2(Empregado2 supervisor2) {
        this.supervisor2 = supervisor2;
    }

    public Departamento2 getDepartamento2() {
        return departamento2;
    }

    public void setDepartamento2(Departamento2 departamento2) {
        this.departamento2 = departamento2;
    }
}
