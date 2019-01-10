import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoUnidade } from 'app/shared/model/tipo-unidade.model';

type EntityResponseType = HttpResponse<ITipoUnidade>;
type EntityArrayResponseType = HttpResponse<ITipoUnidade[]>;

@Injectable({ providedIn: 'root' })
export class TipoUnidadeService {
    public resourceUrl = SERVER_API_URL + 'api/tipo-unidades';

    constructor(private http: HttpClient) {}

    create(tipoUnidade: ITipoUnidade): Observable<EntityResponseType> {
        return this.http.post<ITipoUnidade>(this.resourceUrl, tipoUnidade, { observe: 'response' });
    }

    update(tipoUnidade: ITipoUnidade): Observable<EntityResponseType> {
        return this.http.put<ITipoUnidade>(this.resourceUrl, tipoUnidade, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipoUnidade>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoUnidade[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
