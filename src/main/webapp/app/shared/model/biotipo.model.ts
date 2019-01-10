import { ITipoBiotipo } from 'app/shared/model//tipo-biotipo.model';
import { IExameMedico } from 'app/shared/model//exame-medico.model';

export interface IBiotipo {
    id?: number;
    valor?: number;
    obs?: string;
    tipoBiotipo?: ITipoBiotipo;
    exameMedico?: IExameMedico;
}

export class Biotipo implements IBiotipo {
    constructor(
        public id?: number,
        public valor?: number,
        public obs?: string,
        public tipoBiotipo?: ITipoBiotipo,
        public exameMedico?: IExameMedico
    ) {}
}
