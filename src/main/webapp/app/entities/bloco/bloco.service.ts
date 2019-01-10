import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBloco } from 'app/shared/model/bloco.model';

type EntityResponseType = HttpResponse<IBloco>;
type EntityArrayResponseType = HttpResponse<IBloco[]>;

@Injectable({ providedIn: 'root' })
export class BlocoService {
    public resourceUrl = SERVER_API_URL + 'api/blocos';

    constructor(private http: HttpClient) {}

    create(bloco: IBloco): Observable<EntityResponseType> {
        return this.http.post<IBloco>(this.resourceUrl, bloco, { observe: 'response' });
    }

    update(bloco: IBloco): Observable<EntityResponseType> {
        return this.http.put<IBloco>(this.resourceUrl, bloco, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IBloco>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBloco[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
