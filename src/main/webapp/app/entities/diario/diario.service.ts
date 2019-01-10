import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDiario } from 'app/shared/model/diario.model';

type EntityResponseType = HttpResponse<IDiario>;
type EntityArrayResponseType = HttpResponse<IDiario[]>;

@Injectable({ providedIn: 'root' })
export class DiarioService {
    public resourceUrl = SERVER_API_URL + 'api/diarios';

    constructor(private http: HttpClient) {}

    create(diario: IDiario): Observable<EntityResponseType> {
        return this.http.post<IDiario>(this.resourceUrl, diario, { observe: 'response' });
    }

    update(diario: IDiario): Observable<EntityResponseType> {
        return this.http.put<IDiario>(this.resourceUrl, diario, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDiario>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDiario[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
