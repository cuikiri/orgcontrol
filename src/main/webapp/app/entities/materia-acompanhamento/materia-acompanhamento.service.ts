import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMateriaAcompanhamento } from 'app/shared/model/materia-acompanhamento.model';

type EntityResponseType = HttpResponse<IMateriaAcompanhamento>;
type EntityArrayResponseType = HttpResponse<IMateriaAcompanhamento[]>;

@Injectable({ providedIn: 'root' })
export class MateriaAcompanhamentoService {
    public resourceUrl = SERVER_API_URL + 'api/materia-acompanhamentos';

    constructor(private http: HttpClient) {}

    create(materiaAcompanhamento: IMateriaAcompanhamento): Observable<EntityResponseType> {
        return this.http.post<IMateriaAcompanhamento>(this.resourceUrl, materiaAcompanhamento, { observe: 'response' });
    }

    update(materiaAcompanhamento: IMateriaAcompanhamento): Observable<EntityResponseType> {
        return this.http.put<IMateriaAcompanhamento>(this.resourceUrl, materiaAcompanhamento, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMateriaAcompanhamento>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMateriaAcompanhamento[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
