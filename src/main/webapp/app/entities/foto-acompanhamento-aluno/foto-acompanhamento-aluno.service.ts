import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFotoAcompanhamentoAluno } from 'app/shared/model/foto-acompanhamento-aluno.model';

type EntityResponseType = HttpResponse<IFotoAcompanhamentoAluno>;
type EntityArrayResponseType = HttpResponse<IFotoAcompanhamentoAluno[]>;

@Injectable({ providedIn: 'root' })
export class FotoAcompanhamentoAlunoService {
    public resourceUrl = SERVER_API_URL + 'api/foto-acompanhamento-alunos';

    constructor(private http: HttpClient) {}

    create(fotoAcompanhamentoAluno: IFotoAcompanhamentoAluno): Observable<EntityResponseType> {
        return this.http.post<IFotoAcompanhamentoAluno>(this.resourceUrl, fotoAcompanhamentoAluno, { observe: 'response' });
    }

    update(fotoAcompanhamentoAluno: IFotoAcompanhamentoAluno): Observable<EntityResponseType> {
        return this.http.put<IFotoAcompanhamentoAluno>(this.resourceUrl, fotoAcompanhamentoAluno, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFotoAcompanhamentoAluno>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFotoAcompanhamentoAluno[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
