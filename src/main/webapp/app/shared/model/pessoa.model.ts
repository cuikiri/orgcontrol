import { ITelefone } from 'app/shared/model//telefone.model';
import { IEmail } from 'app/shared/model//email.model';
import { IEndereco } from 'app/shared/model//endereco.model';
import { IDocumento } from 'app/shared/model//documento.model';
import { IAviso } from 'app/shared/model//aviso.model';
import { IColaborador } from 'app/shared/model//colaborador.model';
import { IResponsavel } from 'app/shared/model//responsavel.model';
import { IAluno } from 'app/shared/model//aluno.model';

export interface IPessoa {
    id?: number;
    nome?: string;
    telefones?: ITelefone[];
    emails?: IEmail[];
    enderecos?: IEndereco[];
    documentos?: IDocumento[];
    avisos?: IAviso[];
    colaborador?: IColaborador;
    responsavel?: IResponsavel;
    aluno?: IAluno;
    alunoMae?: IAluno;
    alunoPai?: IAluno;
    alunoIrmao?: IAluno;
}

export class Pessoa implements IPessoa {
    constructor(
        public id?: number,
        public nome?: string,
        public telefones?: ITelefone[],
        public emails?: IEmail[],
        public enderecos?: IEndereco[],
        public documentos?: IDocumento[],
        public avisos?: IAviso[],
        public colaborador?: IColaborador,
        public responsavel?: IResponsavel,
        public aluno?: IAluno,
        public alunoMae?: IAluno,
        public alunoPai?: IAluno,
        public alunoIrmao?: IAluno
    ) {}
}
