export interface ISexo {
    id?: number;
    nome?: string;
}

export class Sexo implements ISexo {
    constructor(public id?: number, public nome?: string) {}
}
