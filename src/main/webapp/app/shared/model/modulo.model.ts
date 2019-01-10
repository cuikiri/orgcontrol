export interface IModulo {
    id?: number;
    nome?: string;
    obs?: string;
}

export class Modulo implements IModulo {
    constructor(public id?: number, public nome?: string, public obs?: string) {}
}
