import { IMateria } from 'app/shared/model//materia.model';
import { IObservacaoProfessor } from 'app/shared/model//observacao-professor.model';
import { IObservacaoCoordenador } from 'app/shared/model//observacao-coordenador.model';
import { IRegistroRecuperacao } from 'app/shared/model//registro-recuperacao.model';
import { IColaborador } from 'app/shared/model//colaborador.model';
import { IGeneralidade } from 'app/shared/model//generalidade.model';
import { IAnotacao } from 'app/shared/model//anotacao.model';
import { IAluno } from 'app/shared/model//aluno.model';
import { IAtividade } from 'app/shared/model//atividade.model';
import { IBimestre } from 'app/shared/model//bimestre.model';
import { ITurma } from 'app/shared/model//turma.model';

export interface IDiario {
    id?: number;
    nome?: string;
    obs?: string;
    materia?: IMateria;
    observacaoProfessor?: IObservacaoProfessor;
    observacaoCoordenador?: IObservacaoCoordenador;
    registroRecuperacao?: IRegistroRecuperacao;
    colaborador?: IColaborador;
    generalidades?: IGeneralidade[];
    anotacaos?: IAnotacao[];
    alunos?: IAluno[];
    atividades?: IAtividade[];
    bimestres?: IBimestre[];
    turma?: ITurma;
}

export class Diario implements IDiario {
    constructor(
        public id?: number,
        public nome?: string,
        public obs?: string,
        public materia?: IMateria,
        public observacaoProfessor?: IObservacaoProfessor,
        public observacaoCoordenador?: IObservacaoCoordenador,
        public registroRecuperacao?: IRegistroRecuperacao,
        public colaborador?: IColaborador,
        public generalidades?: IGeneralidade[],
        public anotacaos?: IAnotacao[],
        public alunos?: IAluno[],
        public atividades?: IAtividade[],
        public bimestres?: IBimestre[],
        public turma?: ITurma
    ) {}
}
