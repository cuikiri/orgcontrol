import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAcompanhamentoEscolarAluno } from 'app/shared/model/acompanhamento-escolar-aluno.model';

type EntityResponseType = HttpResponse<IAcompanhamentoEscolarAluno>;
type EntityArrayResponseType = HttpResponse<IAcompanhamentoEscolarAluno[]>;

@Injectable({ providedIn: 'root' })
export class AcompanhamentoEscolarAlunoService {
    public resourceUrl = SERVER_API_URL + 'api/acompanhamento-escolar-alunos';

    constructor(private http: HttpClient) {}

    create(acompanhamentoEscolarAluno: IAcompanhamentoEscolarAluno): Observable<EntityResponseType> {
        return this.http.post<IAcompanhamentoEscolarAluno>(this.resourceUrl, acompanhamentoEscolarAluno, { observe: 'response' });
    }

    update(acompanhamentoEscolarAluno: IAcompanhamentoEscolarAluno): Observable<EntityResponseType> {
        return this.http.put<IAcompanhamentoEscolarAluno>(this.resourceUrl, acompanhamentoEscolarAluno, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAcompanhamentoEscolarAluno>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAcompanhamentoEscolarAluno[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
