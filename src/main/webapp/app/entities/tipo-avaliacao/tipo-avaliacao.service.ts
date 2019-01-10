import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoAvaliacao } from 'app/shared/model/tipo-avaliacao.model';

type EntityResponseType = HttpResponse<ITipoAvaliacao>;
type EntityArrayResponseType = HttpResponse<ITipoAvaliacao[]>;

@Injectable({ providedIn: 'root' })
export class TipoAvaliacaoService {
    public resourceUrl = SERVER_API_URL + 'api/tipo-avaliacaos';

    constructor(private http: HttpClient) {}

    create(tipoAvaliacao: ITipoAvaliacao): Observable<EntityResponseType> {
        return this.http.post<ITipoAvaliacao>(this.resourceUrl, tipoAvaliacao, { observe: 'response' });
    }

    update(tipoAvaliacao: ITipoAvaliacao): Observable<EntityResponseType> {
        return this.http.put<ITipoAvaliacao>(this.resourceUrl, tipoAvaliacao, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipoAvaliacao>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoAvaliacao[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
