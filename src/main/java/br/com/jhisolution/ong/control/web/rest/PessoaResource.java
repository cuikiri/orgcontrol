package br.com.jhisolution.ong.control.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.jhisolution.ong.control.domain.Pessoa;
import br.com.jhisolution.ong.control.domain.User;
import br.com.jhisolution.ong.control.repository.UserRepository;
import br.com.jhisolution.ong.control.security.SecurityUtils;
import br.com.jhisolution.ong.control.service.PessoaService;
import br.com.jhisolution.ong.control.web.rest.dto.PessoaDTO;
import br.com.jhisolution.ong.control.web.rest.errors.BadRequestAlertException;
import br.com.jhisolution.ong.control.web.rest.util.HeaderUtil;
import br.com.jhisolution.ong.control.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Pessoa.
 */
@RestController
@RequestMapping("/api")
public class PessoaResource {

    private final Logger log = LoggerFactory.getLogger(PessoaResource.class);

    private static final String ENTITY_NAME = "pessoa";

    private final PessoaService pessoaService;
    
    private final UserRepository userRepository;

    public PessoaResource(PessoaService pessoaService, UserRepository userRepository) {
        this.pessoaService  = pessoaService;
        this.userRepository = userRepository;
    }

    /**
     * POST  /pessoas : Create a new pessoa.
     *
     * @param pessoa the pessoa to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pessoa, or with status 400 (Bad Request) if the pessoa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pessoas")
    @Timed
    public ResponseEntity<Pessoa> createPessoa(@Valid @RequestBody Pessoa pessoa) throws URISyntaxException {
        log.debug("REST request to save Pessoa : {}", pessoa);
        
        Pessoa result = pessoa; 
        
        if (pessoa.getId() != null) {
            throw new BadRequestAlertException("A new pessoa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        Optional<User> user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
        		
        if (user.isPresent()) {
        	User u = user.get();
        	pessoa.setUser(u);
    		u.setPessoa(pessoa);
    		userRepository.save(u);
        }

        result.setId(0L);
        return ResponseEntity.created(new URI("/api/pessoas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pessoas : Updates an existing pessoa.
     *
     * @param pessoa the pessoa to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pessoa,
     * or with status 400 (Bad Request) if the pessoa is not valid,
     * or with status 500 (Internal Server Error) if the pessoa couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pessoas")
    @Timed
    public ResponseEntity<Pessoa> updatePessoa(@Valid @RequestBody Pessoa pessoa) throws URISyntaxException {
        log.debug("REST request to update Pessoa : {}", pessoa);
        if (pessoa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Pessoa result = pessoaService.save(pessoa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pessoa.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pessoas : get all the pessoas.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of pessoas in body
     */
    @GetMapping("/pessoas")
    @Timed
    public ResponseEntity<List<Pessoa>> getAllPessoas(Pageable pageable, @RequestParam(required = false) String filter, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        if ("colaborador-is-null".equals(filter)) {
            log.debug("REST request to get all Pessoas where colaborador is null");
            return new ResponseEntity<>(pessoaService.findAllWhereColaboradorIsNull(),
                    HttpStatus.OK);
        }
        if ("responsavel-is-null".equals(filter)) {
            log.debug("REST request to get all Pessoas where responsavel is null");
            return new ResponseEntity<>(pessoaService.findAllWhereResponsavelIsNull(),
                    HttpStatus.OK);
        }
        if ("aluno-is-null".equals(filter)) {
            log.debug("REST request to get all Pessoas where aluno is null");
            return new ResponseEntity<>(pessoaService.findAllWhereAlunoIsNull(),
                    HttpStatus.OK);
        }
        if ("alunomae-is-null".equals(filter)) {
            log.debug("REST request to get all Pessoas where alunoMae is null");
            return new ResponseEntity<>(pessoaService.findAllWhereAlunoMaeIsNull(),
                    HttpStatus.OK);
        }
        if ("alunopai-is-null".equals(filter)) {
            log.debug("REST request to get all Pessoas where alunoPai is null");
            return new ResponseEntity<>(pessoaService.findAllWhereAlunoPaiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of Pessoas");
        Page<Pessoa> page;
        if (eagerload) {
            page = pessoaService.findAllWithEagerRelationships(pageable);
        } else {
            page = pessoaService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/pessoas?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /pessoas/:id : get the "id" pessoa.
     *
     * @param id the id of the pessoa to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pessoa, or with status 404 (Not Found)
     */
    @GetMapping("/pessoas/{id}")
    @Timed
    public ResponseEntity<Pessoa> getPessoa(@PathVariable Long id) {
        log.debug("REST request to get Pessoa : {}", id);
        Optional<Pessoa> pessoa = pessoaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pessoa);
    }

    /**
     * DELETE  /pessoas/:id : delete the "id" pessoa.
     *
     * @param id the id of the pessoa to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pessoas/{id}")
    @Timed
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        log.debug("REST request to delete Pessoa : {}", id);
        pessoaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * GET  /pessoas/user
     *
     * @param id the id of the pessoa to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pessoa, or with status 404 (Not Found)
     */
    @GetMapping("/pessoas/user")
    @Timed
    public ResponseEntity<PessoaDTO> getPessoa() {
    	
    	Optional<User> usuario = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
    	
    	PessoaDTO pessoaDto = null;
    	
    	if (usuario.isPresent()) {
    		
    		Pessoa pessoa = pessoaService.findOneByUser(usuario.get().getId());
            pessoaDto = PessoaDTO.getInstance(pessoa);
    		
    	}
    	
    	return Optional.ofNullable(pessoaDto)
                .map(result -> new ResponseEntity<>(
                    result,
                    HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        
    }
}
