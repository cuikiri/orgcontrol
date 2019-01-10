import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlanejamentoEnsino } from 'app/shared/model/planejamento-ensino.model';

type EntityResponseType = HttpResponse<IPlanejamentoEnsino>;
type EntityArrayResponseType = HttpResponse<IPlanejamentoEnsino[]>;

@Injectable({ providedIn: 'root' })
export class PlanejamentoEnsinoService {
    public resourceUrl = SERVER_API_URL + 'api/planejamento-ensinos';

    constructor(private http: HttpClient) {}

    create(planejamentoEnsino: IPlanejamentoEnsino): Observable<EntityResponseType> {
        return this.http.post<IPlanejamentoEnsino>(this.resourceUrl, planejamentoEnsino, { observe: 'response' });
    }

    update(planejamentoEnsino: IPlanejamentoEnsino): Observable<EntityResponseType> {
        return this.http.put<IPlanejamentoEnsino>(this.resourceUrl, planejamentoEnsino, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPlanejamentoEnsino>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPlanejamentoEnsino[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
