import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBimestre } from 'app/shared/model/bimestre.model';

type EntityResponseType = HttpResponse<IBimestre>;
type EntityArrayResponseType = HttpResponse<IBimestre[]>;

@Injectable({ providedIn: 'root' })
export class BimestreService {
    public resourceUrl = SERVER_API_URL + 'api/bimestres';

    constructor(private http: HttpClient) {}

    create(bimestre: IBimestre): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(bimestre);
        return this.http
            .post<IBimestre>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(bimestre: IBimestre): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(bimestre);
        return this.http
            .put<IBimestre>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IBimestre>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IBimestre[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(bimestre: IBimestre): IBimestre {
        const copy: IBimestre = Object.assign({}, bimestre, {
            dataInicio: bimestre.dataInicio != null && bimestre.dataInicio.isValid() ? bimestre.dataInicio.format(DATE_FORMAT) : null,
            dataFim: bimestre.dataFim != null && bimestre.dataFim.isValid() ? bimestre.dataFim.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dataInicio = res.body.dataInicio != null ? moment(res.body.dataInicio) : null;
            res.body.dataFim = res.body.dataFim != null ? moment(res.body.dataFim) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((bimestre: IBimestre) => {
                bimestre.dataInicio = bimestre.dataInicio != null ? moment(bimestre.dataInicio) : null;
                bimestre.dataFim = bimestre.dataFim != null ? moment(bimestre.dataFim) : null;
            });
        }
        return res;
    }
}
