import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoBloco } from 'app/shared/model/tipo-bloco.model';

type EntityResponseType = HttpResponse<ITipoBloco>;
type EntityArrayResponseType = HttpResponse<ITipoBloco[]>;

@Injectable({ providedIn: 'root' })
export class TipoBlocoService {
    public resourceUrl = SERVER_API_URL + 'api/tipo-blocos';

    constructor(private http: HttpClient) {}

    create(tipoBloco: ITipoBloco): Observable<EntityResponseType> {
        return this.http.post<ITipoBloco>(this.resourceUrl, tipoBloco, { observe: 'response' });
    }

    update(tipoBloco: ITipoBloco): Observable<EntityResponseType> {
        return this.http.put<ITipoBloco>(this.resourceUrl, tipoBloco, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipoBloco>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoBloco[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
