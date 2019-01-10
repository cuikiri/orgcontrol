import { IBimestreAcompanhamento } from 'app/shared/model//bimestre-acompanhamento.model';
import { IAcompanhamentoEscolarAluno } from 'app/shared/model//acompanhamento-escolar-aluno.model';

export interface IMateriaAcompanhamento {
    id?: number;
    nome?: string;
    obs?: string;
    bimestreAcompanhamentos?: IBimestreAcompanhamento[];
    acompanhamentoEscolarAluno?: IAcompanhamentoEscolarAluno;
}

export class MateriaAcompanhamento implements IMateriaAcompanhamento {
    constructor(
        public id?: number,
        public nome?: string,
        public obs?: string,
        public bimestreAcompanhamentos?: IBimestreAcompanhamento[],
        public acompanhamentoEscolarAluno?: IAcompanhamentoEscolarAluno
    ) {}
}
