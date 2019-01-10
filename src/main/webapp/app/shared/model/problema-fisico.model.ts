import { IDadosMedico } from 'app/shared/model//dados-medico.model';

export interface IProblemaFisico {
    id?: number;
    nome?: string;
    obs?: string;
    dadosMedico?: IDadosMedico;
}

export class ProblemaFisico implements IProblemaFisico {
    constructor(public id?: number, public nome?: string, public obs?: string, public dadosMedico?: IDadosMedico) {}
}
