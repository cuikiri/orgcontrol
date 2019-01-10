package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ItemPlanejamentoInstituicaoService;
import br.com.jhisolution.ong.control.domain.ItemPlanejamentoInstituicao;
import br.com.jhisolution.ong.control.repository.ItemPlanejamentoInstituicaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ItemPlanejamentoInstituicao.
 */
@Service
@Transactional
public class ItemPlanejamentoInstituicaoServiceImpl implements ItemPlanejamentoInstituicaoService {

    private final Logger log = LoggerFactory.getLogger(ItemPlanejamentoInstituicaoServiceImpl.class);

    private final ItemPlanejamentoInstituicaoRepository itemPlanejamentoInstituicaoRepository;

    public ItemPlanejamentoInstituicaoServiceImpl(ItemPlanejamentoInstituicaoRepository itemPlanejamentoInstituicaoRepository) {
        this.itemPlanejamentoInstituicaoRepository = itemPlanejamentoInstituicaoRepository;
    }

    /**
     * Save a itemPlanejamentoInstituicao.
     *
     * @param itemPlanejamentoInstituicao the entity to save
     * @return the persisted entity
     */
    @Override
    public ItemPlanejamentoInstituicao save(ItemPlanejamentoInstituicao itemPlanejamentoInstituicao) {
        log.debug("Request to save ItemPlanejamentoInstituicao : {}", itemPlanejamentoInstituicao);
        return itemPlanejamentoInstituicaoRepository.save(itemPlanejamentoInstituicao);
    }

    /**
     * Get all the itemPlanejamentoInstituicaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ItemPlanejamentoInstituicao> findAll(Pageable pageable) {
        log.debug("Request to get all ItemPlanejamentoInstituicaos");
        return itemPlanejamentoInstituicaoRepository.findAll(pageable);
    }


    /**
     * Get one itemPlanejamentoInstituicao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ItemPlanejamentoInstituicao> findOne(Long id) {
        log.debug("Request to get ItemPlanejamentoInstituicao : {}", id);
        return itemPlanejamentoInstituicaoRepository.findById(id);
    }

    /**
     * Delete the itemPlanejamentoInstituicao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemPlanejamentoInstituicao : {}", id);
        itemPlanejamentoInstituicaoRepository.deleteById(id);
    }
}
