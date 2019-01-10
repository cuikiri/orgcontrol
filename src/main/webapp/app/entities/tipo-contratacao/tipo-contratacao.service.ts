import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoContratacao } from 'app/shared/model/tipo-contratacao.model';

type EntityResponseType = HttpResponse<ITipoContratacao>;
type EntityArrayResponseType = HttpResponse<ITipoContratacao[]>;

@Injectable({ providedIn: 'root' })
export class TipoContratacaoService {
    public resourceUrl = SERVER_API_URL + 'api/tipo-contratacaos';

    constructor(private http: HttpClient) {}

    create(tipoContratacao: ITipoContratacao): Observable<EntityResponseType> {
        return this.http.post<ITipoContratacao>(this.resourceUrl, tipoContratacao, { observe: 'response' });
    }

    update(tipoContratacao: ITipoContratacao): Observable<EntityResponseType> {
        return this.http.put<ITipoContratacao>(this.resourceUrl, tipoContratacao, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipoContratacao>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoContratacao[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
