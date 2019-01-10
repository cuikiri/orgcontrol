import { IBimestreAcompanhamento } from 'app/shared/model//bimestre-acompanhamento.model';

export interface IConceitoAcompanhamento {
    id?: number;
    nome?: string;
    nota?: number;
    obs?: string;
    bimestreAcompanhamento?: IBimestreAcompanhamento;
}

export class ConceitoAcompanhamento implements IConceitoAcompanhamento {
    constructor(
        public id?: number,
        public nome?: string,
        public nota?: number,
        public obs?: string,
        public bimestreAcompanhamento?: IBimestreAcompanhamento
    ) {}
}
