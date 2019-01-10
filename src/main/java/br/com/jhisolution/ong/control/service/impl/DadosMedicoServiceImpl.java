package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.DadosMedicoService;
import br.com.jhisolution.ong.control.domain.DadosMedico;
import br.com.jhisolution.ong.control.repository.DadosMedicoRepository;
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
 * Service Implementation for managing DadosMedico.
 */
@Service
@Transactional
public class DadosMedicoServiceImpl implements DadosMedicoService {

    private final Logger log = LoggerFactory.getLogger(DadosMedicoServiceImpl.class);

    private final DadosMedicoRepository dadosMedicoRepository;

    public DadosMedicoServiceImpl(DadosMedicoRepository dadosMedicoRepository) {
        this.dadosMedicoRepository = dadosMedicoRepository;
    }

    /**
     * Save a dadosMedico.
     *
     * @param dadosMedico the entity to save
     * @return the persisted entity
     */
    @Override
    public DadosMedico save(DadosMedico dadosMedico) {
        log.debug("Request to save DadosMedico : {}", dadosMedico);
        return dadosMedicoRepository.save(dadosMedico);
    }

    /**
     * Get all the dadosMedicos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DadosMedico> findAll(Pageable pageable) {
        log.debug("Request to get all DadosMedicos");
        return dadosMedicoRepository.findAll(pageable);
    }



    /**
     *  get all the dadosMedicos where Colaborador is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<DadosMedico> findAllWhereColaboradorIsNull() {
        log.debug("Request to get all dadosMedicos where Colaborador is null");
        return StreamSupport
            .stream(dadosMedicoRepository.findAll().spliterator(), false)
            .filter(dadosMedico -> dadosMedico.getColaborador() == null)
            .collect(Collectors.toList());
    }


    /**
     *  get all the dadosMedicos where Aluno is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<DadosMedico> findAllWhereAlunoIsNull() {
        log.debug("Request to get all dadosMedicos where Aluno is null");
        return StreamSupport
            .stream(dadosMedicoRepository.findAll().spliterator(), false)
            .filter(dadosMedico -> dadosMedico.getAluno() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one dadosMedico by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DadosMedico> findOne(Long id) {
        log.debug("Request to get DadosMedico : {}", id);
        return dadosMedicoRepository.findById(id);
    }

    /**
     * Delete the dadosMedico by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DadosMedico : {}", id);
        dadosMedicoRepository.deleteById(id);
    }
}
