import { IAcompanhamentoAluno } from 'app/shared/model//acompanhamento-aluno.model';

export interface ITipoAcompanhamentoAluno {
    id?: number;
    nome?: string;
    acompanhamentoAluno?: IAcompanhamentoAluno;
}

export class TipoAcompanhamentoAluno implements ITipoAcompanhamentoAluno {
    constructor(public id?: number, public nome?: string, public acompanhamentoAluno?: IAcompanhamentoAluno) {}
}
