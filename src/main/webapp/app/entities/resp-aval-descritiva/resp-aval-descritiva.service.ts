import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRespAvalDescritiva } from 'app/shared/model/resp-aval-descritiva.model';

type EntityResponseType = HttpResponse<IRespAvalDescritiva>;
type EntityArrayResponseType = HttpResponse<IRespAvalDescritiva[]>;

@Injectable({ providedIn: 'root' })
export class RespAvalDescritivaService {
    public resourceUrl = SERVER_API_URL + 'api/resp-aval-descritivas';

    constructor(private http: HttpClient) {}

    create(respAvalDescritiva: IRespAvalDescritiva): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respAvalDescritiva);
        return this.http
            .post<IRespAvalDescritiva>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(respAvalDescritiva: IRespAvalDescritiva): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respAvalDescritiva);
        return this.http
            .put<IRespAvalDescritiva>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRespAvalDescritiva>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRespAvalDescritiva[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(respAvalDescritiva: IRespAvalDescritiva): IRespAvalDescritiva {
        const copy: IRespAvalDescritiva = Object.assign({}, respAvalDescritiva, {
            data: respAvalDescritiva.data != null && respAvalDescritiva.data.isValid() ? respAvalDescritiva.data.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.data = res.body.data != null ? moment(res.body.data) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((respAvalDescritiva: IRespAvalDescritiva) => {
                respAvalDescritiva.data = respAvalDescritiva.data != null ? moment(respAvalDescritiva.data) : null;
            });
        }
        return res;
    }
}
