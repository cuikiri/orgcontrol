import { ITipoDocumento } from 'app/shared/model//tipo-documento.model';
import { IFotoDocumento } from 'app/shared/model//foto-documento.model';
import { IPessoa } from 'app/shared/model//pessoa.model';
import { IColaborador } from 'app/shared/model//colaborador.model';
import { IDependenteLegal } from 'app/shared/model//dependente-legal.model';

export interface IDocumento {
    id?: number;
    nome?: string;
    numero?: string;
    obs?: string;
    tipoDocumento?: ITipoDocumento;
    fotoDocumento?: IFotoDocumento;
    pessoa?: IPessoa;
    colaborador?: IColaborador;
    dependenteLegal?: IDependenteLegal;
}

export class Documento implements IDocumento {
    constructor(
        public id?: number,
        public nome?: string,
        public numero?: string,
        public obs?: string,
        public tipoDocumento?: ITipoDocumento,
        public fotoDocumento?: IFotoDocumento,
        public pessoa?: IPessoa,
        public colaborador?: IColaborador,
        public dependenteLegal?: IDependenteLegal
    ) {}
}
