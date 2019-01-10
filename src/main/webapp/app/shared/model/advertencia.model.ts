import { Moment } from 'moment';
import { IAluno } from 'app/shared/model//aluno.model';

export interface IAdvertencia {
    id?: number;
    nome?: string;
    data?: Moment;
    dataAdvertencia?: Moment;
    horaAdvertencia?: string;
    resumo?: string;
    obs?: string;
    aluno?: IAluno;
}

export class Advertencia implements IAdvertencia {
    constructor(
        public id?: number,
        public nome?: string,
        public data?: Moment,
        public dataAdvertencia?: Moment,
        public horaAdvertencia?: string,
        public resumo?: string,
        public obs?: string,
        public aluno?: IAluno
    ) {}
}
