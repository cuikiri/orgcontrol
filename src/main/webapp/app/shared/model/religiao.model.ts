export interface IReligiao {
    id?: number;
    nome?: string;
}

export class Religiao implements IReligiao {
    constructor(public id?: number, public nome?: string) {}
}
