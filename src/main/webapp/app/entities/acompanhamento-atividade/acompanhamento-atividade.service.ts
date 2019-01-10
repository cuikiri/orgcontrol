import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAcompanhamentoAtividade } from 'app/shared/model/acompanhamento-atividade.model';

type EntityResponseType = HttpResponse<IAcompanhamentoAtividade>;
type EntityArrayResponseType = HttpResponse<IAcompanhamentoAtividade[]>;

@Injectable({ providedIn: 'root' })
export class AcompanhamentoAtividadeService {
    public resourceUrl = SERVER_API_URL + 'api/acompanhamento-atividades';

    constructor(private http: HttpClient) {}

    create(acompanhamentoAtividade: IAcompanhamentoAtividade): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(acompanhamentoAtividade);
        return this.http
            .post<IAcompanhamentoAtividade>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(acompanhamentoAtividade: IAcompanhamentoAtividade): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(acompanhamentoAtividade);
        return this.http
            .put<IAcompanhamentoAtividade>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAcompanhamentoAtividade>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAcompanhamentoAtividade[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(acompanhamentoAtividade: IAcompanhamentoAtividade): IAcompanhamentoAtividade {
        const copy: IAcompanhamentoAtividade = Object.assign({}, acompanhamentoAtividade, {
            data:
                acompanhamentoAtividade.data != null && acompanhamentoAtividade.data.isValid()
                    ? acompanhamentoAtividade.data.format(DATE_FORMAT)
                    : null,
            dataInicio:
                acompanhamentoAtividade.dataInicio != null && acompanhamentoAtividade.dataInicio.isValid()
                    ? acompanhamentoAtividade.dataInicio.format(DATE_FORMAT)
                    : null,
            dataFim:
                acompanhamentoAtividade.dataFim != null && acompanhamentoAtividade.dataFim.isValid()
                    ? acompanhamentoAtividade.dataFim.format(DATE_FORMAT)
                    : null
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
            res.body.forEach((acompanhamentoAtividade: IAcompanhamentoAtividade) => {
                acompanhamentoAtividade.data = acompanhamentoAtividade.data != null ? moment(acompanhamentoAtividade.data) : null;
                acompanhamentoAtividade.dataInicio =
                    acompanhamentoAtividade.dataInicio != null ? moment(acompanhamentoAtividade.dataInicio) : null;
                acompanhamentoAtividade.dataFim = acompanhamentoAtividade.dataFim != null ? moment(acompanhamentoAtividade.dataFim) : null;
            });
        }
        return res;
    }
}
