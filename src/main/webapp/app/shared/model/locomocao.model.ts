import { IAluno } from 'app/shared/model//aluno.model';

export interface ILocomocao {
    id?: number;
    nome?: string;
    aluno?: IAluno;
}

export class Locomocao implements ILocomocao {
    constructor(public id?: number, public nome?: string, public aluno?: IAluno) {}
}
