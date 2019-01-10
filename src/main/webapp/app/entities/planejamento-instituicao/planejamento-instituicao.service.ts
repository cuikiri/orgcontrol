import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlanejamentoInstituicao } from 'app/shared/model/planejamento-instituicao.model';

type EntityResponseType = HttpResponse<IPlanejamentoInstituicao>;
type EntityArrayResponseType = HttpResponse<IPlanejamentoInstituicao[]>;

@Injectable({ providedIn: 'root' })
export class PlanejamentoInstituicaoService {
    public resourceUrl = SERVER_API_URL + 'api/planejamento-instituicaos';

    constructor(private http: HttpClient) {}

    create(planejamentoInstituicao: IPlanejamentoInstituicao): Observable<EntityResponseType> {
        return this.http.post<IPlanejamentoInstituicao>(this.resourceUrl, planejamentoInstituicao, { observe: 'response' });
    }

    update(planejamentoInstituicao: IPlanejamentoInstituicao): Observable<EntityResponseType> {
        return this.http.put<IPlanejamentoInstituicao>(this.resourceUrl, planejamentoInstituicao, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPlanejamentoInstituicao>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPlanejamentoInstituicao[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
