import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoAcompanhamentoAluno } from 'app/shared/model/tipo-acompanhamento-aluno.model';

type EntityResponseType = HttpResponse<ITipoAcompanhamentoAluno>;
type EntityArrayResponseType = HttpResponse<ITipoAcompanhamentoAluno[]>;

@Injectable({ providedIn: 'root' })
export class TipoAcompanhamentoAlunoService {
    public resourceUrl = SERVER_API_URL + 'api/tipo-acompanhamento-alunos';

    constructor(private http: HttpClient) {}

    create(tipoAcompanhamentoAluno: ITipoAcompanhamentoAluno): Observable<EntityResponseType> {
        return this.http.post<ITipoAcompanhamentoAluno>(this.resourceUrl, tipoAcompanhamentoAluno, { observe: 'response' });
    }

    update(tipoAcompanhamentoAluno: ITipoAcompanhamentoAluno): Observable<EntityResponseType> {
        return this.http.put<ITipoAcompanhamentoAluno>(this.resourceUrl, tipoAcompanhamentoAluno, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipoAcompanhamentoAluno>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoAcompanhamentoAluno[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
