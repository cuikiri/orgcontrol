import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGeneralidade } from 'app/shared/model/generalidade.model';

type EntityResponseType = HttpResponse<IGeneralidade>;
type EntityArrayResponseType = HttpResponse<IGeneralidade[]>;

@Injectable({ providedIn: 'root' })
export class GeneralidadeService {
    public resourceUrl = SERVER_API_URL + 'api/generalidades';

    constructor(private http: HttpClient) {}

    create(generalidade: IGeneralidade): Observable<EntityResponseType> {
        return this.http.post<IGeneralidade>(this.resourceUrl, generalidade, { observe: 'response' });
    }

    update(generalidade: IGeneralidade): Observable<EntityResponseType> {
        return this.http.put<IGeneralidade>(this.resourceUrl, generalidade, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGeneralidade>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGeneralidade[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
