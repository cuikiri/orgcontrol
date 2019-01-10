import { IDiaNaoUtil } from 'app/shared/model//dia-nao-util.model';

export interface IMotivoDiaNaoUtil {
    id?: number;
    nome?: string;
    obs?: string;
    diaNaoUtil?: IDiaNaoUtil;
}

export class MotivoDiaNaoUtil implements IMotivoDiaNaoUtil {
    constructor(public id?: number, public nome?: string, public obs?: string, public diaNaoUtil?: IDiaNaoUtil) {}
}
