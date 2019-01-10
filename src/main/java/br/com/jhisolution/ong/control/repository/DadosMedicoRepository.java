package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.DadosMedico;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DadosMedico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DadosMedicoRepository extends JpaRepository<DadosMedico, Long> {

}
