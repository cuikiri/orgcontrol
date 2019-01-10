import { IAcompanhamentoAtividade } from 'app/shared/model//acompanhamento-atividade.model';

export interface ITipoAcompanhamentoAtividade {
    id?: number;
    nome?: string;
    obs?: string;
    acompanhamentoAtividade?: IAcompanhamentoAtividade;
}

export class TipoAcompanhamentoAtividade implements ITipoAcompanhamentoAtividade {
    constructor(public id?: number, public nome?: string, public obs?: string, public acompanhamentoAtividade?: IAcompanhamentoAtividade) {}
}
