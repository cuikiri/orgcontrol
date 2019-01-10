import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMatricula } from 'app/shared/model/matricula.model';

type EntityResponseType = HttpResponse<IMatricula>;
type EntityArrayResponseType = HttpResponse<IMatricula[]>;

@Injectable({ providedIn: 'root' })
export class MatriculaService {
    public resourceUrl = SERVER_API_URL + 'api/matriculas';

    constructor(private http: HttpClient) {}

    create(matricula: IMatricula): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(matricula);
        return this.http
            .post<IMatricula>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(matricula: IMatricula): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(matricula);
        return this.http
            .put<IMatricula>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMatricula>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMatricula[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(matricula: IMatricula): IMatricula {
        const copy: IMatricula = Object.assign({}, matricula, {
            data: matricula.data != null && matricula.data.isValid() ? matricula.data.format(DATE_FORMAT) : null
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
            res.body.forEach((matricula: IMatricula) => {
                matricula.data = matricula.data != null ? moment(matricula.data) : null;
            });
        }
        return res;
    }
}
