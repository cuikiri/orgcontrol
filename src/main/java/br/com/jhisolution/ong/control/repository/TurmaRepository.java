package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Turma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Turma entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    @Query(value = "select distinct turma from Turma turma left join fetch turma.horarioMaterias left join fetch turma.periodoAtividades",
        countQuery = "select count(distinct turma) from Turma turma")
    Page<Turma> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct turma from Turma turma left join fetch turma.horarioMaterias left join fetch turma.periodoAtividades")
    List<Turma> findAllWithEagerRelationships();

    @Query("select turma from Turma turma left join fetch turma.horarioMaterias left join fetch turma.periodoAtividades where turma.id =:id")
    Optional<Turma> findOneWithEagerRelationships(@Param("id") Long id);

}
