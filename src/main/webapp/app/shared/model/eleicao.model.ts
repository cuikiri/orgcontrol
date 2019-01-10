import { Moment } from 'moment';
import { IChapa } from 'app/shared/model//chapa.model';
import { IInstituicao } from 'app/shared/model//instituicao.model';
import { IUnidade } from 'app/shared/model//unidade.model';

export interface IEleicao {
    id?: number;
    nome?: string;
    dataCadastro?: Moment;
    anoEleicao?: number;
    dataPleito?: Moment;
    totalEleitores?: number;
    totaVotos?: number;
    local?: string;
    hora?: string;
    obs?: string;
    chapaGanhadora?: IChapa;
    chapas?: IChapa[];
    instituicao?: IInstituicao;
    unidade?: IUnidade;
}

export class Eleicao implements IEleicao {
    constructor(
        public id?: number,
        public nome?: string,
        public dataCadastro?: Moment,
        public anoEleicao?: number,
        public dataPleito?: Moment,
        public totalEleitores?: number,
        public totaVotos?: number,
        public local?: string,
        public hora?: string,
        public obs?: string,
        public chapaGanhadora?: IChapa,
        public chapas?: IChapa[],
        public instituicao?: IInstituicao,
        public unidade?: IUnidade
    ) {}
}
