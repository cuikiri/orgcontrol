package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.TipoAtividadeService;
import br.com.jhisolution.ong.control.domain.TipoAtividade;
import br.com.jhisolution.ong.control.repository.TipoAtividadeRepository;
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
 * Service Implementation for managing TipoAtividade.
 */
@Service
@Transactional
public class TipoAtividadeServiceImpl implements TipoAtividadeService {

    private final Logger log = LoggerFactory.getLogger(TipoAtividadeServiceImpl.class);

    private final TipoAtividadeRepository tipoAtividadeRepository;

    public TipoAtividadeServiceImpl(TipoAtividadeRepository tipoAtividadeRepository) {
        this.tipoAtividadeRepository = tipoAtividadeRepository;
    }

    /**
     * Save a tipoAtividade.
     *
     * @param tipoAtividade the entity to save
     * @return the persisted entity
     */
    @Override
    public TipoAtividade save(TipoAtividade tipoAtividade) {
        log.debug("Request to save TipoAtividade : {}", tipoAtividade);
        return tipoAtividadeRepository.save(tipoAtividade);
    }

    /**
     * Get all the tipoAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoAtividade> findAll(Pageable pageable) {
        log.debug("Request to get all TipoAtividades");
        return tipoAtividadeRepository.findAll(pageable);
    }



    /**
     *  get all the tipoAtividades where Atividade is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TipoAtividade> findAllWhereAtividadeIsNull() {
        log.debug("Request to get all tipoAtividades where Atividade is null");
        return StreamSupport
            .stream(tipoAtividadeRepository.findAll().spliterator(), false)
            .filter(tipoAtividade -> tipoAtividade.getAtividade() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one tipoAtividade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoAtividade> findOne(Long id) {
        log.debug("Request to get TipoAtividade : {}", id);
        return tipoAtividadeRepository.findById(id);
    }

    /**
     * Delete the tipoAtividade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoAtividade : {}", id);
        tipoAtividadeRepository.deleteById(id);
    }
}
