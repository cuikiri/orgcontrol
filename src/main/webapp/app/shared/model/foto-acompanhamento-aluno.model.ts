import { IAcompanhamentoAluno } from 'app/shared/model//acompanhamento-aluno.model';

export interface IFotoAcompanhamentoAluno {
    id?: number;
    fotoContentType?: string;
    foto?: any;
    acompanhamentoAluno?: IAcompanhamentoAluno;
}

export class FotoAcompanhamentoAluno implements IFotoAcompanhamentoAluno {
    constructor(
        public id?: number,
        public fotoContentType?: string,
        public foto?: any,
        public acompanhamentoAluno?: IAcompanhamentoAluno
    ) {}
}
