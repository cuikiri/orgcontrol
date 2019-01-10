import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRespAtivDescritiva } from 'app/shared/model/resp-ativ-descritiva.model';

type EntityResponseType = HttpResponse<IRespAtivDescritiva>;
type EntityArrayResponseType = HttpResponse<IRespAtivDescritiva[]>;

@Injectable({ providedIn: 'root' })
export class RespAtivDescritivaService {
    public resourceUrl = SERVER_API_URL + 'api/resp-ativ-descritivas';

    constructor(private http: HttpClient) {}

    create(respAtivDescritiva: IRespAtivDescritiva): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respAtivDescritiva);
        return this.http
            .post<IRespAtivDescritiva>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(respAtivDescritiva: IRespAtivDescritiva): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respAtivDescritiva);
        return this.http
            .put<IRespAtivDescritiva>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRespAtivDescritiva>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRespAtivDescritiva[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(respAtivDescritiva: IRespAtivDescritiva): IRespAtivDescritiva {
        const copy: IRespAtivDescritiva = Object.assign({}, respAtivDescritiva, {
            data: respAtivDescritiva.data != null && respAtivDescritiva.data.isValid() ? respAtivDescritiva.data.format(DATE_FORMAT) : null
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
            res.body.forEach((respAtivDescritiva: IRespAtivDescritiva) => {
                respAtivDescritiva.data = respAtivDescritiva.data != null ? moment(respAtivDescritiva.data) : null;
            });
        }
        return res;
    }
}
