package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Responsavel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Responsavel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {

    @Query(value = "select distinct responsavel from Responsavel responsavel left join fetch responsavel.alunos",
        countQuery = "select count(distinct responsavel) from Responsavel responsavel")
    Page<Responsavel> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct responsavel from Responsavel responsavel left join fetch responsavel.alunos")
    List<Responsavel> findAllWithEagerRelationships();

    @Query("select responsavel from Responsavel responsavel left join fetch responsavel.alunos where responsavel.id =:id")
    Optional<Responsavel> findOneWithEagerRelationships(@Param("id") Long id);

}
