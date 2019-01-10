import { IDiaSemana } from 'app/shared/model//dia-semana.model';

export interface IPeriodoSemana {
    id?: number;
    nome?: string;
    obs?: string;
    diaSemanas?: IDiaSemana[];
}

export class PeriodoSemana implements IPeriodoSemana {
    constructor(public id?: number, public nome?: string, public obs?: string, public diaSemanas?: IDiaSemana[]) {}
}
