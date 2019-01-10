import { IUf } from 'app/shared/model//uf.model';
import { ILocalizacao } from 'app/shared/model//localizacao.model';
import { IUnidade } from 'app/shared/model//unidade.model';
import { IPessoa } from 'app/shared/model//pessoa.model';

export const enum TipoResidencia {
    CASA = 'CASA',
    APARTAMENTO = 'APARTAMENTO',
    SITIO = 'SITIO',
    FAZENDA = 'FAZENDA'
}

export const enum EnderecoTipo {
    RESIDENCIAL = 'RESIDENCIAL',
    COMERCIAL = 'COMERCIAL',
    ESTUDANTIL = 'ESTUDANTIL'
}

export const enum TipoLogradouro {
    RUA = 'RUA',
    AVENIDA = 'AVENIDA',
    TRAVESSA = 'TRAVESSA',
    ESTRADA = 'ESTRADA',
    RODOVIA = 'RODOVIA'
}

export const enum TipoBairro {
    RESIDENCIAL = 'RESIDENCIAL',
    COMERCIAL = 'COMERCIAL',
    INDUSTRIAL = 'INDUSTRIAL'
}

export const enum Regiao {
    CENTRAL = 'CENTRAL',
    LESTE = 'LESTE',
    OESTE = 'OESTE',
    NORTE = 'NORTE',
    SUL = 'SUL',
    SUDESTE = 'SUDESTE',
    NORDESTE = 'NORDESTE'
}

export interface IEndereco {
    id?: number;
    tipoResidencia?: TipoResidencia;
    tipoEndereco?: EnderecoTipo;
    tipoLogradouro?: TipoLogradouro;
    nome?: string;
    numero?: number;
    bairro?: string;
    tipoBairoo?: TipoBairro;
    zona?: Regiao;
    cep?: string;
    bloco?: string;
    apto?: string;
    complemento?: string;
    cidade?: string;
    estado?: IUf;
    localizacao?: ILocalizacao;
    unidade?: IUnidade;
    pessoa?: IPessoa;
}

export class Endereco implements IEndereco {
    constructor(
        public id?: number,
        public tipoResidencia?: TipoResidencia,
        public tipoEndereco?: EnderecoTipo,
        public tipoLogradouro?: TipoLogradouro,
        public nome?: string,
        public numero?: number,
        public bairro?: string,
        public tipoBairoo?: TipoBairro,
        public zona?: Regiao,
        public cep?: string,
        public bloco?: string,
        public apto?: string,
        public complemento?: string,
        public cidade?: string,
        public estado?: IUf,
        public localizacao?: ILocalizacao,
        public unidade?: IUnidade,
        public pessoa?: IPessoa
    ) {}
}
