import { IUnidade } from 'app/shared/model//unidade.model';
import { IEleicao } from 'app/shared/model//eleicao.model';
import { IColaborador } from 'app/shared/model//colaborador.model';
import { ICalendarioInstituicao } from 'app/shared/model//calendario-instituicao.model';

export interface IInstituicao {
    id?: number;
    nome?: string;
    razaoSocial?: string;
    cnpj?: string;
    site?: string;
    blog?: string;
    unidades?: IUnidade[];
    eleicaos?: IEleicao[];
    colaboradors?: IColaborador[];
    calendarioInstituicaos?: ICalendarioInstituicao[];
}

export class Instituicao implements IInstituicao {
    constructor(
        public id?: number,
        public nome?: string,
        public razaoSocial?: string,
        public cnpj?: string,
        public site?: string,
        public blog?: string,
        public unidades?: IUnidade[],
        public eleicaos?: IEleicao[],
        public colaboradors?: IColaborador[],
        public calendarioInstituicaos?: ICalendarioInstituicao[]
    ) {}
}
