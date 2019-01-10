import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBimestreAcompanhamento } from 'app/shared/model/bimestre-acompanhamento.model';

type EntityResponseType = HttpResponse<IBimestreAcompanhamento>;
type EntityArrayResponseType = HttpResponse<IBimestreAcompanhamento[]>;

@Injectable({ providedIn: 'root' })
export class BimestreAcompanhamentoService {
    public resourceUrl = SERVER_API_URL + 'api/bimestre-acompanhamentos';

    constructor(private http: HttpClient) {}

    create(bimestreAcompanhamento: IBimestreAcompanhamento): Observable<EntityResponseType> {
        return this.http.post<IBimestreAcompanhamento>(this.resourceUrl, bimestreAcompanhamento, { observe: 'response' });
    }

    update(bimestreAcompanhamento: IBimestreAcompanhamento): Observable<EntityResponseType> {
        return this.http.put<IBimestreAcompanhamento>(this.resourceUrl, bimestreAcompanhamento, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IBimestreAcompanhamento>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBimestreAcompanhamento[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
