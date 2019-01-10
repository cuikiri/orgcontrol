import { IEndereco } from 'app/shared/model//endereco.model';
import { IBloco } from 'app/shared/model//bloco.model';

export interface ILocalizacao {
    id?: number;
    descricao?: string;
    latitude?: number;
    longitude?: number;
    altitude?: number;
    endereco?: IEndereco;
    bloco?: IBloco;
}

export class Localizacao implements ILocalizacao {
    constructor(
        public id?: number,
        public descricao?: string,
        public latitude?: number,
        public longitude?: number,
        public altitude?: number,
        public endereco?: IEndereco,
        public bloco?: IBloco
    ) {}
}
