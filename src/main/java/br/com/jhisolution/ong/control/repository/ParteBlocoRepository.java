package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.ParteBloco;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ParteBloco entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParteBlocoRepository extends JpaRepository<ParteBloco, Long> {

}
