import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMotivoDiaNaoUtil } from 'app/shared/model/motivo-dia-nao-util.model';

type EntityResponseType = HttpResponse<IMotivoDiaNaoUtil>;
type EntityArrayResponseType = HttpResponse<IMotivoDiaNaoUtil[]>;

@Injectable({ providedIn: 'root' })
export class MotivoDiaNaoUtilService {
    public resourceUrl = SERVER_API_URL + 'api/motivo-dia-nao-utils';

    constructor(private http: HttpClient) {}

    create(motivoDiaNaoUtil: IMotivoDiaNaoUtil): Observable<EntityResponseType> {
        return this.http.post<IMotivoDiaNaoUtil>(this.resourceUrl, motivoDiaNaoUtil, { observe: 'response' });
    }

    update(motivoDiaNaoUtil: IMotivoDiaNaoUtil): Observable<EntityResponseType> {
        return this.http.put<IMotivoDiaNaoUtil>(this.resourceUrl, motivoDiaNaoUtil, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMotivoDiaNaoUtil>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMotivoDiaNaoUtil[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
