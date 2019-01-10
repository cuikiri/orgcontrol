import { IHorarioMateria } from 'app/shared/model//horario-materia.model';
import { IPeriodoSemana } from 'app/shared/model//periodo-semana.model';

export interface IDiaSemana {
    id?: number;
    abreviatura?: string;
    nome?: string;
    numero?: number;
    obs?: string;
    horarioMateria?: IHorarioMateria;
    periodoSemana?: IPeriodoSemana;
}

export class DiaSemana implements IDiaSemana {
    constructor(
        public id?: number,
        public abreviatura?: string,
        public nome?: string,
        public numero?: number,
        public obs?: string,
        public horarioMateria?: IHorarioMateria,
        public periodoSemana?: IPeriodoSemana
    ) {}
}
