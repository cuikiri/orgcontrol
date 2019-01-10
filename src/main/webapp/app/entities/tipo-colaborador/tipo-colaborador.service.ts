import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoColaborador } from 'app/shared/model/tipo-colaborador.model';

type EntityResponseType = HttpResponse<ITipoColaborador>;
type EntityArrayResponseType = HttpResponse<ITipoColaborador[]>;

@Injectable({ providedIn: 'root' })
export class TipoColaboradorService {
    public resourceUrl = SERVER_API_URL + 'api/tipo-colaboradors';

    constructor(private http: HttpClient) {}

    create(tipoColaborador: ITipoColaborador): Observable<EntityResponseType> {
        return this.http.post<ITipoColaborador>(this.resourceUrl, tipoColaborador, { observe: 'response' });
    }

    update(tipoColaborador: ITipoColaborador): Observable<EntityResponseType> {
        return this.http.put<ITipoColaborador>(this.resourceUrl, tipoColaborador, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipoColaborador>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoColaborador[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
