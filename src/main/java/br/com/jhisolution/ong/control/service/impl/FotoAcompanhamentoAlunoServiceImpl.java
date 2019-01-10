package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.FotoAcompanhamentoAlunoService;
import br.com.jhisolution.ong.control.domain.FotoAcompanhamentoAluno;
import br.com.jhisolution.ong.control.repository.FotoAcompanhamentoAlunoRepository;
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
 * Service Implementation for managing FotoAcompanhamentoAluno.
 */
@Service
@Transactional
public class FotoAcompanhamentoAlunoServiceImpl implements FotoAcompanhamentoAlunoService {

    private final Logger log = LoggerFactory.getLogger(FotoAcompanhamentoAlunoServiceImpl.class);

    private final FotoAcompanhamentoAlunoRepository fotoAcompanhamentoAlunoRepository;

    public FotoAcompanhamentoAlunoServiceImpl(FotoAcompanhamentoAlunoRepository fotoAcompanhamentoAlunoRepository) {
        this.fotoAcompanhamentoAlunoRepository = fotoAcompanhamentoAlunoRepository;
    }

    /**
     * Save a fotoAcompanhamentoAluno.
     *
     * @param fotoAcompanhamentoAluno the entity to save
     * @return the persisted entity
     */
    @Override
    public FotoAcompanhamentoAluno save(FotoAcompanhamentoAluno fotoAcompanhamentoAluno) {
        log.debug("Request to save FotoAcompanhamentoAluno : {}", fotoAcompanhamentoAluno);
        return fotoAcompanhamentoAlunoRepository.save(fotoAcompanhamentoAluno);
    }

    /**
     * Get all the fotoAcompanhamentoAlunos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FotoAcompanhamentoAluno> findAll(Pageable pageable) {
        log.debug("Request to get all FotoAcompanhamentoAlunos");
        return fotoAcompanhamentoAlunoRepository.findAll(pageable);
    }



    /**
     *  get all the fotoAcompanhamentoAlunos where AcompanhamentoAluno is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<FotoAcompanhamentoAluno> findAllWhereAcompanhamentoAlunoIsNull() {
        log.debug("Request to get all fotoAcompanhamentoAlunos where AcompanhamentoAluno is null");
        return StreamSupport
            .stream(fotoAcompanhamentoAlunoRepository.findAll().spliterator(), false)
            .filter(fotoAcompanhamentoAluno -> fotoAcompanhamentoAluno.getAcompanhamentoAluno() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one fotoAcompanhamentoAluno by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FotoAcompanhamentoAluno> findOne(Long id) {
        log.debug("Request to get FotoAcompanhamentoAluno : {}", id);
        return fotoAcompanhamentoAlunoRepository.findById(id);
    }

    /**
     * Delete the fotoAcompanhamentoAluno by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FotoAcompanhamentoAluno : {}", id);
        fotoAcompanhamentoAlunoRepository.deleteById(id);
    }
}
