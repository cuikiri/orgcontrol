import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IItemAvaliacao } from 'app/shared/model/item-avaliacao.model';

type EntityResponseType = HttpResponse<IItemAvaliacao>;
type EntityArrayResponseType = HttpResponse<IItemAvaliacao[]>;

@Injectable({ providedIn: 'root' })
export class ItemAvaliacaoService {
    public resourceUrl = SERVER_API_URL + 'api/item-avaliacaos';

    constructor(private http: HttpClient) {}

    create(itemAvaliacao: IItemAvaliacao): Observable<EntityResponseType> {
        return this.http.post<IItemAvaliacao>(this.resourceUrl, itemAvaliacao, { observe: 'response' });
    }

    update(itemAvaliacao: IItemAvaliacao): Observable<EntityResponseType> {
        return this.http.put<IItemAvaliacao>(this.resourceUrl, itemAvaliacao, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IItemAvaliacao>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IItemAvaliacao[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
