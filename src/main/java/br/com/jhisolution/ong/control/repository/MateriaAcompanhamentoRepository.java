package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.MateriaAcompanhamento;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MateriaAcompanhamento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MateriaAcompanhamentoRepository extends JpaRepository<MateriaAcompanhamento, Long> {

}
