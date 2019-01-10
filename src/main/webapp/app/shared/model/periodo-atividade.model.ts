import { IParteBloco } from 'app/shared/model//parte-bloco.model';
import { ITurma } from 'app/shared/model//turma.model';

export interface IPeriodoAtividade {
    id?: number;
    nome?: string;
    horaInicio?: string;
    horaFim?: string;
    obs?: string;
    parteBloco?: IParteBloco;
    turmas?: ITurma[];
}

export class PeriodoAtividade implements IPeriodoAtividade {
    constructor(
        public id?: number,
        public nome?: string,
        public horaInicio?: string,
        public horaFim?: string,
        public obs?: string,
        public parteBloco?: IParteBloco,
        public turmas?: ITurma[]
    ) {}
}
