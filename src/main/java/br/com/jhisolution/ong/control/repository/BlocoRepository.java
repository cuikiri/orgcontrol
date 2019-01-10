package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Bloco;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Bloco entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlocoRepository extends JpaRepository<Bloco, Long> {

}
