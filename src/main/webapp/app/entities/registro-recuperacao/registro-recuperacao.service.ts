import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRegistroRecuperacao } from 'app/shared/model/registro-recuperacao.model';

type EntityResponseType = HttpResponse<IRegistroRecuperacao>;
type EntityArrayResponseType = HttpResponse<IRegistroRecuperacao[]>;

@Injectable({ providedIn: 'root' })
export class RegistroRecuperacaoService {
    public resourceUrl = SERVER_API_URL + 'api/registro-recuperacaos';

    constructor(private http: HttpClient) {}

    create(registroRecuperacao: IRegistroRecuperacao): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(registroRecuperacao);
        return this.http
            .post<IRegistroRecuperacao>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(registroRecuperacao: IRegistroRecuperacao): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(registroRecuperacao);
        return this.http
            .put<IRegistroRecuperacao>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRegistroRecuperacao>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRegistroRecuperacao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(registroRecuperacao: IRegistroRecuperacao): IRegistroRecuperacao {
        const copy: IRegistroRecuperacao = Object.assign({}, registroRecuperacao, {
            data:
                registroRecuperacao.data != null && registroRecuperacao.data.isValid() ? registroRecuperacao.data.format(DATE_FORMAT) : null
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
            res.body.forEach((registroRecuperacao: IRegistroRecuperacao) => {
                registroRecuperacao.data = registroRecuperacao.data != null ? moment(registroRecuperacao.data) : null;
            });
        }
        return res;
    }
}
