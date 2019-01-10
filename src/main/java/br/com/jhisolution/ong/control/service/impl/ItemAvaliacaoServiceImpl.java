package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ItemAvaliacaoService;
import br.com.jhisolution.ong.control.domain.ItemAvaliacao;
import br.com.jhisolution.ong.control.repository.ItemAvaliacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ItemAvaliacao.
 */
@Service
@Transactional
public class ItemAvaliacaoServiceImpl implements ItemAvaliacaoService {

    private final Logger log = LoggerFactory.getLogger(ItemAvaliacaoServiceImpl.class);

    private final ItemAvaliacaoRepository itemAvaliacaoRepository;

    public ItemAvaliacaoServiceImpl(ItemAvaliacaoRepository itemAvaliacaoRepository) {
        this.itemAvaliacaoRepository = itemAvaliacaoRepository;
    }

    /**
     * Save a itemAvaliacao.
     *
     * @param itemAvaliacao the entity to save
     * @return the persisted entity
     */
    @Override
    public ItemAvaliacao save(ItemAvaliacao itemAvaliacao) {
        log.debug("Request to save ItemAvaliacao : {}", itemAvaliacao);
        return itemAvaliacaoRepository.save(itemAvaliacao);
    }

    /**
     * Get all the itemAvaliacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ItemAvaliacao> findAll(Pageable pageable) {
        log.debug("Request to get all ItemAvaliacaos");
        return itemAvaliacaoRepository.findAll(pageable);
    }


    /**
     * Get one itemAvaliacao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ItemAvaliacao> findOne(Long id) {
        log.debug("Request to get ItemAvaliacao : {}", id);
        return itemAvaliacaoRepository.findById(id);
    }

    /**
     * Delete the itemAvaliacao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemAvaliacao : {}", id);
        itemAvaliacaoRepository.deleteById(id);
    }
}
