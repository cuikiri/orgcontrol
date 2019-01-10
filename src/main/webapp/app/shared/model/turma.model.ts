import { IDiario } from 'app/shared/model//diario.model';
import { IEnsino } from 'app/shared/model//ensino.model';
import { IPeriodoDuracao } from 'app/shared/model//periodo-duracao.model';
import { IPeriodoSemana } from 'app/shared/model//periodo-semana.model';
import { ITipoCurso } from 'app/shared/model//tipo-curso.model';
import { ICurso } from 'app/shared/model//curso.model';
import { IModulo } from 'app/shared/model//modulo.model';
import { IHorarioMateria } from 'app/shared/model//horario-materia.model';
import { IPeriodoAtividade } from 'app/shared/model//periodo-atividade.model';

export interface ITurma {
    id?: number;
    nome?: string;
    ano?: number;
    diarios?: IDiario[];
    ensino?: IEnsino;
    periodoDuracao?: IPeriodoDuracao;
    periodoSemana?: IPeriodoSemana;
    tipoCurso?: ITipoCurso;
    curso?: ICurso;
    modulo?: IModulo;
    horarioMaterias?: IHorarioMateria[];
    periodoAtividades?: IPeriodoAtividade[];
}

export class Turma implements ITurma {
    constructor(
        public id?: number,
        public nome?: string,
        public ano?: number,
        public diarios?: IDiario[],
        public ensino?: IEnsino,
        public periodoDuracao?: IPeriodoDuracao,
        public periodoSemana?: IPeriodoSemana,
        public tipoCurso?: ITipoCurso,
        public curso?: ICurso,
        public modulo?: IModulo,
        public horarioMaterias?: IHorarioMateria[],
        public periodoAtividades?: IPeriodoAtividade[]
    ) {}
}
