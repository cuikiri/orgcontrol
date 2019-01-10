import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IItemPlanejamentoInstituicao } from 'app/shared/model/item-planejamento-instituicao.model';

type EntityResponseType = HttpResponse<IItemPlanejamentoInstituicao>;
type EntityArrayResponseType = HttpResponse<IItemPlanejamentoInstituicao[]>;

@Injectable({ providedIn: 'root' })
export class ItemPlanejamentoInstituicaoService {
    public resourceUrl = SERVER_API_URL + 'api/item-planejamento-instituicaos';

    constructor(private http: HttpClient) {}

    create(itemPlanejamentoInstituicao: IItemPlanejamentoInstituicao): Observable<EntityResponseType> {
        return this.http.post<IItemPlanejamentoInstituicao>(this.resourceUrl, itemPlanejamentoInstituicao, { observe: 'response' });
    }

    update(itemPlanejamentoInstituicao: IItemPlanejamentoInstituicao): Observable<EntityResponseType> {
        return this.http.put<IItemPlanejamentoInstituicao>(this.resourceUrl, itemPlanejamentoInstituicao, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IItemPlanejamentoInstituicao>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IItemPlanejamentoInstituicao[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
