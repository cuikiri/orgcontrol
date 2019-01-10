package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ItemPlanejamentoAtividadeService;
import br.com.jhisolution.ong.control.domain.ItemPlanejamentoAtividade;
import br.com.jhisolution.ong.control.repository.ItemPlanejamentoAtividadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ItemPlanejamentoAtividade.
 */
@Service
@Transactional
public class ItemPlanejamentoAtividadeServiceImpl implements ItemPlanejamentoAtividadeService {

    private final Logger log = LoggerFactory.getLogger(ItemPlanejamentoAtividadeServiceImpl.class);

    private final ItemPlanejamentoAtividadeRepository itemPlanejamentoAtividadeRepository;

    public ItemPlanejamentoAtividadeServiceImpl(ItemPlanejamentoAtividadeRepository itemPlanejamentoAtividadeRepository) {
        this.itemPlanejamentoAtividadeRepository = itemPlanejamentoAtividadeRepository;
    }

    /**
     * Save a itemPlanejamentoAtividade.
     *
     * @param itemPlanejamentoAtividade the entity to save
     * @return the persisted entity
     */
    @Override
    public ItemPlanejamentoAtividade save(ItemPlanejamentoAtividade itemPlanejamentoAtividade) {
        log.debug("Request to save ItemPlanejamentoAtividade : {}", itemPlanejamentoAtividade);
        return itemPlanejamentoAtividadeRepository.save(itemPlanejamentoAtividade);
    }

    /**
     * Get all the itemPlanejamentoAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ItemPlanejamentoAtividade> findAll(Pageable pageable) {
        log.debug("Request to get all ItemPlanejamentoAtividades");
        return itemPlanejamentoAtividadeRepository.findAll(pageable);
    }


    /**
     * Get one itemPlanejamentoAtividade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ItemPlanejamentoAtividade> findOne(Long id) {
        log.debug("Request to get ItemPlanejamentoAtividade : {}", id);
        return itemPlanejamentoAtividadeRepository.findById(id);
    }

    /**
     * Delete the itemPlanejamentoAtividade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemPlanejamentoAtividade : {}", id);
        itemPlanejamentoAtividadeRepository.deleteById(id);
    }
}
