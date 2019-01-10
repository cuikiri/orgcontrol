import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRespAtivOptativa } from 'app/shared/model/resp-ativ-optativa.model';

type EntityResponseType = HttpResponse<IRespAtivOptativa>;
type EntityArrayResponseType = HttpResponse<IRespAtivOptativa[]>;

@Injectable({ providedIn: 'root' })
export class RespAtivOptativaService {
    public resourceUrl = SERVER_API_URL + 'api/resp-ativ-optativas';

    constructor(private http: HttpClient) {}

    create(respAtivOptativa: IRespAtivOptativa): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respAtivOptativa);
        return this.http
            .post<IRespAtivOptativa>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(respAtivOptativa: IRespAtivOptativa): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respAtivOptativa);
        return this.http
            .put<IRespAtivOptativa>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRespAtivOptativa>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRespAtivOptativa[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(respAtivOptativa: IRespAtivOptativa): IRespAtivOptativa {
        const copy: IRespAtivOptativa = Object.assign({}, respAtivOptativa, {
            data: respAtivOptativa.data != null && respAtivOptativa.data.isValid() ? respAtivOptativa.data.format(DATE_FORMAT) : null
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
            res.body.forEach((respAtivOptativa: IRespAtivOptativa) => {
                respAtivOptativa.data = respAtivOptativa.data != null ? moment(respAtivOptativa.data) : null;
            });
        }
        return res;
    }
}
