package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Localizacao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Localizacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {

}
