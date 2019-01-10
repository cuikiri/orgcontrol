import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlanejamentoAtividade } from 'app/shared/model/planejamento-atividade.model';

type EntityResponseType = HttpResponse<IPlanejamentoAtividade>;
type EntityArrayResponseType = HttpResponse<IPlanejamentoAtividade[]>;

@Injectable({ providedIn: 'root' })
export class PlanejamentoAtividadeService {
    public resourceUrl = SERVER_API_URL + 'api/planejamento-atividades';

    constructor(private http: HttpClient) {}

    create(planejamentoAtividade: IPlanejamentoAtividade): Observable<EntityResponseType> {
        return this.http.post<IPlanejamentoAtividade>(this.resourceUrl, planejamentoAtividade, { observe: 'response' });
    }

    update(planejamentoAtividade: IPlanejamentoAtividade): Observable<EntityResponseType> {
        return this.http.put<IPlanejamentoAtividade>(this.resourceUrl, planejamentoAtividade, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPlanejamentoAtividade>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPlanejamentoAtividade[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
