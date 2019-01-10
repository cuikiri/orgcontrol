import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRespostaAvaliacaoEconomica } from 'app/shared/model/resposta-avaliacao-economica.model';

type EntityResponseType = HttpResponse<IRespostaAvaliacaoEconomica>;
type EntityArrayResponseType = HttpResponse<IRespostaAvaliacaoEconomica[]>;

@Injectable({ providedIn: 'root' })
export class RespostaAvaliacaoEconomicaService {
    public resourceUrl = SERVER_API_URL + 'api/resposta-avaliacao-economicas';

    constructor(private http: HttpClient) {}

    create(respostaAvaliacaoEconomica: IRespostaAvaliacaoEconomica): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respostaAvaliacaoEconomica);
        return this.http
            .post<IRespostaAvaliacaoEconomica>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(respostaAvaliacaoEconomica: IRespostaAvaliacaoEconomica): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respostaAvaliacaoEconomica);
        return this.http
            .put<IRespostaAvaliacaoEconomica>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRespostaAvaliacaoEconomica>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRespostaAvaliacaoEconomica[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(respostaAvaliacaoEconomica: IRespostaAvaliacaoEconomica): IRespostaAvaliacaoEconomica {
        const copy: IRespostaAvaliacaoEconomica = Object.assign({}, respostaAvaliacaoEconomica, {
            data:
                respostaAvaliacaoEconomica.data != null && respostaAvaliacaoEconomica.data.isValid()
                    ? respostaAvaliacaoEconomica.data.format(DATE_FORMAT)
                    : null
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
            res.body.forEach((respostaAvaliacaoEconomica: IRespostaAvaliacaoEconomica) => {
                respostaAvaliacaoEconomica.data = respostaAvaliacaoEconomica.data != null ? moment(respostaAvaliacaoEconomica.data) : null;
            });
        }
        return res;
    }
}
