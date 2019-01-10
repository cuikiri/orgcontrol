import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IItemPlanejamentoAtividade } from 'app/shared/model/item-planejamento-atividade.model';

type EntityResponseType = HttpResponse<IItemPlanejamentoAtividade>;
type EntityArrayResponseType = HttpResponse<IItemPlanejamentoAtividade[]>;

@Injectable({ providedIn: 'root' })
export class ItemPlanejamentoAtividadeService {
    public resourceUrl = SERVER_API_URL + 'api/item-planejamento-atividades';

    constructor(private http: HttpClient) {}

    create(itemPlanejamentoAtividade: IItemPlanejamentoAtividade): Observable<EntityResponseType> {
        return this.http.post<IItemPlanejamentoAtividade>(this.resourceUrl, itemPlanejamentoAtividade, { observe: 'response' });
    }

    update(itemPlanejamentoAtividade: IItemPlanejamentoAtividade): Observable<EntityResponseType> {
        return this.http.put<IItemPlanejamentoAtividade>(this.resourceUrl, itemPlanejamentoAtividade, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IItemPlanejamentoAtividade>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IItemPlanejamentoAtividade[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
