import { IFotoAcompanhamentoAluno } from 'app/shared/model//foto-acompanhamento-aluno.model';
import { ITipoAcompanhamentoAluno } from 'app/shared/model//tipo-acompanhamento-aluno.model';
import { IAluno } from 'app/shared/model//aluno.model';

export interface IAcompanhamentoAluno {
    id?: number;
    nome?: string;
    numero?: string;
    obs?: string;
    fotoAcompanhamentoAluno?: IFotoAcompanhamentoAluno;
    tipoAcompanhamentoAluno?: ITipoAcompanhamentoAluno;
    aluno?: IAluno;
}

export class AcompanhamentoAluno implements IAcompanhamentoAluno {
    constructor(
        public id?: number,
        public nome?: string,
        public numero?: string,
        public obs?: string,
        public fotoAcompanhamentoAluno?: IFotoAcompanhamentoAluno,
        public tipoAcompanhamentoAluno?: ITipoAcompanhamentoAluno,
        public aluno?: IAluno
    ) {}
}
