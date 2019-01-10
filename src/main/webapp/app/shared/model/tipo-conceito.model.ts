import { IConceito } from 'app/shared/model//conceito.model';

export interface ITipoConceito {
    id?: number;
    nome?: string;
    obs?: string;
    conceito?: IConceito;
}

export class TipoConceito implements ITipoConceito {
    constructor(public id?: number, public nome?: string, public obs?: string, public conceito?: IConceito) {}
}
