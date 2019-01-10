import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRespostaAvaliacao } from 'app/shared/model/resposta-avaliacao.model';

type EntityResponseType = HttpResponse<IRespostaAvaliacao>;
type EntityArrayResponseType = HttpResponse<IRespostaAvaliacao[]>;

@Injectable({ providedIn: 'root' })
export class RespostaAvaliacaoService {
    public resourceUrl = SERVER_API_URL + 'api/resposta-avaliacaos';

    constructor(private http: HttpClient) {}

    create(respostaAvaliacao: IRespostaAvaliacao): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respostaAvaliacao);
        return this.http
            .post<IRespostaAvaliacao>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(respostaAvaliacao: IRespostaAvaliacao): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respostaAvaliacao);
        return this.http
            .put<IRespostaAvaliacao>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRespostaAvaliacao>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRespostaAvaliacao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(respostaAvaliacao: IRespostaAvaliacao): IRespostaAvaliacao {
        const copy: IRespostaAvaliacao = Object.assign({}, respostaAvaliacao, {
            data: respostaAvaliacao.data != null && respostaAvaliacao.data.isValid() ? respostaAvaliacao.data.format(DATE_FORMAT) : null
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
            res.body.forEach((respostaAvaliacao: IRespostaAvaliacao) => {
                respostaAvaliacao.data = respostaAvaliacao.data != null ? moment(respostaAvaliacao.data) : null;
            });
        }
        return res;
    }
}
