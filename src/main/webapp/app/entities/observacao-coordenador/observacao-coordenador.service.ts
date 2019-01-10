import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IObservacaoCoordenador } from 'app/shared/model/observacao-coordenador.model';

type EntityResponseType = HttpResponse<IObservacaoCoordenador>;
type EntityArrayResponseType = HttpResponse<IObservacaoCoordenador[]>;

@Injectable({ providedIn: 'root' })
export class ObservacaoCoordenadorService {
    public resourceUrl = SERVER_API_URL + 'api/observacao-coordenadors';

    constructor(private http: HttpClient) {}

    create(observacaoCoordenador: IObservacaoCoordenador): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(observacaoCoordenador);
        return this.http
            .post<IObservacaoCoordenador>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(observacaoCoordenador: IObservacaoCoordenador): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(observacaoCoordenador);
        return this.http
            .put<IObservacaoCoordenador>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IObservacaoCoordenador>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IObservacaoCoordenador[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(observacaoCoordenador: IObservacaoCoordenador): IObservacaoCoordenador {
        const copy: IObservacaoCoordenador = Object.assign({}, observacaoCoordenador, {
            data:
                observacaoCoordenador.data != null && observacaoCoordenador.data.isValid()
                    ? observacaoCoordenador.data.format(DATE_FORMAT)
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
            res.body.forEach((observacaoCoordenador: IObservacaoCoordenador) => {
                observacaoCoordenador.data = observacaoCoordenador.data != null ? moment(observacaoCoordenador.data) : null;
            });
        }
        return res;
    }
}
