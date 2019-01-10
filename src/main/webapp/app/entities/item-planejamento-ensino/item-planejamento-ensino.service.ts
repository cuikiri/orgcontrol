import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IItemPlanejamentoEnsino } from 'app/shared/model/item-planejamento-ensino.model';

type EntityResponseType = HttpResponse<IItemPlanejamentoEnsino>;
type EntityArrayResponseType = HttpResponse<IItemPlanejamentoEnsino[]>;

@Injectable({ providedIn: 'root' })
export class ItemPlanejamentoEnsinoService {
    public resourceUrl = SERVER_API_URL + 'api/item-planejamento-ensinos';

    constructor(private http: HttpClient) {}

    create(itemPlanejamentoEnsino: IItemPlanejamentoEnsino): Observable<EntityResponseType> {
        return this.http.post<IItemPlanejamentoEnsino>(this.resourceUrl, itemPlanejamentoEnsino, { observe: 'response' });
    }

    update(itemPlanejamentoEnsino: IItemPlanejamentoEnsino): Observable<EntityResponseType> {
        return this.http.put<IItemPlanejamentoEnsino>(this.resourceUrl, itemPlanejamentoEnsino, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IItemPlanejamentoEnsino>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IItemPlanejamentoEnsino[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
