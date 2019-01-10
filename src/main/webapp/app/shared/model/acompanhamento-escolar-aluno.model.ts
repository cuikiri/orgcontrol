import { IMateriaAcompanhamento } from 'app/shared/model//materia-acompanhamento.model';
import { IAluno } from 'app/shared/model//aluno.model';

export interface IAcompanhamentoEscolarAluno {
    id?: number;
    nome?: string;
    obs?: string;
    materiaAcompanhamentos?: IMateriaAcompanhamento[];
    aluno?: IAluno;
}

export class AcompanhamentoEscolarAluno implements IAcompanhamentoEscolarAluno {
    constructor(
        public id?: number,
        public nome?: string,
        public obs?: string,
        public materiaAcompanhamentos?: IMateriaAcompanhamento[],
        public aluno?: IAluno
    ) {}
}
