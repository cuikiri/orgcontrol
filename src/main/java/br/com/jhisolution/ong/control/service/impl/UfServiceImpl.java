package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.UfService;
import br.com.jhisolution.ong.control.domain.Uf;
import br.com.jhisolution.ong.control.repository.UfRepository;
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
 * Service Implementation for managing Uf.
 */
@Service
@Transactional
public class UfServiceImpl implements UfService {

    private final Logger log = LoggerFactory.getLogger(UfServiceImpl.class);

    private final UfRepository ufRepository;

    public UfServiceImpl(UfRepository ufRepository) {
        this.ufRepository = ufRepository;
    }

    /**
     * Save a uf.
     *
     * @param uf the entity to save
     * @return the persisted entity
     */
    @Override
    public Uf save(Uf uf) {
        log.debug("Request to save Uf : {}", uf);
        return ufRepository.save(uf);
    }

    /**
     * Get all the ufs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Uf> findAll(Pageable pageable) {
        log.debug("Request to get all Ufs");
        return ufRepository.findAll(pageable);
    }



    /**
     *  get all the ufs where Endereco is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Uf> findAllWhereEnderecoIsNull() {
        log.debug("Request to get all ufs where Endereco is null");
        return StreamSupport
            .stream(ufRepository.findAll().spliterator(), false)
            .filter(uf -> uf.getEndereco() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one uf by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Uf> findOne(Long id) {
        log.debug("Request to get Uf : {}", id);
        return ufRepository.findById(id);
    }

    /**
     * Delete the uf by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Uf : {}", id);
        ufRepository.deleteById(id);
    }
}
