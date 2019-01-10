package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.ExameMedico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the ExameMedico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExameMedicoRepository extends JpaRepository<ExameMedico, Long> {

    @Query(value = "select distinct exame_medico from ExameMedico exame_medico left join fetch exame_medico.doencas",
        countQuery = "select count(distinct exame_medico) from ExameMedico exame_medico")
    Page<ExameMedico> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct exame_medico from ExameMedico exame_medico left join fetch exame_medico.doencas")
    List<ExameMedico> findAllWithEagerRelationships();

    @Query("select exame_medico from ExameMedico exame_medico left join fetch exame_medico.doencas where exame_medico.id =:id")
    Optional<ExameMedico> findOneWithEagerRelationships(@Param("id") Long id);

}
