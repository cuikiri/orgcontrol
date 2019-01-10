package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.TipoUnidadeService;
import br.com.jhisolution.ong.control.domain.TipoUnidade;
import br.com.jhisolution.ong.control.repository.TipoUnidadeRepository;
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
 * Service Implementation for managing TipoUnidade.
 */
@Service
@Transactional
public class TipoUnidadeServiceImpl implements TipoUnidadeService {

    private final Logger log = LoggerFactory.getLogger(TipoUnidadeServiceImpl.class);

    private final TipoUnidadeRepository tipoUnidadeRepository;

    public TipoUnidadeServiceImpl(TipoUnidadeRepository tipoUnidadeRepository) {
        this.tipoUnidadeRepository = tipoUnidadeRepository;
    }

    /**
     * Save a tipoUnidade.
     *
     * @param tipoUnidade the entity to save
     * @return the persisted entity
     */
    @Override
    public TipoUnidade save(TipoUnidade tipoUnidade) {
        log.debug("Request to save TipoUnidade : {}", tipoUnidade);
        return tipoUnidadeRepository.save(tipoUnidade);
    }

    /**
     * Get all the tipoUnidades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoUnidade> findAll(Pageable pageable) {
        log.debug("Request to get all TipoUnidades");
        return tipoUnidadeRepository.findAll(pageable);
    }



    /**
     *  get all the tipoUnidades where Unidade is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TipoUnidade> findAllWhereUnidadeIsNull() {
        log.debug("Request to get all tipoUnidades where Unidade is null");
        return StreamSupport
            .stream(tipoUnidadeRepository.findAll().spliterator(), false)
            .filter(tipoUnidade -> tipoUnidade.getUnidade() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one tipoUnidade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoUnidade> findOne(Long id) {
        log.debug("Request to get TipoUnidade : {}", id);
        return tipoUnidadeRepository.findById(id);
    }

    /**
     * Delete the tipoUnidade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoUnidade : {}", id);
        tipoUnidadeRepository.deleteById(id);
    }
}
