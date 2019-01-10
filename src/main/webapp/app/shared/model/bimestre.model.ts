import { Moment } from 'moment';
import { IFechamentoBimestre } from 'app/shared/model//fechamento-bimestre.model';
import { IAtividade } from 'app/shared/model//atividade.model';
import { IGeneralidade } from 'app/shared/model//generalidade.model';
import { IObservacaoProfessor } from 'app/shared/model//observacao-professor.model';
import { IObservacaoCoordenador } from 'app/shared/model//observacao-coordenador.model';
import { IRegistroRecuperacao } from 'app/shared/model//registro-recuperacao.model';
import { IDiario } from 'app/shared/model//diario.model';

export interface IBimestre {
    id?: number;
    abreviatura?: string;
    nome?: string;
    componente?: string;
    dataInicio?: Moment;
    dataFim?: Moment;
    numero?: number;
    atividadesPrevistas?: number;
    atividadesDadas?: number;
    atividadesRepostas?: number;
    fechamentoBimestre?: IFechamentoBimestre;
    atividades?: IAtividade[];
    generalidades?: IGeneralidade[];
    observacaoProfessors?: IObservacaoProfessor[];
    observacaoCoordenadors?: IObservacaoCoordenador[];
    registroRecuperacaos?: IRegistroRecuperacao[];
    diario?: IDiario;
}

export class Bimestre implements IBimestre {
    constructor(
        public id?: number,
        public abreviatura?: string,
        public nome?: string,
        public componente?: string,
        public dataInicio?: Moment,
        public dataFim?: Moment,
        public numero?: number,
        public atividadesPrevistas?: number,
        public atividadesDadas?: number,
        public atividadesRepostas?: number,
        public fechamentoBimestre?: IFechamentoBimestre,
        public atividades?: IAtividade[],
        public generalidades?: IGeneralidade[],
        public observacaoProfessors?: IObservacaoProfessor[],
        public observacaoCoordenadors?: IObservacaoCoordenador[],
        public registroRecuperacaos?: IRegistroRecuperacao[],
        public diario?: IDiario
    ) {}
}
