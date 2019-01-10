import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { OrgcontrolLocalizacaoModule } from './localizacao/localizacao.module';
import { OrgcontrolUfModule } from './uf/uf.module';
import { OrgcontrolEnderecoModule } from './endereco/endereco.module';
import { OrgcontrolTelefoneModule } from './telefone/telefone.module';
import { OrgcontrolEmailModule } from './email/email.module';
import { OrgcontrolPessoaModule } from './pessoa/pessoa.module';
import { OrgcontrolAvisoModule } from './aviso/aviso.module';
import { OrgcontrolDocumentoModule } from './documento/documento.module';
import { OrgcontrolFotoDocumentoModule } from './foto-documento/foto-documento.module';
import { OrgcontrolTipoDocumentoModule } from './tipo-documento/tipo-documento.module';
import { OrgcontrolInstituicaoModule } from './instituicao/instituicao.module';
import { OrgcontrolUnidadeModule } from './unidade/unidade.module';
import { OrgcontrolTipoUnidadeModule } from './tipo-unidade/tipo-unidade.module';
import { OrgcontrolBlocoModule } from './bloco/bloco.module';
import { OrgcontrolTipoBlocoModule } from './tipo-bloco/tipo-bloco.module';
import { OrgcontrolParteBlocoModule } from './parte-bloco/parte-bloco.module';
import { OrgcontrolTipoParteBlocoModule } from './tipo-parte-bloco/tipo-parte-bloco.module';
import { OrgcontrolEleicaoModule } from './eleicao/eleicao.module';
import { OrgcontrolChapaModule } from './chapa/chapa.module';
import { OrgcontrolCandidatoModule } from './candidato/candidato.module';
import { OrgcontrolCargoModule } from './cargo/cargo.module';
import { OrgcontrolCalendarioInstituicaoModule } from './calendario-instituicao/calendario-instituicao.module';
import { OrgcontrolPeriodoDuracaoModule } from './periodo-duracao/periodo-duracao.module';
import { OrgcontrolPlanejamentoInstituicaoModule } from './planejamento-instituicao/planejamento-instituicao.module';
import { OrgcontrolItemPlanejamentoInstituicaoModule } from './item-planejamento-instituicao/item-planejamento-instituicao.module';
import { OrgcontrolPlanejamentoEnsinoModule } from './planejamento-ensino/planejamento-ensino.module';
import { OrgcontrolItemPlanejamentoEnsinoModule } from './item-planejamento-ensino/item-planejamento-ensino.module';
import { OrgcontrolPlanejamentoAtividadeModule } from './planejamento-atividade/planejamento-atividade.module';
import { OrgcontrolItemPlanejamentoAtividadeModule } from './item-planejamento-atividade/item-planejamento-atividade.module';
import { OrgcontrolDiaNaoUtilModule } from './dia-nao-util/dia-nao-util.module';
import { OrgcontrolMotivoDiaNaoUtilModule } from './motivo-dia-nao-util/motivo-dia-nao-util.module';
import { OrgcontrolColaboradorModule } from './colaborador/colaborador.module';
import { OrgcontrolAgendaColaboradorModule } from './agenda-colaborador/agenda-colaborador.module';
import { OrgcontrolTipoColaboradorModule } from './tipo-colaborador/tipo-colaborador.module';
import { OrgcontrolTipoContratacaoModule } from './tipo-contratacao/tipo-contratacao.module';
import { OrgcontrolEstadoCivilModule } from './estado-civil/estado-civil.module';
import { OrgcontrolDependenteLegalModule } from './dependente-legal/dependente-legal.module';
import { OrgcontrolDadosMedicoModule } from './dados-medico/dados-medico.module';
import { OrgcontrolVacinaModule } from './vacina/vacina.module';
import { OrgcontrolDadoBiologicoModule } from './dado-biologico/dado-biologico.module';
import { OrgcontrolTipoadoBiologicoModule } from './tipoado-biologico/tipoado-biologico.module';
import { OrgcontrolBiotipoModule } from './biotipo/biotipo.module';
import { OrgcontrolTipoBiotipoModule } from './tipo-biotipo/tipo-biotipo.module';
import { OrgcontrolExameMedicoModule } from './exame-medico/exame-medico.module';
import { OrgcontrolDoencaModule } from './doenca/doenca.module';
import { OrgcontrolProblemaFisicoModule } from './problema-fisico/problema-fisico.module';
import { OrgcontrolEnsinoModule } from './ensino/ensino.module';
import { OrgcontrolDiaSemanaModule } from './dia-semana/dia-semana.module';
import { OrgcontrolResponsavelModule } from './responsavel/responsavel.module';
import { OrgcontrolAlunoModule } from './aluno/aluno.module';
import { OrgcontrolCaracteristicasPsiquicasModule } from './caracteristicas-psiquicas/caracteristicas-psiquicas.module';
import { OrgcontrolLocomocaoModule } from './locomocao/locomocao.module';
import { OrgcontrolSexoModule } from './sexo/sexo.module';
import { OrgcontrolRacaModule } from './raca/raca.module';
import { OrgcontrolReligiaoModule } from './religiao/religiao.module';
import { OrgcontrolAcompanhamentoAlunoModule } from './acompanhamento-aluno/acompanhamento-aluno.module';
import { OrgcontrolTipoAcompanhamentoAlunoModule } from './tipo-acompanhamento-aluno/tipo-acompanhamento-aluno.module';
import { OrgcontrolFotoAcompanhamentoAlunoModule } from './foto-acompanhamento-aluno/foto-acompanhamento-aluno.module';
import { OrgcontrolAcompanhamentoEscolarAlunoModule } from './acompanhamento-escolar-aluno/acompanhamento-escolar-aluno.module';
import { OrgcontrolMateriaAcompanhamentoModule } from './materia-acompanhamento/materia-acompanhamento.module';
import { OrgcontrolBimestreAcompanhamentoModule } from './bimestre-acompanhamento/bimestre-acompanhamento.module';
import { OrgcontrolConceitoAcompanhamentoModule } from './conceito-acompanhamento/conceito-acompanhamento.module';
import { OrgcontrolAdvertenciaModule } from './advertencia/advertencia.module';
import { OrgcontrolAvaliacaoEconomicaModule } from './avaliacao-economica/avaliacao-economica.module';
import { OrgcontrolTipoAvaliacaoEconomicaModule } from './tipo-avaliacao-economica/tipo-avaliacao-economica.module';
import { OrgcontrolItemAvaliacaoEconomicaModule } from './item-avaliacao-economica/item-avaliacao-economica.module';
import { OrgcontrolRespostaAvaliacaoEconomicaModule } from './resposta-avaliacao-economica/resposta-avaliacao-economica.module';
import { OrgcontrolRespAvalDescritivaEconomicaModule } from './resp-aval-descritiva-economica/resp-aval-descritiva-economica.module';
import { OrgcontrolRespAvalOptativaEconomicaModule } from './resp-aval-optativa-economica/resp-aval-optativa-economica.module';
import { OrgcontrolOpcaoRespAvalOptativaEconomicaModule } from './opcao-resp-aval-optativa-economica/opcao-resp-aval-optativa-economica.module';
import { OrgcontrolConceitoModule } from './conceito/conceito.module';
import { OrgcontrolTipoConceitoModule } from './tipo-conceito/tipo-conceito.module';
import { OrgcontrolTurmaModule } from './turma/turma.module';
import { OrgcontrolModuloModule } from './modulo/modulo.module';
import { OrgcontrolCursoModule } from './curso/curso.module';
import { OrgcontrolTipoCursoModule } from './tipo-curso/tipo-curso.module';
import { OrgcontrolPeriodoSemanaModule } from './periodo-semana/periodo-semana.module';
import { OrgcontrolHorarioMateriaModule } from './horario-materia/horario-materia.module';
import { OrgcontrolPeriodoAtividadeModule } from './periodo-atividade/periodo-atividade.module';
import { OrgcontrolMateriaModule } from './materia/materia.module';
import { OrgcontrolDiarioModule } from './diario/diario.module';
import { OrgcontrolObservacaoProfessorModule } from './observacao-professor/observacao-professor.module';
import { OrgcontrolObservacaoCoordenadorModule } from './observacao-coordenador/observacao-coordenador.module';
import { OrgcontrolRegistroRecuperacaoModule } from './registro-recuperacao/registro-recuperacao.module';
import { OrgcontrolGeneralidadeModule } from './generalidade/generalidade.module';
import { OrgcontrolAnotacaoModule } from './anotacao/anotacao.module';
import { OrgcontrolMatriculaModule } from './matricula/matricula.module';
import { OrgcontrolAtividadeModule } from './atividade/atividade.module';
import { OrgcontrolTipoAtividadeModule } from './tipo-atividade/tipo-atividade.module';
import { OrgcontrolConteudoProgramaticoModule } from './conteudo-programatico/conteudo-programatico.module';
import { OrgcontrolAcompanhamentoAtividadeModule } from './acompanhamento-atividade/acompanhamento-atividade.module';
import { OrgcontrolTipoAcompanhamentoAtividadeModule } from './tipo-acompanhamento-atividade/tipo-acompanhamento-atividade.module';
import { OrgcontrolItemAcompanhamentoAtividadeModule } from './item-acompanhamento-atividade/item-acompanhamento-atividade.module';
import { OrgcontrolRespostaAtividadeModule } from './resposta-atividade/resposta-atividade.module';
import { OrgcontrolRespAtivDescritivaModule } from './resp-ativ-descritiva/resp-ativ-descritiva.module';
import { OrgcontrolRespAtivOptativaModule } from './resp-ativ-optativa/resp-ativ-optativa.module';
import { OrgcontrolOpcaoRespAtividadeModule } from './opcao-resp-atividade/opcao-resp-atividade.module';
import { OrgcontrolBimestreModule } from './bimestre/bimestre.module';
import { OrgcontrolFechamentoBimestreModule } from './fechamento-bimestre/fechamento-bimestre.module';
import { OrgcontrolAvaliacaoModule } from './avaliacao/avaliacao.module';
import { OrgcontrolTipoAvaliacaoModule } from './tipo-avaliacao/tipo-avaliacao.module';
import { OrgcontrolItemAvaliacaoModule } from './item-avaliacao/item-avaliacao.module';
import { OrgcontrolRespostaAvaliacaoModule } from './resposta-avaliacao/resposta-avaliacao.module';
import { OrgcontrolRespAvalDescritivaModule } from './resp-aval-descritiva/resp-aval-descritiva.module';
import { OrgcontrolRespAvalOptativaModule } from './resp-aval-optativa/resp-aval-optativa.module';
import { OrgcontrolOpcaoRespAvalOptativaModule } from './opcao-resp-aval-optativa/opcao-resp-aval-optativa.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        OrgcontrolLocalizacaoModule,
        OrgcontrolUfModule,
        OrgcontrolEnderecoModule,
        OrgcontrolTelefoneModule,
        OrgcontrolEmailModule,
        OrgcontrolPessoaModule,
        OrgcontrolAvisoModule,
        OrgcontrolDocumentoModule,
        OrgcontrolFotoDocumentoModule,
        OrgcontrolTipoDocumentoModule,
        OrgcontrolInstituicaoModule,
        OrgcontrolUnidadeModule,
        OrgcontrolTipoUnidadeModule,
        OrgcontrolBlocoModule,
        OrgcontrolTipoBlocoModule,
        OrgcontrolParteBlocoModule,
        OrgcontrolTipoParteBlocoModule,
        OrgcontrolEleicaoModule,
        OrgcontrolChapaModule,
        OrgcontrolCandidatoModule,
        OrgcontrolCargoModule,
        OrgcontrolCalendarioInstituicaoModule,
        OrgcontrolPeriodoDuracaoModule,
        OrgcontrolPlanejamentoInstituicaoModule,
        OrgcontrolItemPlanejamentoInstituicaoModule,
        OrgcontrolPlanejamentoEnsinoModule,
        OrgcontrolItemPlanejamentoEnsinoModule,
        OrgcontrolPlanejamentoAtividadeModule,
        OrgcontrolItemPlanejamentoAtividadeModule,
        OrgcontrolDiaNaoUtilModule,
        OrgcontrolMotivoDiaNaoUtilModule,
        OrgcontrolColaboradorModule,
        OrgcontrolAgendaColaboradorModule,
        OrgcontrolTipoColaboradorModule,
        OrgcontrolTipoContratacaoModule,
        OrgcontrolEstadoCivilModule,
        OrgcontrolDependenteLegalModule,
        OrgcontrolDadosMedicoModule,
        OrgcontrolVacinaModule,
        OrgcontrolDadoBiologicoModule,
        OrgcontrolTipoadoBiologicoModule,
        OrgcontrolBiotipoModule,
        OrgcontrolTipoBiotipoModule,
        OrgcontrolExameMedicoModule,
        OrgcontrolDoencaModule,
        OrgcontrolProblemaFisicoModule,
        OrgcontrolEnsinoModule,
        OrgcontrolDiaSemanaModule,
        OrgcontrolResponsavelModule,
        OrgcontrolAlunoModule,
        OrgcontrolCaracteristicasPsiquicasModule,
        OrgcontrolLocomocaoModule,
        OrgcontrolSexoModule,
        OrgcontrolRacaModule,
        OrgcontrolReligiaoModule,
        OrgcontrolAcompanhamentoAlunoModule,
        OrgcontrolTipoAcompanhamentoAlunoModule,
        OrgcontrolFotoAcompanhamentoAlunoModule,
        OrgcontrolAcompanhamentoEscolarAlunoModule,
        OrgcontrolMateriaAcompanhamentoModule,
        OrgcontrolBimestreAcompanhamentoModule,
        OrgcontrolConceitoAcompanhamentoModule,
        OrgcontrolAdvertenciaModule,
        OrgcontrolAvaliacaoEconomicaModule,
        OrgcontrolTipoAvaliacaoEconomicaModule,
        OrgcontrolItemAvaliacaoEconomicaModule,
        OrgcontrolRespostaAvaliacaoEconomicaModule,
        OrgcontrolRespAvalDescritivaEconomicaModule,
        OrgcontrolRespAvalOptativaEconomicaModule,
        OrgcontrolOpcaoRespAvalOptativaEconomicaModule,
        OrgcontrolConceitoModule,
        OrgcontrolTipoConceitoModule,
        OrgcontrolTurmaModule,
        OrgcontrolModuloModule,
        OrgcontrolCursoModule,
        OrgcontrolTipoCursoModule,
        OrgcontrolPeriodoSemanaModule,
        OrgcontrolHorarioMateriaModule,
        OrgcontrolPeriodoAtividadeModule,
        OrgcontrolMateriaModule,
        OrgcontrolDiarioModule,
        OrgcontrolObservacaoProfessorModule,
        OrgcontrolObservacaoCoordenadorModule,
        OrgcontrolRegistroRecuperacaoModule,
        OrgcontrolGeneralidadeModule,
        OrgcontrolAnotacaoModule,
        OrgcontrolMatriculaModule,
        OrgcontrolAtividadeModule,
        OrgcontrolTipoAtividadeModule,
        OrgcontrolConteudoProgramaticoModule,
        OrgcontrolAcompanhamentoAtividadeModule,
        OrgcontrolTipoAcompanhamentoAtividadeModule,
        OrgcontrolItemAcompanhamentoAtividadeModule,
        OrgcontrolRespostaAtividadeModule,
        OrgcontrolRespAtivDescritivaModule,
        OrgcontrolRespAtivOptativaModule,
        OrgcontrolOpcaoRespAtividadeModule,
        OrgcontrolBimestreModule,
        OrgcontrolFechamentoBimestreModule,
        OrgcontrolAvaliacaoModule,
        OrgcontrolTipoAvaliacaoModule,
        OrgcontrolItemAvaliacaoModule,
        OrgcontrolRespostaAvaliacaoModule,
        OrgcontrolRespAvalDescritivaModule,
        OrgcontrolRespAvalOptativaModule,
        OrgcontrolOpcaoRespAvalOptativaModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolEntityModule {}
