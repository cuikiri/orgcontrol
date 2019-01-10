package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.AcompanhamentoAlunoService;
import br.com.jhisolution.ong.control.domain.AcompanhamentoAluno;
import br.com.jhisolution.ong.control.repository.AcompanhamentoAlunoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AcompanhamentoAluno.
 */
@Service
@Transactional
public class AcompanhamentoAlunoServiceImpl implements AcompanhamentoAlunoService {

    private final Logger log = LoggerFactory.getLogger(AcompanhamentoAlunoServiceImpl.class);

    private final AcompanhamentoAlunoRepository acompanhamentoAlunoRepository;

    public AcompanhamentoAlunoServiceImpl(AcompanhamentoAlunoRepository acompanhamentoAlunoRepository) {
        this.acompanhamentoAlunoRepository = acompanhamentoAlunoRepository;
    }

    /**
     * Save a acompanhamentoAluno.
     *
     * @param acompanhamentoAluno the entity to save
     * @return the persisted entity
     */
    @Override
    public AcompanhamentoAluno save(AcompanhamentoAluno acompanhamentoAluno) {
        log.debug("Request to save AcompanhamentoAluno : {}", acompanhamentoAluno);
        return acompanhamentoAlunoRepository.save(acompanhamentoAluno);
    }

    /**
     * Get all the acompanhamentoAlunos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AcompanhamentoAluno> findAll(Pageable pageable) {
        log.debug("Request to get all AcompanhamentoAlunos");
        return acompanhamentoAlunoRepository.findAll(pageable);
    }


    /**
     * Get one acompanhamentoAluno by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AcompanhamentoAluno> findOne(Long id) {
        log.debug("Request to get AcompanhamentoAluno : {}", id);
        return acompanhamentoAlunoRepository.findById(id);
    }

    /**
     * Delete the acompanhamentoAluno by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AcompanhamentoAluno : {}", id);
        acompanhamentoAlunoRepository.deleteById(id);
    }
}
