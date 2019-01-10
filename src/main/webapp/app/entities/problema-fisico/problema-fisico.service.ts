import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProblemaFisico } from 'app/shared/model/problema-fisico.model';

type EntityResponseType = HttpResponse<IProblemaFisico>;
type EntityArrayResponseType = HttpResponse<IProblemaFisico[]>;

@Injectable({ providedIn: 'root' })
export class ProblemaFisicoService {
    public resourceUrl = SERVER_API_URL + 'api/problema-fisicos';

    constructor(private http: HttpClient) {}

    create(problemaFisico: IProblemaFisico): Observable<EntityResponseType> {
        return this.http.post<IProblemaFisico>(this.resourceUrl, problemaFisico, { observe: 'response' });
    }

    update(problemaFisico: IProblemaFisico): Observable<EntityResponseType> {
        return this.http.put<IProblemaFisico>(this.resourceUrl, problemaFisico, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProblemaFisico>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProblemaFisico[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
