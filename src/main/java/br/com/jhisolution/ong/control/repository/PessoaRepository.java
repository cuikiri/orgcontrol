package br.com.jhisolution.ong.control.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jhisolution.ong.control.domain.Pessoa;

/**
 * Spring Data  repository for the Pessoa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("select p from Pessoa p join fetch p.user u left join fetch u.foto f where u.id = :id")
	Pessoa findOneByUser(@Param("id") Long id);
	
	@Modifying
	@Query("update Pessoa p set p.nome = :nome, p.nascimento = :nascimento, p.rg = :rg, p.cpf = :cpf where p.id = :id")
	void updateOne(@Param("id") Long id, @Param("nome") String nome, @Param("nascimento") LocalDate nascimento, @Param("rg") String rg, @Param("cpf") String cpf);
	
    @Query(value = "select distinct pessoa from Pessoa pessoa left join fetch pessoa.avisos",
        countQuery = "select count(distinct pessoa) from Pessoa pessoa")
    Page<Pessoa> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct pessoa from Pessoa pessoa left join fetch pessoa.avisos")
    List<Pessoa> findAllWithEagerRelationships();

    @Query("select pessoa from Pessoa pessoa left join fetch pessoa.avisos where pessoa.id =:id")
    Optional<Pessoa> findOneWithEagerRelationships(@Param("id") Long id);

}
