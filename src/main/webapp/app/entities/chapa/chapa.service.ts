import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IChapa } from 'app/shared/model/chapa.model';

type EntityResponseType = HttpResponse<IChapa>;
type EntityArrayResponseType = HttpResponse<IChapa[]>;

@Injectable({ providedIn: 'root' })
export class ChapaService {
    public resourceUrl = SERVER_API_URL + 'api/chapas';

    constructor(private http: HttpClient) {}

    create(chapa: IChapa): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(chapa);
        return this.http
            .post<IChapa>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(chapa: IChapa): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(chapa);
        return this.http
            .put<IChapa>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IChapa>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IChapa[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(chapa: IChapa): IChapa {
        const copy: IChapa = Object.assign({}, chapa, {
            dataCadastro: chapa.dataCadastro != null && chapa.dataCadastro.isValid() ? chapa.dataCadastro.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dataCadastro = res.body.dataCadastro != null ? moment(res.body.dataCadastro) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((chapa: IChapa) => {
                chapa.dataCadastro = chapa.dataCadastro != null ? moment(chapa.dataCadastro) : null;
            });
        }
        return res;
    }
}
