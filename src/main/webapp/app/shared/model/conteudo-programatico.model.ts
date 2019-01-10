import { Moment } from 'moment';
import { IAtividade } from 'app/shared/model//atividade.model';

export interface IConteudoProgramatico {
    id?: number;
    data?: Moment;
    descricao?: string;
    atividade?: IAtividade;
}

export class ConteudoProgramatico implements IConteudoProgramatico {
    constructor(public id?: number, public data?: Moment, public descricao?: string, public atividade?: IAtividade) {}
}
