import { IDadosMedico } from 'app/shared/model//dados-medico.model';

export interface IVacina {
    id?: number;
    nome?: string;
    idade?: string;
    obs?: string;
    dadosMedico?: IDadosMedico;
}

export class Vacina implements IVacina {
    constructor(public id?: number, public nome?: string, public idade?: string, public obs?: string, public dadosMedico?: IDadosMedico) {}
}
