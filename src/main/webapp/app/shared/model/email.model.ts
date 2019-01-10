import { IPessoa } from 'app/shared/model//pessoa.model';
import { IUnidade } from 'app/shared/model//unidade.model';

export const enum TipoEmail {
    PESSOAL = 'PESSOAL',
    COMERCIAL = 'COMERCIAL',
    OUTROS = 'OUTROS'
}

export interface IEmail {
    id?: number;
    tipoEmail?: TipoEmail;
    descricao?: string;
    pessoa?: IPessoa;
    unidade?: IUnidade;
}

export class Email implements IEmail {
    constructor(
        public id?: number,
        public tipoEmail?: TipoEmail,
        public descricao?: string,
        public pessoa?: IPessoa,
        public unidade?: IUnidade
    ) {}
}
