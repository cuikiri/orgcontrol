import { IMateria } from 'app/shared/model//materia.model';
import { IDiaSemana } from 'app/shared/model//dia-semana.model';
import { ITurma } from 'app/shared/model//turma.model';

export interface IHorarioMateria {
    id?: number;
    nome?: string;
    horaInicio?: string;
    horaFim?: string;
    obs?: string;
    materia?: IMateria;
    diaSemana?: IDiaSemana;
    turmas?: ITurma[];
}

export class HorarioMateria implements IHorarioMateria {
    constructor(
        public id?: number,
        public nome?: string,
        public horaInicio?: string,
        public horaFim?: string,
        public obs?: string,
        public materia?: IMateria,
        public diaSemana?: IDiaSemana,
        public turmas?: ITurma[]
    ) {}
}
