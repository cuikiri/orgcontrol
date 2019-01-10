import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDiaSemana } from 'app/shared/model/dia-semana.model';

type EntityResponseType = HttpResponse<IDiaSemana>;
type EntityArrayResponseType = HttpResponse<IDiaSemana[]>;

@Injectable({ providedIn: 'root' })
export class DiaSemanaService {
    public resourceUrl = SERVER_API_URL + 'api/dia-semanas';

    constructor(private http: HttpClient) {}

    create(diaSemana: IDiaSemana): Observable<EntityResponseType> {
        return this.http.post<IDiaSemana>(this.resourceUrl, diaSemana, { observe: 'response' });
    }

    update(diaSemana: IDiaSemana): Observable<EntityResponseType> {
        return this.http.put<IDiaSemana>(this.resourceUrl, diaSemana, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDiaSemana>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDiaSemana[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
