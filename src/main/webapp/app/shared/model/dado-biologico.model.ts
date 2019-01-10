import { ITipoadoBiologico } from 'app/shared/model//tipoado-biologico.model';
import { IDadosMedico } from 'app/shared/model//dados-medico.model';

export interface IDadoBiologico {
    id?: number;
    valor?: string;
    obs?: string;
    tipoadoBiologico?: ITipoadoBiologico;
    dadosMedico?: IDadosMedico;
}

export class DadoBiologico implements IDadoBiologico {
    constructor(
        public id?: number,
        public valor?: string,
        public obs?: string,
        public tipoadoBiologico?: ITipoadoBiologico,
        public dadosMedico?: IDadosMedico
    ) {}
}
