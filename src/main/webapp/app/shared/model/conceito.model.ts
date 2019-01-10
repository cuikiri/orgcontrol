import { ITipoConceito } from 'app/shared/model//tipo-conceito.model';
import { IFechamentoBimestre } from 'app/shared/model//fechamento-bimestre.model';

export interface IConceito {
    id?: number;
    nome?: string;
    nota?: number;
    tipoConceito?: ITipoConceito;
    fechamentoBimestre?: IFechamentoBimestre;
}

export class Conceito implements IConceito {
    constructor(
        public id?: number,
        public nome?: string,
        public nota?: number,
        public tipoConceito?: ITipoConceito,
        public fechamentoBimestre?: IFechamentoBimestre
    ) {}
}
