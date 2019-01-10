package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ColaboradorService;
import br.com.jhisolution.ong.control.domain.Colaborador;
import br.com.jhisolution.ong.control.repository.ColaboradorRepository;
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
 * Service Implementation for managing Colaborador.
 */
@Service
@Transactional
public class ColaboradorServiceImpl implements ColaboradorService {

    private final Logger log = LoggerFactory.getLogger(ColaboradorServiceImpl.class);

    private final ColaboradorRepository colaboradorRepository;

    public ColaboradorServiceImpl(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    /**
     * Save a colaborador.
     *
     * @param colaborador the entity to save
     * @return the persisted entity
     */
    @Override
    public Colaborador save(Colaborador colaborador) {
        log.debug("Request to save Colaborador : {}", colaborador);
        return colaboradorRepository.save(colaborador);
    }

    /**
     * Get all the colaboradors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Colaborador> findAll(Pageable pageable) {
        log.debug("Request to get all Colaboradors");
        return colaboradorRepository.findAll(pageable);
    }



    /**
     *  get all the colaboradors where Candidato is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Colaborador> findAllWhereCandidatoIsNull() {
        log.debug("Request to get all colaboradors where Candidato is null");
        return StreamSupport
            .stream(colaboradorRepository.findAll().spliterator(), false)
            .filter(colaborador -> colaborador.getCandidato() == null)
            .collect(Collectors.toList());
    }


    /**
     *  get all the colaboradors where Diario is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Colaborador> findAllWhereDiarioIsNull() {
        log.debug("Request to get all colaboradors where Diario is null");
        return StreamSupport
            .stream(colaboradorRepository.findAll().spliterator(), false)
            .filter(colaborador -> colaborador.getDiario() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one colaborador by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Colaborador> findOne(Long id) {
        log.debug("Request to get Colaborador : {}", id);
        return colaboradorRepository.findById(id);
    }

    /**
     * Delete the colaborador by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Colaborador : {}", id);
        colaboradorRepository.deleteById(id);
    }
}
