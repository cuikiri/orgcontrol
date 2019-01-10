import { IParteBloco } from 'app/shared/model//parte-bloco.model';

export interface ITipoParteBloco {
    id?: number;
    nome?: string;
    parteBloco?: IParteBloco;
}

export class TipoParteBloco implements ITipoParteBloco {
    constructor(public id?: number, public nome?: string, public parteBloco?: IParteBloco) {}
}
