import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDadoBiologico } from 'app/shared/model/dado-biologico.model';

type EntityResponseType = HttpResponse<IDadoBiologico>;
type EntityArrayResponseType = HttpResponse<IDadoBiologico[]>;

@Injectable({ providedIn: 'root' })
export class DadoBiologicoService {
    public resourceUrl = SERVER_API_URL + 'api/dado-biologicos';

    constructor(private http: HttpClient) {}

    create(dadoBiologico: IDadoBiologico): Observable<EntityResponseType> {
        return this.http.post<IDadoBiologico>(this.resourceUrl, dadoBiologico, { observe: 'response' });
    }

    update(dadoBiologico: IDadoBiologico): Observable<EntityResponseType> {
        return this.http.put<IDadoBiologico>(this.resourceUrl, dadoBiologico, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDadoBiologico>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDadoBiologico[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
