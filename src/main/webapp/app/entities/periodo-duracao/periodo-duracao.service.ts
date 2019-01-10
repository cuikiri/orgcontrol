import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPeriodoDuracao } from 'app/shared/model/periodo-duracao.model';

type EntityResponseType = HttpResponse<IPeriodoDuracao>;
type EntityArrayResponseType = HttpResponse<IPeriodoDuracao[]>;

@Injectable({ providedIn: 'root' })
export class PeriodoDuracaoService {
    public resourceUrl = SERVER_API_URL + 'api/periodo-duracaos';

    constructor(private http: HttpClient) {}

    create(periodoDuracao: IPeriodoDuracao): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(periodoDuracao);
        return this.http
            .post<IPeriodoDuracao>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(periodoDuracao: IPeriodoDuracao): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(periodoDuracao);
        return this.http
            .put<IPeriodoDuracao>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPeriodoDuracao>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPeriodoDuracao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(periodoDuracao: IPeriodoDuracao): IPeriodoDuracao {
        const copy: IPeriodoDuracao = Object.assign({}, periodoDuracao, {
            dataInicio:
                periodoDuracao.dataInicio != null && periodoDuracao.dataInicio.isValid()
                    ? periodoDuracao.dataInicio.format(DATE_FORMAT)
                    : null,
            dataFim: periodoDuracao.dataFim != null && periodoDuracao.dataFim.isValid() ? periodoDuracao.dataFim.format(DATE_FORMAT) : null
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
            res.body.forEach((periodoDuracao: IPeriodoDuracao) => {
                periodoDuracao.dataInicio = periodoDuracao.dataInicio != null ? moment(periodoDuracao.dataInicio) : null;
                periodoDuracao.dataFim = periodoDuracao.dataFim != null ? moment(periodoDuracao.dataFim) : null;
            });
        }
        return res;
    }
}
