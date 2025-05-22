package com.devsuperior.uri2611.repositories;

import com.devsuperior.uri2611.dto.MovieDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true, value = "SELECT m.id, m.name "
            + "FROM movies m "
            + "INNER JOIN genres g ON m.id_genres = g.id "
            + "WHERE UPPER(g.description) = UPPER(:description)")
    List<MovieProjection> search1(String description);


    @Query("SELECT new com.devsuperior.uri2611.dto.MovieDTO(obj.id, obj.name) "
            + "FROM Movie obj "
            + "WHERE UPPER(obj.genre.description) = UPPER(:description)")
    List<MovieDTO> search2(String description);
}
