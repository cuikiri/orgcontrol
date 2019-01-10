import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRespAvalOptativa } from 'app/shared/model/resp-aval-optativa.model';

type EntityResponseType = HttpResponse<IRespAvalOptativa>;
type EntityArrayResponseType = HttpResponse<IRespAvalOptativa[]>;

@Injectable({ providedIn: 'root' })
export class RespAvalOptativaService {
    public resourceUrl = SERVER_API_URL + 'api/resp-aval-optativas';

    constructor(private http: HttpClient) {}

    create(respAvalOptativa: IRespAvalOptativa): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respAvalOptativa);
        return this.http
            .post<IRespAvalOptativa>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(respAvalOptativa: IRespAvalOptativa): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respAvalOptativa);
        return this.http
            .put<IRespAvalOptativa>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRespAvalOptativa>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRespAvalOptativa[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(respAvalOptativa: IRespAvalOptativa): IRespAvalOptativa {
        const copy: IRespAvalOptativa = Object.assign({}, respAvalOptativa, {
            data: respAvalOptativa.data != null && respAvalOptativa.data.isValid() ? respAvalOptativa.data.format(DATE_FORMAT) : null
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
            res.body.forEach((respAvalOptativa: IRespAvalOptativa) => {
                respAvalOptativa.data = respAvalOptativa.data != null ? moment(respAvalOptativa.data) : null;
            });
        }
        return res;
    }
}
