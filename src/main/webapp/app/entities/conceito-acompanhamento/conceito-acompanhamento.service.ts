import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IConceitoAcompanhamento } from 'app/shared/model/conceito-acompanhamento.model';

type EntityResponseType = HttpResponse<IConceitoAcompanhamento>;
type EntityArrayResponseType = HttpResponse<IConceitoAcompanhamento[]>;

@Injectable({ providedIn: 'root' })
export class ConceitoAcompanhamentoService {
    public resourceUrl = SERVER_API_URL + 'api/conceito-acompanhamentos';

    constructor(private http: HttpClient) {}

    create(conceitoAcompanhamento: IConceitoAcompanhamento): Observable<EntityResponseType> {
        return this.http.post<IConceitoAcompanhamento>(this.resourceUrl, conceitoAcompanhamento, { observe: 'response' });
    }

    update(conceitoAcompanhamento: IConceitoAcompanhamento): Observable<EntityResponseType> {
        return this.http.put<IConceitoAcompanhamento>(this.resourceUrl, conceitoAcompanhamento, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IConceitoAcompanhamento>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IConceitoAcompanhamento[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
