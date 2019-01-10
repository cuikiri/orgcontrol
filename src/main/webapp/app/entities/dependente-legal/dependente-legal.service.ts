import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDependenteLegal } from 'app/shared/model/dependente-legal.model';

type EntityResponseType = HttpResponse<IDependenteLegal>;
type EntityArrayResponseType = HttpResponse<IDependenteLegal[]>;

@Injectable({ providedIn: 'root' })
export class DependenteLegalService {
    public resourceUrl = SERVER_API_URL + 'api/dependente-legals';

    constructor(private http: HttpClient) {}

    create(dependenteLegal: IDependenteLegal): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(dependenteLegal);
        return this.http
            .post<IDependenteLegal>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(dependenteLegal: IDependenteLegal): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(dependenteLegal);
        return this.http
            .put<IDependenteLegal>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDependenteLegal>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDependenteLegal[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(dependenteLegal: IDependenteLegal): IDependenteLegal {
        const copy: IDependenteLegal = Object.assign({}, dependenteLegal, {
            dataNascimento:
                dependenteLegal.dataNascimento != null && dependenteLegal.dataNascimento.isValid()
                    ? dependenteLegal.dataNascimento.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dataNascimento = res.body.dataNascimento != null ? moment(res.body.dataNascimento) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((dependenteLegal: IDependenteLegal) => {
                dependenteLegal.dataNascimento = dependenteLegal.dataNascimento != null ? moment(dependenteLegal.dataNascimento) : null;
            });
        }
        return res;
    }
}
