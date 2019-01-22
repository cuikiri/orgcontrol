export interface IFotoUser {
    id?: number;
    conteudoContentType?: string;
    conteudo?: any;
}

export class FotoUser implements IFotoUser {
    constructor(public id?: number, public conteudoContentType?: string, public conteudo?: any) {}
}
