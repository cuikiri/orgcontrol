import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPeriodoSemana } from 'app/shared/model/periodo-semana.model';

type EntityResponseType = HttpResponse<IPeriodoSemana>;
type EntityArrayResponseType = HttpResponse<IPeriodoSemana[]>;

@Injectable({ providedIn: 'root' })
export class PeriodoSemanaService {
    public resourceUrl = SERVER_API_URL + 'api/periodo-semanas';

    constructor(private http: HttpClient) {}

    create(periodoSemana: IPeriodoSemana): Observable<EntityResponseType> {
        return this.http.post<IPeriodoSemana>(this.resourceUrl, periodoSemana, { observe: 'response' });
    }

    update(periodoSemana: IPeriodoSemana): Observable<EntityResponseType> {
        return this.http.put<IPeriodoSemana>(this.resourceUrl, periodoSemana, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPeriodoSemana>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPeriodoSemana[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
