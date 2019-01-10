import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEleicao } from 'app/shared/model/eleicao.model';

type EntityResponseType = HttpResponse<IEleicao>;
type EntityArrayResponseType = HttpResponse<IEleicao[]>;

@Injectable({ providedIn: 'root' })
export class EleicaoService {
    public resourceUrl = SERVER_API_URL + 'api/eleicaos';

    constructor(private http: HttpClient) {}

    create(eleicao: IEleicao): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(eleicao);
        return this.http
            .post<IEleicao>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(eleicao: IEleicao): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(eleicao);
        return this.http
            .put<IEleicao>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEleicao>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEleicao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(eleicao: IEleicao): IEleicao {
        const copy: IEleicao = Object.assign({}, eleicao, {
            dataCadastro: eleicao.dataCadastro != null && eleicao.dataCadastro.isValid() ? eleicao.dataCadastro.format(DATE_FORMAT) : null,
            dataPleito: eleicao.dataPleito != null && eleicao.dataPleito.isValid() ? eleicao.dataPleito.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dataCadastro = res.body.dataCadastro != null ? moment(res.body.dataCadastro) : null;
            res.body.dataPleito = res.body.dataPleito != null ? moment(res.body.dataPleito) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((eleicao: IEleicao) => {
                eleicao.dataCadastro = eleicao.dataCadastro != null ? moment(eleicao.dataCadastro) : null;
                eleicao.dataPleito = eleicao.dataPleito != null ? moment(eleicao.dataPleito) : null;
            });
        }
        return res;
    }
}
