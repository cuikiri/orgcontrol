import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICaracteristicasPsiquicas } from 'app/shared/model/caracteristicas-psiquicas.model';

type EntityResponseType = HttpResponse<ICaracteristicasPsiquicas>;
type EntityArrayResponseType = HttpResponse<ICaracteristicasPsiquicas[]>;

@Injectable({ providedIn: 'root' })
export class CaracteristicasPsiquicasService {
    public resourceUrl = SERVER_API_URL + 'api/caracteristicas-psiquicas';

    constructor(private http: HttpClient) {}

    create(caracteristicasPsiquicas: ICaracteristicasPsiquicas): Observable<EntityResponseType> {
        return this.http.post<ICaracteristicasPsiquicas>(this.resourceUrl, caracteristicasPsiquicas, { observe: 'response' });
    }

    update(caracteristicasPsiquicas: ICaracteristicasPsiquicas): Observable<EntityResponseType> {
        return this.http.put<ICaracteristicasPsiquicas>(this.resourceUrl, caracteristicasPsiquicas, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICaracteristicasPsiquicas>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICaracteristicasPsiquicas[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
