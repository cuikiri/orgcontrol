import { Moment } from 'moment';
import { ICalendarioInstituicao } from 'app/shared/model//calendario-instituicao.model';

export interface IPeriodoDuracao {
    id?: number;
    nome?: string;
    dataInicio?: Moment;
    dataFim?: Moment;
    obs?: string;
    calendarioInstituicao?: ICalendarioInstituicao;
}

export class PeriodoDuracao implements IPeriodoDuracao {
    constructor(
        public id?: number,
        public nome?: string,
        public dataInicio?: Moment,
        public dataFim?: Moment,
        public obs?: string,
        public calendarioInstituicao?: ICalendarioInstituicao
    ) {}
}
