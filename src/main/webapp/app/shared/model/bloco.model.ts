import { ITipoBloco } from 'app/shared/model//tipo-bloco.model';
import { ILocalizacao } from 'app/shared/model//localizacao.model';
import { IParteBloco } from 'app/shared/model//parte-bloco.model';
import { IUnidade } from 'app/shared/model//unidade.model';

export interface IBloco {
    id?: number;
    nome?: string;
    descricao?: string;
    obs?: string;
    tipoBloco?: ITipoBloco;
    localizacao?: ILocalizacao;
    parteBlocos?: IParteBloco[];
    unidade?: IUnidade;
}

export class Bloco implements IBloco {
    constructor(
        public id?: number,
        public nome?: string,
        public descricao?: string,
        public obs?: string,
        public tipoBloco?: ITipoBloco,
        public localizacao?: ILocalizacao,
        public parteBlocos?: IParteBloco[],
        public unidade?: IUnidade
    ) {}
}
