import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoBiotipo } from 'app/shared/model/tipo-biotipo.model';

type EntityResponseType = HttpResponse<ITipoBiotipo>;
type EntityArrayResponseType = HttpResponse<ITipoBiotipo[]>;

@Injectable({ providedIn: 'root' })
export class TipoBiotipoService {
    public resourceUrl = SERVER_API_URL + 'api/tipo-biotipos';

    constructor(private http: HttpClient) {}

    create(tipoBiotipo: ITipoBiotipo): Observable<EntityResponseType> {
        return this.http.post<ITipoBiotipo>(this.resourceUrl, tipoBiotipo, { observe: 'response' });
    }

    update(tipoBiotipo: ITipoBiotipo): Observable<EntityResponseType> {
        return this.http.put<ITipoBiotipo>(this.resourceUrl, tipoBiotipo, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipoBiotipo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoBiotipo[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
