import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBiotipo } from 'app/shared/model/biotipo.model';

type EntityResponseType = HttpResponse<IBiotipo>;
type EntityArrayResponseType = HttpResponse<IBiotipo[]>;

@Injectable({ providedIn: 'root' })
export class BiotipoService {
    public resourceUrl = SERVER_API_URL + 'api/biotipos';

    constructor(private http: HttpClient) {}

    create(biotipo: IBiotipo): Observable<EntityResponseType> {
        return this.http.post<IBiotipo>(this.resourceUrl, biotipo, { observe: 'response' });
    }

    update(biotipo: IBiotipo): Observable<EntityResponseType> {
        return this.http.put<IBiotipo>(this.resourceUrl, biotipo, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IBiotipo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBiotipo[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
