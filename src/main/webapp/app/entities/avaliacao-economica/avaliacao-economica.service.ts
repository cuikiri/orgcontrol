import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAvaliacaoEconomica } from 'app/shared/model/avaliacao-economica.model';

type EntityResponseType = HttpResponse<IAvaliacaoEconomica>;
type EntityArrayResponseType = HttpResponse<IAvaliacaoEconomica[]>;

@Injectable({ providedIn: 'root' })
export class AvaliacaoEconomicaService {
    public resourceUrl = SERVER_API_URL + 'api/avaliacao-economicas';

    constructor(private http: HttpClient) {}

    create(avaliacaoEconomica: IAvaliacaoEconomica): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(avaliacaoEconomica);
        return this.http
            .post<IAvaliacaoEconomica>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(avaliacaoEconomica: IAvaliacaoEconomica): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(avaliacaoEconomica);
        return this.http
            .put<IAvaliacaoEconomica>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAvaliacaoEconomica>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAvaliacaoEconomica[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(avaliacaoEconomica: IAvaliacaoEconomica): IAvaliacaoEconomica {
        const copy: IAvaliacaoEconomica = Object.assign({}, avaliacaoEconomica, {
            data: avaliacaoEconomica.data != null && avaliacaoEconomica.data.isValid() ? avaliacaoEconomica.data.format(DATE_FORMAT) : null,
            dataInicio:
                avaliacaoEconomica.dataInicio != null && avaliacaoEconomica.dataInicio.isValid()
                    ? avaliacaoEconomica.dataInicio.format(DATE_FORMAT)
                    : null,
            dataFim:
                avaliacaoEconomica.dataFim != null && avaliacaoEconomica.dataFim.isValid()
                    ? avaliacaoEconomica.dataFim.format(DATE_FORMAT)
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
            res.body.forEach((avaliacaoEconomica: IAvaliacaoEconomica) => {
                avaliacaoEconomica.data = avaliacaoEconomica.data != null ? moment(avaliacaoEconomica.data) : null;
                avaliacaoEconomica.dataInicio = avaliacaoEconomica.dataInicio != null ? moment(avaliacaoEconomica.dataInicio) : null;
                avaliacaoEconomica.dataFim = avaliacaoEconomica.dataFim != null ? moment(avaliacaoEconomica.dataFim) : null;
            });
        }
        return res;
    }
}
