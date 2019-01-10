package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.TipoConceitoService;
import br.com.jhisolution.ong.control.domain.TipoConceito;
import br.com.jhisolution.ong.control.repository.TipoConceitoRepository;
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
 * Service Implementation for managing TipoConceito.
 */
@Service
@Transactional
public class TipoConceitoServiceImpl implements TipoConceitoService {

    private final Logger log = LoggerFactory.getLogger(TipoConceitoServiceImpl.class);

    private final TipoConceitoRepository tipoConceitoRepository;

    public TipoConceitoServiceImpl(TipoConceitoRepository tipoConceitoRepository) {
        this.tipoConceitoRepository = tipoConceitoRepository;
    }

    /**
     * Save a tipoConceito.
     *
     * @param tipoConceito the entity to save
     * @return the persisted entity
     */
    @Override
    public TipoConceito save(TipoConceito tipoConceito) {
        log.debug("Request to save TipoConceito : {}", tipoConceito);
        return tipoConceitoRepository.save(tipoConceito);
    }

    /**
     * Get all the tipoConceitos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoConceito> findAll(Pageable pageable) {
        log.debug("Request to get all TipoConceitos");
        return tipoConceitoRepository.findAll(pageable);
    }



    /**
     *  get all the tipoConceitos where Conceito is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TipoConceito> findAllWhereConceitoIsNull() {
        log.debug("Request to get all tipoConceitos where Conceito is null");
        return StreamSupport
            .stream(tipoConceitoRepository.findAll().spliterator(), false)
            .filter(tipoConceito -> tipoConceito.getConceito() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one tipoConceito by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoConceito> findOne(Long id) {
        log.debug("Request to get TipoConceito : {}", id);
        return tipoConceitoRepository.findById(id);
    }

    /**
     * Delete the tipoConceito by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoConceito : {}", id);
        tipoConceitoRepository.deleteById(id);
    }
}
