import { IBiotipo } from 'app/shared/model//biotipo.model';
import { IDoenca } from 'app/shared/model//doenca.model';
import { IDadosMedico } from 'app/shared/model//dados-medico.model';

export interface IExameMedico {
    id?: number;
    nomeMedico?: string;
    crmMedico?: string;
    especialidadeMedico?: string;
    idadePaciente?: number;
    obs?: string;
    biotipos?: IBiotipo[];
    doencas?: IDoenca[];
    dadosMedico?: IDadosMedico;
}

export class ExameMedico implements IExameMedico {
    constructor(
        public id?: number,
        public nomeMedico?: string,
        public crmMedico?: string,
        public especialidadeMedico?: string,
        public idadePaciente?: number,
        public obs?: string,
        public biotipos?: IBiotipo[],
        public doencas?: IDoenca[],
        public dadosMedico?: IDadosMedico
    ) {}
}
