import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoParteBloco } from 'app/shared/model/tipo-parte-bloco.model';

type EntityResponseType = HttpResponse<ITipoParteBloco>;
type EntityArrayResponseType = HttpResponse<ITipoParteBloco[]>;

@Injectable({ providedIn: 'root' })
export class TipoParteBlocoService {
    public resourceUrl = SERVER_API_URL + 'api/tipo-parte-blocos';

    constructor(private http: HttpClient) {}

    create(tipoParteBloco: ITipoParteBloco): Observable<EntityResponseType> {
        return this.http.post<ITipoParteBloco>(this.resourceUrl, tipoParteBloco, { observe: 'response' });
    }

    update(tipoParteBloco: ITipoParteBloco): Observable<EntityResponseType> {
        return this.http.put<ITipoParteBloco>(this.resourceUrl, tipoParteBloco, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipoParteBloco>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoParteBloco[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
