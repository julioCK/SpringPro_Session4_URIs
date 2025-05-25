package com.devsuperior.uri2990.repositories;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2990.entities.Empregado;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

    @Query(nativeQuery = true, value = "SELECT e.cpf, e.enome, d.dnome " +
            "FROM empregados e INNER JOIN departamentos d ON e.dnumero = d.dnumero " +
            "WHERE e.cpf NOT IN (SELECT cpf_emp FROM trabalha) " +
            "ORDER BY e.cpf")
    List<EmpregadoDeptProjection> search1();

    @Query("SELECT new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(obj.cpf, obj.enome, obj.departamento.dnome) " +
            "FROM Empregado obj " +
            "WHERE obj.cpf NOT IN (SELECT obj.cpf FROM Empregado obj INNER JOIN obj.projetosOndeTrabalha) " +
            "ORDER BY obj.cpf")
    List<EmpregadoDeptDTO> search2();
}
