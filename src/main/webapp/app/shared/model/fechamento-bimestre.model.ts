import { IConceito } from 'app/shared/model//conceito.model';
import { IAvaliacao } from 'app/shared/model//avaliacao.model';
import { IAluno } from 'app/shared/model//aluno.model';
import { IBimestre } from 'app/shared/model//bimestre.model';

export interface IFechamentoBimestre {
    id?: number;
    frequencia?: number;
    ausencia?: number;
    totalAtividades?: number;
    porcentagemFrequencia?: number;
    porcentagemAusequencia?: number;
    conceito?: IConceito;
    avaliacaos?: IAvaliacao[];
    alunos?: IAluno[];
    bimestre?: IBimestre;
}

export class FechamentoBimestre implements IFechamentoBimestre {
    constructor(
        public id?: number,
        public frequencia?: number,
        public ausencia?: number,
        public totalAtividades?: number,
        public porcentagemFrequencia?: number,
        public porcentagemAusequencia?: number,
        public conceito?: IConceito,
        public avaliacaos?: IAvaliacao[],
        public alunos?: IAluno[],
        public bimestre?: IBimestre
    ) {}
}
