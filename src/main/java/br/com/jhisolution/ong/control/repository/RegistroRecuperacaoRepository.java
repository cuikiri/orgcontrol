package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.RegistroRecuperacao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RegistroRecuperacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegistroRecuperacaoRepository extends JpaRepository<RegistroRecuperacao, Long> {

}
