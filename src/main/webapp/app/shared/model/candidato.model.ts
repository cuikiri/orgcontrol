import { Moment } from 'moment';
import { ICargo } from 'app/shared/model//cargo.model';
import { IColaborador } from 'app/shared/model//colaborador.model';
import { IChapa } from 'app/shared/model//chapa.model';

export interface ICandidato {
    id?: number;
    apelido?: string;
    dataCadastro?: Moment;
    obs?: string;
    cargo?: ICargo;
    colaborador?: IColaborador;
    chapa?: IChapa;
}

export class Candidato implements ICandidato {
    constructor(
        public id?: number,
        public apelido?: string,
        public dataCadastro?: Moment,
        public obs?: string,
        public cargo?: ICargo,
        public colaborador?: IColaborador,
        public chapa?: IChapa
    ) {}
}
