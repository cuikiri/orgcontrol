import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILocomocao } from 'app/shared/model/locomocao.model';

type EntityResponseType = HttpResponse<ILocomocao>;
type EntityArrayResponseType = HttpResponse<ILocomocao[]>;

@Injectable({ providedIn: 'root' })
export class LocomocaoService {
    public resourceUrl = SERVER_API_URL + 'api/locomocaos';

    constructor(private http: HttpClient) {}

    create(locomocao: ILocomocao): Observable<EntityResponseType> {
        return this.http.post<ILocomocao>(this.resourceUrl, locomocao, { observe: 'response' });
    }

    update(locomocao: ILocomocao): Observable<EntityResponseType> {
        return this.http.put<ILocomocao>(this.resourceUrl, locomocao, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ILocomocao>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILocomocao[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
