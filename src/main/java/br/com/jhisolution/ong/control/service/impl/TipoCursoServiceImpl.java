package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.TipoCursoService;
import br.com.jhisolution.ong.control.domain.TipoCurso;
import br.com.jhisolution.ong.control.repository.TipoCursoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TipoCurso.
 */
@Service
@Transactional
public class TipoCursoServiceImpl implements TipoCursoService {

    private final Logger log = LoggerFactory.getLogger(TipoCursoServiceImpl.class);

    private final TipoCursoRepository tipoCursoRepository;

    public TipoCursoServiceImpl(TipoCursoRepository tipoCursoRepository) {
        this.tipoCursoRepository = tipoCursoRepository;
    }

    /**
     * Save a tipoCurso.
     *
     * @param tipoCurso the entity to save
     * @return the persisted entity
     */
    @Override
    public TipoCurso save(TipoCurso tipoCurso) {
        log.debug("Request to save TipoCurso : {}", tipoCurso);
        return tipoCursoRepository.save(tipoCurso);
    }

    /**
     * Get all the tipoCursos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoCurso> findAll(Pageable pageable) {
        log.debug("Request to get all TipoCursos");
        return tipoCursoRepository.findAll(pageable);
    }


    /**
     * Get one tipoCurso by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoCurso> findOne(Long id) {
        log.debug("Request to get TipoCurso : {}", id);
        return tipoCursoRepository.findById(id);
    }

    /**
     * Delete the tipoCurso by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoCurso : {}", id);
        tipoCursoRepository.deleteById(id);
    }
}
