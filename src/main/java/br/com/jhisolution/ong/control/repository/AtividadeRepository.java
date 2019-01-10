package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Atividade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Atividade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    @Query(value = "select distinct atividade from Atividade atividade left join fetch atividade.alunos",
        countQuery = "select count(distinct atividade) from Atividade atividade")
    Page<Atividade> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct atividade from Atividade atividade left join fetch atividade.alunos")
    List<Atividade> findAllWithEagerRelationships();

    @Query("select atividade from Atividade atividade left join fetch atividade.alunos where atividade.id =:id")
    Optional<Atividade> findOneWithEagerRelationships(@Param("id") Long id);

}
