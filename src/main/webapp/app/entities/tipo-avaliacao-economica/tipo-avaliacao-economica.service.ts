import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoAvaliacaoEconomica } from 'app/shared/model/tipo-avaliacao-economica.model';

type EntityResponseType = HttpResponse<ITipoAvaliacaoEconomica>;
type EntityArrayResponseType = HttpResponse<ITipoAvaliacaoEconomica[]>;

@Injectable({ providedIn: 'root' })
export class TipoAvaliacaoEconomicaService {
    public resourceUrl = SERVER_API_URL + 'api/tipo-avaliacao-economicas';

    constructor(private http: HttpClient) {}

    create(tipoAvaliacaoEconomica: ITipoAvaliacaoEconomica): Observable<EntityResponseType> {
        return this.http.post<ITipoAvaliacaoEconomica>(this.resourceUrl, tipoAvaliacaoEconomica, { observe: 'response' });
    }

    update(tipoAvaliacaoEconomica: ITipoAvaliacaoEconomica): Observable<EntityResponseType> {
        return this.http.put<ITipoAvaliacaoEconomica>(this.resourceUrl, tipoAvaliacaoEconomica, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipoAvaliacaoEconomica>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoAvaliacaoEconomica[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
