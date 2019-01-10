import { ITipoParteBloco } from 'app/shared/model//tipo-parte-bloco.model';
import { IPeriodoAtividade } from 'app/shared/model//periodo-atividade.model';
import { IBloco } from 'app/shared/model//bloco.model';

export interface IParteBloco {
    id?: number;
    abreviatura?: string;
    nome?: string;
    andar?: number;
    numero?: number;
    obs?: string;
    tipoParteBloco?: ITipoParteBloco;
    periodoAtividade?: IPeriodoAtividade;
    bloco?: IBloco;
}

export class ParteBloco implements IParteBloco {
    constructor(
        public id?: number,
        public abreviatura?: string,
        public nome?: string,
        public andar?: number,
        public numero?: number,
        public obs?: string,
        public tipoParteBloco?: ITipoParteBloco,
        public periodoAtividade?: IPeriodoAtividade,
        public bloco?: IBloco
    ) {}
}
