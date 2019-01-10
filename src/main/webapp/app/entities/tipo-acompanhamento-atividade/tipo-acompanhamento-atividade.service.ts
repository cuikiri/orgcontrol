import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoAcompanhamentoAtividade } from 'app/shared/model/tipo-acompanhamento-atividade.model';

type EntityResponseType = HttpResponse<ITipoAcompanhamentoAtividade>;
type EntityArrayResponseType = HttpResponse<ITipoAcompanhamentoAtividade[]>;

@Injectable({ providedIn: 'root' })
export class TipoAcompanhamentoAtividadeService {
    public resourceUrl = SERVER_API_URL + 'api/tipo-acompanhamento-atividades';

    constructor(private http: HttpClient) {}

    create(tipoAcompanhamentoAtividade: ITipoAcompanhamentoAtividade): Observable<EntityResponseType> {
        return this.http.post<ITipoAcompanhamentoAtividade>(this.resourceUrl, tipoAcompanhamentoAtividade, { observe: 'response' });
    }

    update(tipoAcompanhamentoAtividade: ITipoAcompanhamentoAtividade): Observable<EntityResponseType> {
        return this.http.put<ITipoAcompanhamentoAtividade>(this.resourceUrl, tipoAcompanhamentoAtividade, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipoAcompanhamentoAtividade>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoAcompanhamentoAtividade[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
