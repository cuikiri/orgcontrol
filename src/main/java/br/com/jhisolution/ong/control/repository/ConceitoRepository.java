package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Conceito;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Conceito entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConceitoRepository extends JpaRepository<Conceito, Long> {

}
