import { Moment } from 'moment';
import { IDadoBiologico } from 'app/shared/model//dado-biologico.model';
import { IProblemaFisico } from 'app/shared/model//problema-fisico.model';
import { IVacina } from 'app/shared/model//vacina.model';
import { IExameMedico } from 'app/shared/model//exame-medico.model';
import { IColaborador } from 'app/shared/model//colaborador.model';
import { IAluno } from 'app/shared/model//aluno.model';

export interface IDadosMedico {
    id?: number;
    data?: Moment;
    obs?: string;
    dadoBiologico?: IDadoBiologico;
    problemaFisicos?: IProblemaFisico[];
    vacinas?: IVacina[];
    exameMedicos?: IExameMedico[];
    colaborador?: IColaborador;
    aluno?: IAluno;
}

export class DadosMedico implements IDadosMedico {
    constructor(
        public id?: number,
        public data?: Moment,
        public obs?: string,
        public dadoBiologico?: IDadoBiologico,
        public problemaFisicos?: IProblemaFisico[],
        public vacinas?: IVacina[],
        public exameMedicos?: IExameMedico[],
        public colaborador?: IColaborador,
        public aluno?: IAluno
    ) {}
}
