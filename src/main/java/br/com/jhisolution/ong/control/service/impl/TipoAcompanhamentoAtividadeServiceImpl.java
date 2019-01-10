package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.TipoAcompanhamentoAtividadeService;
import br.com.jhisolution.ong.control.domain.TipoAcompanhamentoAtividade;
import br.com.jhisolution.ong.control.repository.TipoAcompanhamentoAtividadeRepository;
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
 * Service Implementation for managing TipoAcompanhamentoAtividade.
 */
@Service
@Transactional
public class TipoAcompanhamentoAtividadeServiceImpl implements TipoAcompanhamentoAtividadeService {

    private final Logger log = LoggerFactory.getLogger(TipoAcompanhamentoAtividadeServiceImpl.class);

    private final TipoAcompanhamentoAtividadeRepository tipoAcompanhamentoAtividadeRepository;

    public TipoAcompanhamentoAtividadeServiceImpl(TipoAcompanhamentoAtividadeRepository tipoAcompanhamentoAtividadeRepository) {
        this.tipoAcompanhamentoAtividadeRepository = tipoAcompanhamentoAtividadeRepository;
    }

    /**
     * Save a tipoAcompanhamentoAtividade.
     *
     * @param tipoAcompanhamentoAtividade the entity to save
     * @return the persisted entity
     */
    @Override
    public TipoAcompanhamentoAtividade save(TipoAcompanhamentoAtividade tipoAcompanhamentoAtividade) {
        log.debug("Request to save TipoAcompanhamentoAtividade : {}", tipoAcompanhamentoAtividade);
        return tipoAcompanhamentoAtividadeRepository.save(tipoAcompanhamentoAtividade);
    }

    /**
     * Get all the tipoAcompanhamentoAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoAcompanhamentoAtividade> findAll(Pageable pageable) {
        log.debug("Request to get all TipoAcompanhamentoAtividades");
        return tipoAcompanhamentoAtividadeRepository.findAll(pageable);
    }



    /**
     *  get all the tipoAcompanhamentoAtividades where AcompanhamentoAtividade is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TipoAcompanhamentoAtividade> findAllWhereAcompanhamentoAtividadeIsNull() {
        log.debug("Request to get all tipoAcompanhamentoAtividades where AcompanhamentoAtividade is null");
        return StreamSupport
            .stream(tipoAcompanhamentoAtividadeRepository.findAll().spliterator(), false)
            .filter(tipoAcompanhamentoAtividade -> tipoAcompanhamentoAtividade.getAcompanhamentoAtividade() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one tipoAcompanhamentoAtividade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoAcompanhamentoAtividade> findOne(Long id) {
        log.debug("Request to get TipoAcompanhamentoAtividade : {}", id);
        return tipoAcompanhamentoAtividadeRepository.findById(id);
    }

    /**
     * Delete the tipoAcompanhamentoAtividade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoAcompanhamentoAtividade : {}", id);
        tipoAcompanhamentoAtividadeRepository.deleteById(id);
    }
}
