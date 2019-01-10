import { IHorarioMateria } from 'app/shared/model//horario-materia.model';
import { IDiario } from 'app/shared/model//diario.model';

export interface IMateria {
    id?: number;
    abreviatura?: string;
    nome?: string;
    descricao?: string;
    obs?: string;
    horarioMateria?: IHorarioMateria;
    diario?: IDiario;
}

export class Materia implements IMateria {
    constructor(
        public id?: number,
        public abreviatura?: string,
        public nome?: string,
        public descricao?: string,
        public obs?: string,
        public horarioMateria?: IHorarioMateria,
        public diario?: IDiario
    ) {}
}
