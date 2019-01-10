import { IConceitoAcompanhamento } from 'app/shared/model//conceito-acompanhamento.model';
import { IMateriaAcompanhamento } from 'app/shared/model//materia-acompanhamento.model';

export interface IBimestreAcompanhamento {
    id?: number;
    nome?: string;
    numero?: number;
    obs?: string;
    conceitoAcompanhamentos?: IConceitoAcompanhamento[];
    materiaAcompanhamento?: IMateriaAcompanhamento;
}

export class BimestreAcompanhamento implements IBimestreAcompanhamento {
    constructor(
        public id?: number,
        public nome?: string,
        public numero?: number,
        public obs?: string,
        public conceitoAcompanhamentos?: IConceitoAcompanhamento[],
        public materiaAcompanhamento?: IMateriaAcompanhamento
    ) {}
}
