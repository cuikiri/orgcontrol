import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IItemAvaliacaoEconomica } from 'app/shared/model/item-avaliacao-economica.model';

type EntityResponseType = HttpResponse<IItemAvaliacaoEconomica>;
type EntityArrayResponseType = HttpResponse<IItemAvaliacaoEconomica[]>;

@Injectable({ providedIn: 'root' })
export class ItemAvaliacaoEconomicaService {
    public resourceUrl = SERVER_API_URL + 'api/item-avaliacao-economicas';

    constructor(private http: HttpClient) {}

    create(itemAvaliacaoEconomica: IItemAvaliacaoEconomica): Observable<EntityResponseType> {
        return this.http.post<IItemAvaliacaoEconomica>(this.resourceUrl, itemAvaliacaoEconomica, { observe: 'response' });
    }

    update(itemAvaliacaoEconomica: IItemAvaliacaoEconomica): Observable<EntityResponseType> {
        return this.http.put<IItemAvaliacaoEconomica>(this.resourceUrl, itemAvaliacaoEconomica, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IItemAvaliacaoEconomica>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IItemAvaliacaoEconomica[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
