import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRespAvalDescritivaEconomica } from 'app/shared/model/resp-aval-descritiva-economica.model';

type EntityResponseType = HttpResponse<IRespAvalDescritivaEconomica>;
type EntityArrayResponseType = HttpResponse<IRespAvalDescritivaEconomica[]>;

@Injectable({ providedIn: 'root' })
export class RespAvalDescritivaEconomicaService {
    public resourceUrl = SERVER_API_URL + 'api/resp-aval-descritiva-economicas';

    constructor(private http: HttpClient) {}

    create(respAvalDescritivaEconomica: IRespAvalDescritivaEconomica): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respAvalDescritivaEconomica);
        return this.http
            .post<IRespAvalDescritivaEconomica>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(respAvalDescritivaEconomica: IRespAvalDescritivaEconomica): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(respAvalDescritivaEconomica);
        return this.http
            .put<IRespAvalDescritivaEconomica>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRespAvalDescritivaEconomica>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRespAvalDescritivaEconomica[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(respAvalDescritivaEconomica: IRespAvalDescritivaEconomica): IRespAvalDescritivaEconomica {
        const copy: IRespAvalDescritivaEconomica = Object.assign({}, respAvalDescritivaEconomica, {
            data:
                respAvalDescritivaEconomica.data != null && respAvalDescritivaEconomica.data.isValid()
                    ? respAvalDescritivaEconomica.data.format(DATE_FORMAT)
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
            res.body.forEach((respAvalDescritivaEconomica: IRespAvalDescritivaEconomica) => {
                respAvalDescritivaEconomica.data =
                    respAvalDescritivaEconomica.data != null ? moment(respAvalDescritivaEconomica.data) : null;
            });
        }
        return res;
    }
}
