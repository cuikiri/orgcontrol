package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Pessoa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query(value = "select distinct pessoa from Pessoa pessoa left join fetch pessoa.avisos",
        countQuery = "select count(distinct pessoa) from Pessoa pessoa")
    Page<Pessoa> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct pessoa from Pessoa pessoa left join fetch pessoa.avisos")
    List<Pessoa> findAllWithEagerRelationships();

    @Query("select pessoa from Pessoa pessoa left join fetch pessoa.avisos where pessoa.id =:id")
    Optional<Pessoa> findOneWithEagerRelationships(@Param("id") Long id);

}
