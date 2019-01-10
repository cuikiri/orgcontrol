import { IExameMedico } from 'app/shared/model//exame-medico.model';

export interface IDoenca {
    id?: number;
    nome?: string;
    sintoma?: string;
    precaucoes?: string;
    socorro?: string;
    obs?: string;
    exameMedicos?: IExameMedico[];
}

export class Doenca implements IDoenca {
    constructor(
        public id?: number,
        public nome?: string,
        public sintoma?: string,
        public precaucoes?: string,
        public socorro?: string,
        public obs?: string,
        public exameMedicos?: IExameMedico[]
    ) {}
}
