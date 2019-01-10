import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRespostaAtividade } from 'app/shared/model/resposta-atividade.model';

type EntityResponseType = HttpResponse<IRespostaAtividade>;
type EntityArrayResponseType = HttpResponse<IRespostaAtividade[]>;

@Injectable({ providedIn: 'root' })
export class RespostaAtividadeService {
    public resourceUrl = SERVER_API_URL + 'api/resposta-atividades';

    constructor(private http: HttpClient) {}

    create(respostaAtividade: IRespostaAtividade): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respostaAtividade);
        return this.http
            .post<IRespostaAtividade>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(respostaAtividade: IRespostaAtividade): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respostaAtividade);
        return this.http
            .put<IRespostaAtividade>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRespostaAtividade>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRespostaAtividade[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(respostaAtividade: IRespostaAtividade): IRespostaAtividade {
        const copy: IRespostaAtividade = Object.assign({}, respostaAtividade, {
            data: respostaAtividade.data != null && respostaAtividade.data.isValid() ? respostaAtividade.data.format(DATE_FORMAT) : null
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
            res.body.forEach((respostaAtividade: IRespostaAtividade) => {
                respostaAtividade.data = respostaAtividade.data != null ? moment(respostaAtividade.data) : null;
            });
        }
        return res;
    }
}
