export interface ITipoCurso {
    id?: number;
    nome?: string;
    obs?: string;
}

export class TipoCurso implements ITipoCurso {
    constructor(public id?: number, public nome?: string, public obs?: string) {}
}
