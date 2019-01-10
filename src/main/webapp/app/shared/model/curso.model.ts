export interface ICurso {
    id?: number;
    nome?: string;
    obs?: string;
}

export class Curso implements ICurso {
    constructor(public id?: number, public nome?: string, public obs?: string) {}
}
