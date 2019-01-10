import { Moment } from 'moment';
import { IPessoa } from 'app/shared/model//pessoa.model';
import { IAluno } from 'app/shared/model//aluno.model';

export interface IResponsavel {
    id?: number;
    data?: Moment;
    obs?: string;
    pessoa?: IPessoa;
    alunos?: IAluno[];
}

export class Responsavel implements IResponsavel {
    constructor(public id?: number, public data?: Moment, public obs?: string, public pessoa?: IPessoa, public alunos?: IAluno[]) {}
}
