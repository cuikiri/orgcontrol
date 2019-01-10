import { IDiaNaoUtil } from 'app/shared/model//dia-nao-util.model';
import { IPeriodoDuracao } from 'app/shared/model//periodo-duracao.model';
import { IPlanejamentoInstituicao } from 'app/shared/model//planejamento-instituicao.model';
import { IPlanejamentoEnsino } from 'app/shared/model//planejamento-ensino.model';
import { IPlanejamentoAtividade } from 'app/shared/model//planejamento-atividade.model';
import { IInstituicao } from 'app/shared/model//instituicao.model';
import { IUnidade } from 'app/shared/model//unidade.model';

export interface ICalendarioInstituicao {
    id?: number;
    nome?: string;
    obs?: string;
    diaNaoUtils?: IDiaNaoUtil[];
    periodoDuracaos?: IPeriodoDuracao[];
    planejamentoInstituicaos?: IPlanejamentoInstituicao[];
    planejamentoEnsinos?: IPlanejamentoEnsino[];
    planejamentoAtividades?: IPlanejamentoAtividade[];
    instituicao?: IInstituicao;
    unidade?: IUnidade;
}

export class CalendarioInstituicao implements ICalendarioInstituicao {
    constructor(
        public id?: number,
        public nome?: string,
        public obs?: string,
        public diaNaoUtils?: IDiaNaoUtil[],
        public periodoDuracaos?: IPeriodoDuracao[],
        public planejamentoInstituicaos?: IPlanejamentoInstituicao[],
        public planejamentoEnsinos?: IPlanejamentoEnsino[],
        public planejamentoAtividades?: IPlanejamentoAtividade[],
        public instituicao?: IInstituicao,
        public unidade?: IUnidade
    ) {}
}
