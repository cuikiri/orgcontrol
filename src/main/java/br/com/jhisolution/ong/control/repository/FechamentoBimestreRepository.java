package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.FechamentoBimestre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the FechamentoBimestre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FechamentoBimestreRepository extends JpaRepository<FechamentoBimestre, Long> {

    @Query(value = "select distinct fechamento_bimestre from FechamentoBimestre fechamento_bimestre left join fetch fechamento_bimestre.alunos",
        countQuery = "select count(distinct fechamento_bimestre) from FechamentoBimestre fechamento_bimestre")
    Page<FechamentoBimestre> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct fechamento_bimestre from FechamentoBimestre fechamento_bimestre left join fetch fechamento_bimestre.alunos")
    List<FechamentoBimestre> findAllWithEagerRelationships();

    @Query("select fechamento_bimestre from FechamentoBimestre fechamento_bimestre left join fetch fechamento_bimestre.alunos where fechamento_bimestre.id =:id")
    Optional<FechamentoBimestre> findOneWithEagerRelationships(@Param("id") Long id);

}
