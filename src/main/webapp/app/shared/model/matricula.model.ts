import { Moment } from 'moment';
import { IAluno } from 'app/shared/model//aluno.model';
import { ITurma } from 'app/shared/model//turma.model';

export interface IMatricula {
    id?: number;
    data?: Moment;
    obs?: string;
    aluno?: IAluno;
    turma?: ITurma;
}

export class Matricula implements IMatricula {
    constructor(public id?: number, public data?: Moment, public obs?: string, public aluno?: IAluno, public turma?: ITurma) {}
}
