import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoConceito } from 'app/shared/model/tipo-conceito.model';

type EntityResponseType = HttpResponse<ITipoConceito>;
type EntityArrayResponseType = HttpResponse<ITipoConceito[]>;

@Injectable({ providedIn: 'root' })
export class TipoConceitoService {
    public resourceUrl = SERVER_API_URL + 'api/tipo-conceitos';

    constructor(private http: HttpClient) {}

    create(tipoConceito: ITipoConceito): Observable<EntityResponseType> {
        return this.http.post<ITipoConceito>(this.resourceUrl, tipoConceito, { observe: 'response' });
    }

    update(tipoConceito: ITipoConceito): Observable<EntityResponseType> {
        return this.http.put<ITipoConceito>(this.resourceUrl, tipoConceito, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipoConceito>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoConceito[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
