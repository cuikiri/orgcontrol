import { IDocumento } from 'app/shared/model//documento.model';

export interface IFotoDocumento {
    id?: number;
    fotoContentType?: string;
    foto?: any;
    documento?: IDocumento;
}

export class FotoDocumento implements IFotoDocumento {
    constructor(public id?: number, public fotoContentType?: string, public foto?: any, public documento?: IDocumento) {}
}
