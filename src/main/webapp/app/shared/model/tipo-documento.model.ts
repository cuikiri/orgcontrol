import { IDocumento } from 'app/shared/model//documento.model';

export interface ITipoDocumento {
    id?: number;
    nome?: string;
    documento?: IDocumento;
}

export class TipoDocumento implements ITipoDocumento {
    constructor(public id?: number, public nome?: string, public documento?: IDocumento) {}
}
