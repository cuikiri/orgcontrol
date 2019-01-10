import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IParteBloco } from 'app/shared/model/parte-bloco.model';

type EntityResponseType = HttpResponse<IParteBloco>;
type EntityArrayResponseType = HttpResponse<IParteBloco[]>;

@Injectable({ providedIn: 'root' })
export class ParteBlocoService {
    public resourceUrl = SERVER_API_URL + 'api/parte-blocos';

    constructor(private http: HttpClient) {}

    create(parteBloco: IParteBloco): Observable<EntityResponseType> {
        return this.http.post<IParteBloco>(this.resourceUrl, parteBloco, { observe: 'response' });
    }

    update(parteBloco: IParteBloco): Observable<EntityResponseType> {
        return this.http.put<IParteBloco>(this.resourceUrl, parteBloco, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IParteBloco>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IParteBloco[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
