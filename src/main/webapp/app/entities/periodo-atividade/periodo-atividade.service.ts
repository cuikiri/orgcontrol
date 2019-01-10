import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPeriodoAtividade } from 'app/shared/model/periodo-atividade.model';

type EntityResponseType = HttpResponse<IPeriodoAtividade>;
type EntityArrayResponseType = HttpResponse<IPeriodoAtividade[]>;

@Injectable({ providedIn: 'root' })
export class PeriodoAtividadeService {
    public resourceUrl = SERVER_API_URL + 'api/periodo-atividades';

    constructor(private http: HttpClient) {}

    create(periodoAtividade: IPeriodoAtividade): Observable<EntityResponseType> {
        return this.http.post<IPeriodoAtividade>(this.resourceUrl, periodoAtividade, { observe: 'response' });
    }

    update(periodoAtividade: IPeriodoAtividade): Observable<EntityResponseType> {
        return this.http.put<IPeriodoAtividade>(this.resourceUrl, periodoAtividade, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPeriodoAtividade>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPeriodoAtividade[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
