import { Moment } from 'moment';
import { IMotivoDiaNaoUtil } from 'app/shared/model//motivo-dia-nao-util.model';
import { ICalendarioInstituicao } from 'app/shared/model//calendario-instituicao.model';

export interface IDiaNaoUtil {
    id?: number;
    nome?: string;
    data?: Moment;
    motivoDiaNaoUtils?: IMotivoDiaNaoUtil[];
    calendarioInstituicao?: ICalendarioInstituicao;
}

export class DiaNaoUtil implements IDiaNaoUtil {
    constructor(
        public id?: number,
        public nome?: string,
        public data?: Moment,
        public motivoDiaNaoUtils?: IMotivoDiaNaoUtil[],
        public calendarioInstituicao?: ICalendarioInstituicao
    ) {}
}
