package br.com.jhisolution.ong.control.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(br.com.jhisolution.ong.control.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Localizacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Uf.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Endereco.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Telefone.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Email.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Pessoa.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Pessoa.class.getName() + ".telefones", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Pessoa.class.getName() + ".emails", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Pessoa.class.getName() + ".enderecos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Pessoa.class.getName() + ".documentos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Pessoa.class.getName() + ".avisos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Aviso.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Aviso.class.getName() + ".pessoas", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Documento.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.FotoDocumento.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.TipoDocumento.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Instituicao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Instituicao.class.getName() + ".unidades", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Instituicao.class.getName() + ".eleicaos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Instituicao.class.getName() + ".colaboradors", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Instituicao.class.getName() + ".calendarioInstituicaos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Unidade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Unidade.class.getName() + ".alunos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Unidade.class.getName() + ".blocos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Unidade.class.getName() + ".emails", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Unidade.class.getName() + ".eleicaos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Unidade.class.getName() + ".telefones", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Unidade.class.getName() + ".colaboradors", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Unidade.class.getName() + ".calendarioInstituicaos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.TipoUnidade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Bloco.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Bloco.class.getName() + ".parteBlocos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.TipoBloco.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ParteBloco.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.TipoParteBloco.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Eleicao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Eleicao.class.getName() + ".chapas", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Chapa.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Chapa.class.getName() + ".candidatoes", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Candidato.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Cargo.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.CalendarioInstituicao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.CalendarioInstituicao.class.getName() + ".diaNaoUtils", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.CalendarioInstituicao.class.getName() + ".periodoDuracaos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.CalendarioInstituicao.class.getName() + ".planejamentoInstituicaos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.CalendarioInstituicao.class.getName() + ".planejamentoEnsinos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.CalendarioInstituicao.class.getName() + ".planejamentoAtividades", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.PeriodoDuracao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.PlanejamentoInstituicao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.PlanejamentoInstituicao.class.getName() + ".itemPlanejamentoInstituicaos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ItemPlanejamentoInstituicao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.PlanejamentoEnsino.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.PlanejamentoEnsino.class.getName() + ".itemPlanejamentoEnsinos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ItemPlanejamentoEnsino.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.PlanejamentoAtividade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.PlanejamentoAtividade.class.getName() + ".itemPlanejamentoAtividades", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ItemPlanejamentoAtividade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.DiaNaoUtil.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.DiaNaoUtil.class.getName() + ".motivoDiaNaoUtils", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.MotivoDiaNaoUtil.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Colaborador.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Colaborador.class.getName() + ".ensinos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Colaborador.class.getName() + ".documentos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Colaborador.class.getName() + ".dependenteLegals", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Colaborador.class.getName() + ".agendaColaboradors", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.AgendaColaborador.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.TipoColaborador.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.TipoContratacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.EstadoCivil.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.DependenteLegal.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.DependenteLegal.class.getName() + ".documentos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.DadosMedico.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.DadosMedico.class.getName() + ".problemaFisicos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.DadosMedico.class.getName() + ".vacinas", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.DadosMedico.class.getName() + ".exameMedicos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Vacina.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.DadoBiologico.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.TipoadoBiologico.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Biotipo.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.TipoBiotipo.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ExameMedico.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ExameMedico.class.getName() + ".biotipos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ExameMedico.class.getName() + ".doencas", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Doenca.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Doenca.class.getName() + ".exameMedicos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ProblemaFisico.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Ensino.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.DiaSemana.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Responsavel.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Responsavel.class.getName() + ".alunos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Aluno.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Aluno.class.getName() + ".irmaos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Aluno.class.getName() + ".locomocaos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Aluno.class.getName() + ".advertencias", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Aluno.class.getName() + ".avaliacaoEconomicas", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Aluno.class.getName() + ".acompanhamentoAlunos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Aluno.class.getName() + ".acompanhamentoEscolarAlunos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Aluno.class.getName() + ".caracteristicasPsiquicas", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Aluno.class.getName() + ".responsavels", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Aluno.class.getName() + ".atividades", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Aluno.class.getName() + ".fechamentoBimestres", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.CaracteristicasPsiquicas.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Locomocao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Sexo.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Raca.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Religiao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.AcompanhamentoAluno.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.TipoAcompanhamentoAluno.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.FotoAcompanhamentoAluno.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.AcompanhamentoEscolarAluno.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.AcompanhamentoEscolarAluno.class.getName() + ".materiaAcompanhamentos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.MateriaAcompanhamento.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.MateriaAcompanhamento.class.getName() + ".bimestreAcompanhamentos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.BimestreAcompanhamento.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.BimestreAcompanhamento.class.getName() + ".conceitoAcompanhamentos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ConceitoAcompanhamento.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Advertencia.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.AvaliacaoEconomica.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.AvaliacaoEconomica.class.getName() + ".itemAvaliacaoEconomicas", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.TipoAvaliacaoEconomica.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ItemAvaliacaoEconomica.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ItemAvaliacaoEconomica.class.getName() + ".respostaAvaliacaoEconomicas", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.RespostaAvaliacaoEconomica.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.RespAvalDescritivaEconomica.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.RespAvalOptativaEconomica.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.RespAvalOptativaEconomica.class.getName() + ".opcaoRespostas", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.OpcaoRespAvalOptativaEconomica.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Conceito.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.TipoConceito.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Turma.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Turma.class.getName() + ".diarios", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Turma.class.getName() + ".horarioMaterias", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Turma.class.getName() + ".periodoAtividades", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Modulo.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Curso.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.TipoCurso.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.PeriodoSemana.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.PeriodoSemana.class.getName() + ".diaSemanas", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.HorarioMateria.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.HorarioMateria.class.getName() + ".turmas", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.PeriodoAtividade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.PeriodoAtividade.class.getName() + ".turmas", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Materia.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Diario.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Diario.class.getName() + ".generalidades", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Diario.class.getName() + ".anotacaos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Diario.class.getName() + ".alunos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Diario.class.getName() + ".atividades", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Diario.class.getName() + ".bimestres", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ObservacaoProfessor.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ObservacaoCoordenador.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.RegistroRecuperacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Generalidade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Anotacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Matricula.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Atividade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Atividade.class.getName() + ".conteudoProgramaticos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Atividade.class.getName() + ".acompanhamentoAtividades", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Atividade.class.getName() + ".alunos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.TipoAtividade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ConteudoProgramatico.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.AcompanhamentoAtividade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.AcompanhamentoAtividade.class.getName() + ".itemAcompanhamentoAtividades", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.TipoAcompanhamentoAtividade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ItemAcompanhamentoAtividade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ItemAcompanhamentoAtividade.class.getName() + ".respostaAtividades", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.RespostaAtividade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.RespAtivDescritiva.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.RespAtivOptativa.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.RespAtivOptativa.class.getName() + ".opcaoRespostas", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.OpcaoRespAtividade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Bimestre.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Bimestre.class.getName() + ".atividades", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Bimestre.class.getName() + ".generalidades", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Bimestre.class.getName() + ".observacaoProfessors", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Bimestre.class.getName() + ".observacaoCoordenadors", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Bimestre.class.getName() + ".registroRecuperacaos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.FechamentoBimestre.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.FechamentoBimestre.class.getName() + ".avaliacaos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.FechamentoBimestre.class.getName() + ".alunos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Avaliacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.Avaliacao.class.getName() + ".itemAvaliacaos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.TipoAvaliacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ItemAvaliacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.ItemAvaliacao.class.getName() + ".respostaAvaliacaos", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.RespostaAvaliacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.RespAvalDescritiva.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.RespAvalOptativa.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.RespAvalOptativa.class.getName() + ".opcaoRespostas", jcacheConfiguration);
            cm.createCache(br.com.jhisolution.ong.control.domain.OpcaoRespAvalOptativa.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
