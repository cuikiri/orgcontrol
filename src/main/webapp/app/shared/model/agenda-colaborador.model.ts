import { IDiaSemana } from 'app/shared/model//dia-semana.model';
import { IColaborador } from 'app/shared/model//colaborador.model';

export interface IAgendaColaborador {
    id?: number;
    nome?: string;
    horaInicio?: string;
    horaFim?: string;
    horaAlmocoInicio?: string;
    horaAlmocoFim?: string;
    obs?: string;
    diaSemana?: IDiaSemana;
    colaborador?: IColaborador;
}

export class AgendaColaborador implements IAgendaColaborador {
    constructor(
        public id?: number,
        public nome?: string,
        public horaInicio?: string,
        public horaFim?: string,
        public horaAlmocoInicio?: string,
        public horaAlmocoFim?: string,
        public obs?: string,
        public diaSemana?: IDiaSemana,
        public colaborador?: IColaborador
    ) {}
}
