import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFechamentoBimestre } from 'app/shared/model/fechamento-bimestre.model';

type EntityResponseType = HttpResponse<IFechamentoBimestre>;
type EntityArrayResponseType = HttpResponse<IFechamentoBimestre[]>;

@Injectable({ providedIn: 'root' })
export class FechamentoBimestreService {
    public resourceUrl = SERVER_API_URL + 'api/fechamento-bimestres';

    constructor(private http: HttpClient) {}

    create(fechamentoBimestre: IFechamentoBimestre): Observable<EntityResponseType> {
        return this.http.post<IFechamentoBimestre>(this.resourceUrl, fechamentoBimestre, { observe: 'response' });
    }

    update(fechamentoBimestre: IFechamentoBimestre): Observable<EntityResponseType> {
        return this.http.put<IFechamentoBimestre>(this.resourceUrl, fechamentoBimestre, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFechamentoBimestre>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFechamentoBimestre[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
