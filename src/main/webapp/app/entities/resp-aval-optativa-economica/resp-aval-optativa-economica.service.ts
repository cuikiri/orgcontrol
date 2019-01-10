import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRespAvalOptativaEconomica } from 'app/shared/model/resp-aval-optativa-economica.model';

type EntityResponseType = HttpResponse<IRespAvalOptativaEconomica>;
type EntityArrayResponseType = HttpResponse<IRespAvalOptativaEconomica[]>;

@Injectable({ providedIn: 'root' })
export class RespAvalOptativaEconomicaService {
    public resourceUrl = SERVER_API_URL + 'api/resp-aval-optativa-economicas';

    constructor(private http: HttpClient) {}

    create(respAvalOptativaEconomica: IRespAvalOptativaEconomica): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respAvalOptativaEconomica);
        return this.http
            .post<IRespAvalOptativaEconomica>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(respAvalOptativaEconomica: IRespAvalOptativaEconomica): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respAvalOptativaEconomica);
        return this.http
            .put<IRespAvalOptativaEconomica>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRespAvalOptativaEconomica>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRespAvalOptativaEconomica[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(respAvalOptativaEconomica: IRespAvalOptativaEconomica): IRespAvalOptativaEconomica {
        const copy: IRespAvalOptativaEconomica = Object.assign({}, respAvalOptativaEconomica, {
            data:
                respAvalOptativaEconomica.data != null && respAvalOptativaEconomica.data.isValid()
                    ? respAvalOptativaEconomica.data.format(DATE_FORMAT)
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
            res.body.forEach((respAvalOptativaEconomica: IRespAvalOptativaEconomica) => {
                respAvalOptativaEconomica.data = respAvalOptativaEconomica.data != null ? moment(respAvalOptativaEconomica.data) : null;
            });
        }
        return res;
    }
}
