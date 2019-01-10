package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.DadoBiologico;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DadoBiologico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DadoBiologicoRepository extends JpaRepository<DadoBiologico, Long> {

}
