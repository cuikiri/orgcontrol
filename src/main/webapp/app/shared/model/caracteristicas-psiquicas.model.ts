import { IAluno } from 'app/shared/model//aluno.model';

export interface ICaracteristicasPsiquicas {
    id?: number;
    nome?: string;
    aluno?: IAluno;
}

export class CaracteristicasPsiquicas implements ICaracteristicasPsiquicas {
    constructor(public id?: number, public nome?: string, public aluno?: IAluno) {}
}
