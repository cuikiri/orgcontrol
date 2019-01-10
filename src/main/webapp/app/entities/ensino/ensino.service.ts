import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnsino } from 'app/shared/model/ensino.model';

type EntityResponseType = HttpResponse<IEnsino>;
type EntityArrayResponseType = HttpResponse<IEnsino[]>;

@Injectable({ providedIn: 'root' })
export class EnsinoService {
    public resourceUrl = SERVER_API_URL + 'api/ensinos';

    constructor(private http: HttpClient) {}

    create(ensino: IEnsino): Observable<EntityResponseType> {
        return this.http.post<IEnsino>(this.resourceUrl, ensino, { observe: 'response' });
    }

    update(ensino: IEnsino): Observable<EntityResponseType> {
        return this.http.put<IEnsino>(this.resourceUrl, ensino, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEnsino>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEnsino[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
