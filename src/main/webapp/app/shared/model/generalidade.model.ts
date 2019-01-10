import { IDiario } from 'app/shared/model//diario.model';
import { IBimestre } from 'app/shared/model//bimestre.model';

export interface IGeneralidade {
    id?: number;
    codigo?: string;
    nome?: string;
    diario?: IDiario;
    bimestre?: IBimestre;
}

export class Generalidade implements IGeneralidade {
    constructor(public id?: number, public codigo?: string, public nome?: string, public diario?: IDiario, public bimestre?: IBimestre) {}
}
