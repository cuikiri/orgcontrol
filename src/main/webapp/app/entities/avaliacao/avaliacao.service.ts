import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAvaliacao } from 'app/shared/model/avaliacao.model';

type EntityResponseType = HttpResponse<IAvaliacao>;
type EntityArrayResponseType = HttpResponse<IAvaliacao[]>;

@Injectable({ providedIn: 'root' })
export class AvaliacaoService {
    public resourceUrl = SERVER_API_URL + 'api/avaliacaos';

    constructor(private http: HttpClient) {}

    create(avaliacao: IAvaliacao): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(avaliacao);
        return this.http
            .post<IAvaliacao>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(avaliacao: IAvaliacao): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(avaliacao);
        return this.http
            .put<IAvaliacao>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAvaliacao>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAvaliacao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(avaliacao: IAvaliacao): IAvaliacao {
        const copy: IAvaliacao = Object.assign({}, avaliacao, {
            data: avaliacao.data != null && avaliacao.data.isValid() ? avaliacao.data.format(DATE_FORMAT) : null,
            dataInicio: avaliacao.dataInicio != null && avaliacao.dataInicio.isValid() ? avaliacao.dataInicio.format(DATE_FORMAT) : null,
            dataFim: avaliacao.dataFim != null && avaliacao.dataFim.isValid() ? avaliacao.dataFim.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.data = res.body.data != null ? moment(res.body.data) : null;
            res.body.dataInicio = res.body.dataInicio != null ? moment(res.body.dataInicio) : null;
            res.body.dataFim = res.body.dataFim != null ? moment(res.body.dataFim) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((avaliacao: IAvaliacao) => {
                avaliacao.data = avaliacao.data != null ? moment(avaliacao.data) : null;
                avaliacao.dataInicio = avaliacao.dataInicio != null ? moment(avaliacao.dataInicio) : null;
                avaliacao.dataFim = avaliacao.dataFim != null ? moment(avaliacao.dataFim) : null;
            });
        }
        return res;
    }
}
