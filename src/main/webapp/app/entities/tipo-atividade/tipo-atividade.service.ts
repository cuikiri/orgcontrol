import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoAtividade } from 'app/shared/model/tipo-atividade.model';

type EntityResponseType = HttpResponse<ITipoAtividade>;
type EntityArrayResponseType = HttpResponse<ITipoAtividade[]>;

@Injectable({ providedIn: 'root' })
export class TipoAtividadeService {
    public resourceUrl = SERVER_API_URL + 'api/tipo-atividades';

    constructor(private http: HttpClient) {}

    create(tipoAtividade: ITipoAtividade): Observable<EntityResponseType> {
        return this.http.post<ITipoAtividade>(this.resourceUrl, tipoAtividade, { observe: 'response' });
    }

    update(tipoAtividade: ITipoAtividade): Observable<EntityResponseType> {
        return this.http.put<ITipoAtividade>(this.resourceUrl, tipoAtividade, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipoAtividade>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoAtividade[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
