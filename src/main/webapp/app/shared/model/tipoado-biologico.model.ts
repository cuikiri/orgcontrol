import { IDadoBiologico } from 'app/shared/model//dado-biologico.model';

export interface ITipoadoBiologico {
    id?: number;
    nome?: string;
    obs?: string;
    dadoBiologico?: IDadoBiologico;
}

export class TipoadoBiologico implements ITipoadoBiologico {
    constructor(public id?: number, public nome?: string, public obs?: string, public dadoBiologico?: IDadoBiologico) {}
}
