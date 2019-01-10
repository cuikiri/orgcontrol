import { Moment } from 'moment';
import { IDocumento } from 'app/shared/model//documento.model';
import { IColaborador } from 'app/shared/model//colaborador.model';

export interface IDependenteLegal {
    id?: number;
    nome?: string;
    dataNascimento?: Moment;
    obs?: string;
    documentos?: IDocumento[];
    colaborador?: IColaborador;
}

export class DependenteLegal implements IDependenteLegal {
    constructor(
        public id?: number,
        public nome?: string,
        public dataNascimento?: Moment,
        public obs?: string,
        public documentos?: IDocumento[],
        public colaborador?: IColaborador
    ) {}
}
