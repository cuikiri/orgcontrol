package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.TipoAcompanhamentoAlunoService;
import br.com.jhisolution.ong.control.domain.TipoAcompanhamentoAluno;
import br.com.jhisolution.ong.control.repository.TipoAcompanhamentoAlunoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing TipoAcompanhamentoAluno.
 */
@Service
@Transactional
public class TipoAcompanhamentoAlunoServiceImpl implements TipoAcompanhamentoAlunoService {

    private final Logger log = LoggerFactory.getLogger(TipoAcompanhamentoAlunoServiceImpl.class);

    private final TipoAcompanhamentoAlunoRepository tipoAcompanhamentoAlunoRepository;

    public TipoAcompanhamentoAlunoServiceImpl(TipoAcompanhamentoAlunoRepository tipoAcompanhamentoAlunoRepository) {
        this.tipoAcompanhamentoAlunoRepository = tipoAcompanhamentoAlunoRepository;
    }

    /**
     * Save a tipoAcompanhamentoAluno.
     *
     * @param tipoAcompanhamentoAluno the entity to save
     * @return the persisted entity
     */
    @Override
    public TipoAcompanhamentoAluno save(TipoAcompanhamentoAluno tipoAcompanhamentoAluno) {
        log.debug("Request to save TipoAcompanhamentoAluno : {}", tipoAcompanhamentoAluno);
        return tipoAcompanhamentoAlunoRepository.save(tipoAcompanhamentoAluno);
    }

    /**
     * Get all the tipoAcompanhamentoAlunos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoAcompanhamentoAluno> findAll(Pageable pageable) {
        log.debug("Request to get all TipoAcompanhamentoAlunos");
        return tipoAcompanhamentoAlunoRepository.findAll(pageable);
    }



    /**
     *  get all the tipoAcompanhamentoAlunos where AcompanhamentoAluno is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TipoAcompanhamentoAluno> findAllWhereAcompanhamentoAlunoIsNull() {
        log.debug("Request to get all tipoAcompanhamentoAlunos where AcompanhamentoAluno is null");
        return StreamSupport
            .stream(tipoAcompanhamentoAlunoRepository.findAll().spliterator(), false)
            .filter(tipoAcompanhamentoAluno -> tipoAcompanhamentoAluno.getAcompanhamentoAluno() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one tipoAcompanhamentoAluno by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoAcompanhamentoAluno> findOne(Long id) {
        log.debug("Request to get TipoAcompanhamentoAluno : {}", id);
        return tipoAcompanhamentoAlunoRepository.findById(id);
    }

    /**
     * Delete the tipoAcompanhamentoAluno by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoAcompanhamentoAluno : {}", id);
        tipoAcompanhamentoAlunoRepository.deleteById(id);
    }
}
