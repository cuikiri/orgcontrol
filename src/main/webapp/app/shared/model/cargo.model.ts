import { ICandidato } from 'app/shared/model//candidato.model';

export interface ICargo {
    id?: number;
    nome?: string;
    obs?: string;
    candidato?: ICandidato;
}

export class Cargo implements ICargo {
    constructor(public id?: number, public nome?: string, public obs?: string, public candidato?: ICandidato) {}
}
