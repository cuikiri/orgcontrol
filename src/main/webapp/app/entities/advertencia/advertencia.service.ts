import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAdvertencia } from 'app/shared/model/advertencia.model';

type EntityResponseType = HttpResponse<IAdvertencia>;
type EntityArrayResponseType = HttpResponse<IAdvertencia[]>;

@Injectable({ providedIn: 'root' })
export class AdvertenciaService {
    public resourceUrl = SERVER_API_URL + 'api/advertencias';

    constructor(private http: HttpClient) {}

    create(advertencia: IAdvertencia): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(advertencia);
        return this.http
            .post<IAdvertencia>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(advertencia: IAdvertencia): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(advertencia);
        return this.http
            .put<IAdvertencia>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAdvertencia>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAdvertencia[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(advertencia: IAdvertencia): IAdvertencia {
        const copy: IAdvertencia = Object.assign({}, advertencia, {
            data: advertencia.data != null && advertencia.data.isValid() ? advertencia.data.format(DATE_FORMAT) : null,
            dataAdvertencia:
                advertencia.dataAdvertencia != null && advertencia.dataAdvertencia.isValid()
                    ? advertencia.dataAdvertencia.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.data = res.body.data != null ? moment(res.body.data) : null;
            res.body.dataAdvertencia = res.body.dataAdvertencia != null ? moment(res.body.dataAdvertencia) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((advertencia: IAdvertencia) => {
                advertencia.data = advertencia.data != null ? moment(advertencia.data) : null;
                advertencia.dataAdvertencia = advertencia.dataAdvertencia != null ? moment(advertencia.dataAdvertencia) : null;
            });
        }
        return res;
    }
}
