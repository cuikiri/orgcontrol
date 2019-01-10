import { Moment } from 'moment';
import { ICandidato } from 'app/shared/model//candidato.model';
import { IEleicao } from 'app/shared/model//eleicao.model';

export interface IChapa {
    id?: number;
    nome?: string;
    dataCadastro?: Moment;
    totaVotos?: number;
    vencedor?: boolean;
    obs?: string;
    candidatoes?: ICandidato[];
    eleicaoGanhadora?: IEleicao;
    eleicao?: IEleicao;
}

export class Chapa implements IChapa {
    constructor(
        public id?: number,
        public nome?: string,
        public dataCadastro?: Moment,
        public totaVotos?: number,
        public vencedor?: boolean,
        public obs?: string,
        public candidatoes?: ICandidato[],
        public eleicaoGanhadora?: IEleicao,
        public eleicao?: IEleicao
    ) {
        this.vencedor = this.vencedor || false;
    }
}
