package com.devsuperior.uri2737.repositories;

import com.devsuperior.uri2737.dto.LawyerMinDTO;
import com.devsuperior.uri2737.entities.Lawyer;
import com.devsuperior.uri2737.projections.LawyerMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {


    /* Em consultas nativeQuery, a coluna da tabela precisa ter um ALIAS exatamente IGUAL ao metodo da interface projection.
    *  No caso abaixo 'customers_number' precisa ter um alias igual ao nome que está no metodo em LaywerMinProjection, que esta em camelcase: 'CustomersNumber'.
    */

    /*  O Spring Data JPA faz a conversão automática de snake_case para camelCase em 2 situações:
    *       - Quando se usa uma consulta em formato JPQL (ou metodos padrão do Spring Data JPA, como findByName());
    *       - Quando mapeia entidades anotadas com @Entity
    */

    /*  Ao usar funçoes SQL como ROUND(), MOD(), ABS() ou AVG, o resultado retornado pode ser de um TIPO DIFERENTE dos dados do campo no banco de dados.
    *       - Abaixo a operação o valor do campo customers_number é trabalhado por AVG() e ROUND() e o resultado é do tipo NUMERIC, sendo necessário o
    *         CAST() AS INTEGER para que seja compatível com o campo do DTO no mapeamento. */

    @Query(nativeQuery = true, value =
                    "SELECT name AS name, customers_number AS customersNumber FROM lawyers WHERE customers_number = (SELECT MAX(customers_number) FROM lawyers) " +
                    "UNION ALL " +
                    "SELECT name AS name, customers_number AS customersNumber FROM lawyers WHERE customers_number = (SELECT MIN(customers_number) FROM lawyers) " +
                    "UNION ALL " +
                    "SELECT 'Average' AS name, CAST(ROUND(AVG(customers_number), 0) AS INTEGER) AS customersNumber FROM lawyers")
    List<LawyerMinProjection> search1();


    /*  No JPQL ainda nao existe uma clausula como o UNION ALL para unir as consultas em um resultado   */
}
