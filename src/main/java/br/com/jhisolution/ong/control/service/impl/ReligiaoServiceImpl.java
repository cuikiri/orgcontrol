package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ReligiaoService;
import br.com.jhisolution.ong.control.domain.Religiao;
import br.com.jhisolution.ong.control.repository.ReligiaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Religiao.
 */
@Service
@Transactional
public class ReligiaoServiceImpl implements ReligiaoService {

    private final Logger log = LoggerFactory.getLogger(ReligiaoServiceImpl.class);

    private final ReligiaoRepository religiaoRepository;

    public ReligiaoServiceImpl(ReligiaoRepository religiaoRepository) {
        this.religiaoRepository = religiaoRepository;
    }

    /**
     * Save a religiao.
     *
     * @param religiao the entity to save
     * @return the persisted entity
     */
    @Override
    public Religiao save(Religiao religiao) {
        log.debug("Request to save Religiao : {}", religiao);
        return religiaoRepository.save(religiao);
    }

    /**
     * Get all the religiaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Religiao> findAll(Pageable pageable) {
        log.debug("Request to get all Religiaos");
        return religiaoRepository.findAll(pageable);
    }


    /**
     * Get one religiao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Religiao> findOne(Long id) {
        log.debug("Request to get Religiao : {}", id);
        return religiaoRepository.findById(id);
    }

    /**
     * Delete the religiao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Religiao : {}", id);
        religiaoRepository.deleteById(id);
    }
}
