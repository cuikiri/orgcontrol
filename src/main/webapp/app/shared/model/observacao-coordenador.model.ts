import { Moment } from 'moment';
import { IDiario } from 'app/shared/model//diario.model';
import { IBimestre } from 'app/shared/model//bimestre.model';

export interface IObservacaoCoordenador {
    id?: number;
    data?: Moment;
    nome?: string;
    obs?: string;
    diario?: IDiario;
    bimestre?: IBimestre;
}

export class ObservacaoCoordenador implements IObservacaoCoordenador {
    constructor(
        public id?: number,
        public data?: Moment,
        public nome?: string,
        public obs?: string,
        public diario?: IDiario,
        public bimestre?: IBimestre
    ) {}
}
