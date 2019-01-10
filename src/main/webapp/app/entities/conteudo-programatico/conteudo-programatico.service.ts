import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IConteudoProgramatico } from 'app/shared/model/conteudo-programatico.model';

type EntityResponseType = HttpResponse<IConteudoProgramatico>;
type EntityArrayResponseType = HttpResponse<IConteudoProgramatico[]>;

@Injectable({ providedIn: 'root' })
export class ConteudoProgramaticoService {
    public resourceUrl = SERVER_API_URL + 'api/conteudo-programaticos';

    constructor(private http: HttpClient) {}

    create(conteudoProgramatico: IConteudoProgramatico): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(conteudoProgramatico);
        return this.http
            .post<IConteudoProgramatico>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(conteudoProgramatico: IConteudoProgramatico): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(conteudoProgramatico);
        return this.http
            .put<IConteudoProgramatico>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IConteudoProgramatico>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IConteudoProgramatico[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(conteudoProgramatico: IConteudoProgramatico): IConteudoProgramatico {
        const copy: IConteudoProgramatico = Object.assign({}, conteudoProgramatico, {
            data:
                conteudoProgramatico.data != null && conteudoProgramatico.data.isValid()
                    ? conteudoProgramatico.data.format(DATE_FORMAT)
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
            res.body.forEach((conteudoProgramatico: IConteudoProgramatico) => {
                conteudoProgramatico.data = conteudoProgramatico.data != null ? moment(conteudoProgramatico.data) : null;
            });
        }
        return res;
    }
}
