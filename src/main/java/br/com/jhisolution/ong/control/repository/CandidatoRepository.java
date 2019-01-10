package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Candidato;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Candidato entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

}
