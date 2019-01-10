import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IItemAcompanhamentoAtividade } from 'app/shared/model/item-acompanhamento-atividade.model';

type EntityResponseType = HttpResponse<IItemAcompanhamentoAtividade>;
type EntityArrayResponseType = HttpResponse<IItemAcompanhamentoAtividade[]>;

@Injectable({ providedIn: 'root' })
export class ItemAcompanhamentoAtividadeService {
    public resourceUrl = SERVER_API_URL + 'api/item-acompanhamento-atividades';

    constructor(private http: HttpClient) {}

    create(itemAcompanhamentoAtividade: IItemAcompanhamentoAtividade): Observable<EntityResponseType> {
        return this.http.post<IItemAcompanhamentoAtividade>(this.resourceUrl, itemAcompanhamentoAtividade, { observe: 'response' });
    }

    update(itemAcompanhamentoAtividade: IItemAcompanhamentoAtividade): Observable<EntityResponseType> {
        return this.http.put<IItemAcompanhamentoAtividade>(this.resourceUrl, itemAcompanhamentoAtividade, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IItemAcompanhamentoAtividade>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IItemAcompanhamentoAtividade[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
