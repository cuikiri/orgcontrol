import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAcompanhamentoAluno } from 'app/shared/model/acompanhamento-aluno.model';

type EntityResponseType = HttpResponse<IAcompanhamentoAluno>;
type EntityArrayResponseType = HttpResponse<IAcompanhamentoAluno[]>;

@Injectable({ providedIn: 'root' })
export class AcompanhamentoAlunoService {
    public resourceUrl = SERVER_API_URL + 'api/acompanhamento-alunos';

    constructor(private http: HttpClient) {}

    create(acompanhamentoAluno: IAcompanhamentoAluno): Observable<EntityResponseType> {
        return this.http.post<IAcompanhamentoAluno>(this.resourceUrl, acompanhamentoAluno, { observe: 'response' });
    }

    update(acompanhamentoAluno: IAcompanhamentoAluno): Observable<EntityResponseType> {
        return this.http.put<IAcompanhamentoAluno>(this.resourceUrl, acompanhamentoAluno, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAcompanhamentoAluno>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAcompanhamentoAluno[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
