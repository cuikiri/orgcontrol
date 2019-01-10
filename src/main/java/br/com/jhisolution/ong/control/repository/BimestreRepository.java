package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Bimestre;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Bimestre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BimestreRepository extends JpaRepository<Bimestre, Long> {

}
