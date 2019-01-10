import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IConceito } from 'app/shared/model/conceito.model';

type EntityResponseType = HttpResponse<IConceito>;
type EntityArrayResponseType = HttpResponse<IConceito[]>;

@Injectable({ providedIn: 'root' })
export class ConceitoService {
    public resourceUrl = SERVER_API_URL + 'api/conceitos';

    constructor(private http: HttpClient) {}

    create(conceito: IConceito): Observable<EntityResponseType> {
        return this.http.post<IConceito>(this.resourceUrl, conceito, { observe: 'response' });
    }

    update(conceito: IConceito): Observable<EntityResponseType> {
        return this.http.put<IConceito>(this.resourceUrl, conceito, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IConceito>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IConceito[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
