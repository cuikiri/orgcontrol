import { IPessoa } from 'app/shared/model//pessoa.model';
import { IUnidade } from 'app/shared/model//unidade.model';

export const enum TipoTelefone {
    CELULAR = 'CELULAR',
    FAX = 'FAX',
    RESIDENCIAL = 'RESIDENCIAL',
    COMERCIAL = 'COMERCIAL',
    OUTROS = 'OUTROS'
}

export interface ITelefone {
    id?: number;
    tipoTelefone?: TipoTelefone;
    numero?: string;
    pessoa?: IPessoa;
    unidade?: IUnidade;
}

export class Telefone implements ITelefone {
    constructor(
        public id?: number,
        public tipoTelefone?: TipoTelefone,
        public numero?: string,
        public pessoa?: IPessoa,
        public unidade?: IUnidade
    ) {}
}
