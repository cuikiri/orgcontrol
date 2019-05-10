package br.com.jhisolution.ong.control.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jhisolution.ong.control.domain.FotoDocumento;


/**
 * Spring Data  repository for the FotoDocumento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FotoDocumentoRepository extends JpaRepository<FotoDocumento, Long> {
	@Query(value = "SELECT u FROM FotoDocumento u WHERE u.documento.id = :id")
	Page<FotoDocumento> findAllByDocumento(Pageable pageable, @Param("id")Long id);
}
