package br.com.jhisolution.ong.control.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jhisolution.ong.control.domain.Documento;
import br.com.jhisolution.ong.control.domain.Pessoa;


/**
 * Spring Data  repository for the Documento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
	Page<Documento> findAllByPessoa(Pageable pageable, Pessoa pessoa);
}
