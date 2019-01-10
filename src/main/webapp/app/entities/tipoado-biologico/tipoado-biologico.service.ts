import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoadoBiologico } from 'app/shared/model/tipoado-biologico.model';

type EntityResponseType = HttpResponse<ITipoadoBiologico>;
type EntityArrayResponseType = HttpResponse<ITipoadoBiologico[]>;

@Injectable({ providedIn: 'root' })
export class TipoadoBiologicoService {
    public resourceUrl = SERVER_API_URL + 'api/tipoado-biologicos';

    constructor(private http: HttpClient) {}

    create(tipoadoBiologico: ITipoadoBiologico): Observable<EntityResponseType> {
        return this.http.post<ITipoadoBiologico>(this.resourceUrl, tipoadoBiologico, { observe: 'response' });
    }

    update(tipoadoBiologico: ITipoadoBiologico): Observable<EntityResponseType> {
        return this.http.put<ITipoadoBiologico>(this.resourceUrl, tipoadoBiologico, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipoadoBiologico>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoadoBiologico[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
