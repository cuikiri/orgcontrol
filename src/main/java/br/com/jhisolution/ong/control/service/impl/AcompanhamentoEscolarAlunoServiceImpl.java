package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.AcompanhamentoEscolarAlunoService;
import br.com.jhisolution.ong.control.domain.AcompanhamentoEscolarAluno;
import br.com.jhisolution.ong.control.repository.AcompanhamentoEscolarAlunoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AcompanhamentoEscolarAluno.
 */
@Service
@Transactional
public class AcompanhamentoEscolarAlunoServiceImpl implements AcompanhamentoEscolarAlunoService {

    private final Logger log = LoggerFactory.getLogger(AcompanhamentoEscolarAlunoServiceImpl.class);

    private final AcompanhamentoEscolarAlunoRepository acompanhamentoEscolarAlunoRepository;

    public AcompanhamentoEscolarAlunoServiceImpl(AcompanhamentoEscolarAlunoRepository acompanhamentoEscolarAlunoRepository) {
        this.acompanhamentoEscolarAlunoRepository = acompanhamentoEscolarAlunoRepository;
    }

    /**
     * Save a acompanhamentoEscolarAluno.
     *
     * @param acompanhamentoEscolarAluno the entity to save
     * @return the persisted entity
     */
    @Override
    public AcompanhamentoEscolarAluno save(AcompanhamentoEscolarAluno acompanhamentoEscolarAluno) {
        log.debug("Request to save AcompanhamentoEscolarAluno : {}", acompanhamentoEscolarAluno);
        return acompanhamentoEscolarAlunoRepository.save(acompanhamentoEscolarAluno);
    }

    /**
     * Get all the acompanhamentoEscolarAlunos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AcompanhamentoEscolarAluno> findAll(Pageable pageable) {
        log.debug("Request to get all AcompanhamentoEscolarAlunos");
        return acompanhamentoEscolarAlunoRepository.findAll(pageable);
    }


    /**
     * Get one acompanhamentoEscolarAluno by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AcompanhamentoEscolarAluno> findOne(Long id) {
        log.debug("Request to get AcompanhamentoEscolarAluno : {}", id);
        return acompanhamentoEscolarAlunoRepository.findById(id);
    }

    /**
     * Delete the acompanhamentoEscolarAluno by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AcompanhamentoEscolarAluno : {}", id);
        acompanhamentoEscolarAlunoRepository.deleteById(id);
    }
}
