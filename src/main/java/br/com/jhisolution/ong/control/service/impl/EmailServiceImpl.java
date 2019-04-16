package br.com.jhisolution.ong.control.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jhisolution.ong.control.domain.Email;
import br.com.jhisolution.ong.control.domain.Pessoa;
import br.com.jhisolution.ong.control.repository.EmailRepository;
import br.com.jhisolution.ong.control.service.EmailService;

/**
 * Service Implementation for managing Email.
 */
@Service
@Transactional
public class EmailServiceImpl implements EmailService {

    private final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final EmailRepository emailRepository;

    public EmailServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    /**
     * Save a email.
     *
     * @param email the entity to save
     * @return the persisted entity
     */
    @Override
    public Email save(Email email) {
        log.debug("Request to save Email : {}", email);
        return emailRepository.save(email);
    }

    /**
     * Get all the emails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Email> findAll(Pageable pageable) {
        log.debug("Request to get all Emails");
        return emailRepository.findAll(pageable);
    }
    
    /**
     * Get all the emails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Email> findAllByPessoa(Pageable pageable, Pessoa pessoa) {
        log.debug("Request to get all Emails");
        return emailRepository.findAllByPessoa(pageable, pessoa);
    }


    /**
     * Get one email by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Email> findOne(Long id) {
        log.debug("Request to get Email : {}", id);
        return emailRepository.findById(id);
    }

    /**
     * Delete the email by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Email : {}", id);
        emailRepository.deleteById(id);
    }
}
