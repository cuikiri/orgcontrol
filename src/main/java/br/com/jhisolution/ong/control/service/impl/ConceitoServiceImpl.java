package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ConceitoService;
import br.com.jhisolution.ong.control.domain.Conceito;
import br.com.jhisolution.ong.control.repository.ConceitoRepository;
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
 * Service Implementation for managing Conceito.
 */
@Service
@Transactional
public class ConceitoServiceImpl implements ConceitoService {

    private final Logger log = LoggerFactory.getLogger(ConceitoServiceImpl.class);

    private final ConceitoRepository conceitoRepository;

    public ConceitoServiceImpl(ConceitoRepository conceitoRepository) {
        this.conceitoRepository = conceitoRepository;
    }

    /**
     * Save a conceito.
     *
     * @param conceito the entity to save
     * @return the persisted entity
     */
    @Override
    public Conceito save(Conceito conceito) {
        log.debug("Request to save Conceito : {}", conceito);
        return conceitoRepository.save(conceito);
    }

    /**
     * Get all the conceitos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Conceito> findAll(Pageable pageable) {
        log.debug("Request to get all Conceitos");
        return conceitoRepository.findAll(pageable);
    }



    /**
     *  get all the conceitos where FechamentoBimestre is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Conceito> findAllWhereFechamentoBimestreIsNull() {
        log.debug("Request to get all conceitos where FechamentoBimestre is null");
        return StreamSupport
            .stream(conceitoRepository.findAll().spliterator(), false)
            .filter(conceito -> conceito.getFechamentoBimestre() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one conceito by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Conceito> findOne(Long id) {
        log.debug("Request to get Conceito : {}", id);
        return conceitoRepository.findById(id);
    }

    /**
     * Delete the conceito by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Conceito : {}", id);
        conceitoRepository.deleteById(id);
    }
}
