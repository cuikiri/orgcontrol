package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ItemAcompanhamentoAtividadeService;
import br.com.jhisolution.ong.control.domain.ItemAcompanhamentoAtividade;
import br.com.jhisolution.ong.control.repository.ItemAcompanhamentoAtividadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ItemAcompanhamentoAtividade.
 */
@Service
@Transactional
public class ItemAcompanhamentoAtividadeServiceImpl implements ItemAcompanhamentoAtividadeService {

    private final Logger log = LoggerFactory.getLogger(ItemAcompanhamentoAtividadeServiceImpl.class);

    private final ItemAcompanhamentoAtividadeRepository itemAcompanhamentoAtividadeRepository;

    public ItemAcompanhamentoAtividadeServiceImpl(ItemAcompanhamentoAtividadeRepository itemAcompanhamentoAtividadeRepository) {
        this.itemAcompanhamentoAtividadeRepository = itemAcompanhamentoAtividadeRepository;
    }

    /**
     * Save a itemAcompanhamentoAtividade.
     *
     * @param itemAcompanhamentoAtividade the entity to save
     * @return the persisted entity
     */
    @Override
    public ItemAcompanhamentoAtividade save(ItemAcompanhamentoAtividade itemAcompanhamentoAtividade) {
        log.debug("Request to save ItemAcompanhamentoAtividade : {}", itemAcompanhamentoAtividade);
        return itemAcompanhamentoAtividadeRepository.save(itemAcompanhamentoAtividade);
    }

    /**
     * Get all the itemAcompanhamentoAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ItemAcompanhamentoAtividade> findAll(Pageable pageable) {
        log.debug("Request to get all ItemAcompanhamentoAtividades");
        return itemAcompanhamentoAtividadeRepository.findAll(pageable);
    }


    /**
     * Get one itemAcompanhamentoAtividade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ItemAcompanhamentoAtividade> findOne(Long id) {
        log.debug("Request to get ItemAcompanhamentoAtividade : {}", id);
        return itemAcompanhamentoAtividadeRepository.findById(id);
    }

    /**
     * Delete the itemAcompanhamentoAtividade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemAcompanhamentoAtividade : {}", id);
        itemAcompanhamentoAtividadeRepository.deleteById(id);
    }
}
