import { IEndereco } from 'app/shared/model//endereco.model';
import { ITipoUnidade } from 'app/shared/model//tipo-unidade.model';
import { IAluno } from 'app/shared/model//aluno.model';
import { IBloco } from 'app/shared/model//bloco.model';
import { IEmail } from 'app/shared/model//email.model';
import { IEleicao } from 'app/shared/model//eleicao.model';
import { ITelefone } from 'app/shared/model//telefone.model';
import { IColaborador } from 'app/shared/model//colaborador.model';
import { ICalendarioInstituicao } from 'app/shared/model//calendario-instituicao.model';
import { IInstituicao } from 'app/shared/model//instituicao.model';

export interface IUnidade {
    id?: number;
    nome?: string;
    endereco?: IEndereco;
    tipoUnidade?: ITipoUnidade;
    alunos?: IAluno[];
    blocos?: IBloco[];
    emails?: IEmail[];
    eleicaos?: IEleicao[];
    telefones?: ITelefone[];
    colaboradors?: IColaborador[];
    calendarioInstituicaos?: ICalendarioInstituicao[];
    instituicao?: IInstituicao;
}

export class Unidade implements IUnidade {
    constructor(
        public id?: number,
        public nome?: string,
        public endereco?: IEndereco,
        public tipoUnidade?: ITipoUnidade,
        public alunos?: IAluno[],
        public blocos?: IBloco[],
        public emails?: IEmail[],
        public eleicaos?: IEleicao[],
        public telefones?: ITelefone[],
        public colaboradors?: IColaborador[],
        public calendarioInstituicaos?: ICalendarioInstituicao[],
        public instituicao?: IInstituicao
    ) {}
}
