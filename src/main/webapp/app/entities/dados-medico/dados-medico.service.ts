import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDadosMedico } from 'app/shared/model/dados-medico.model';

type EntityResponseType = HttpResponse<IDadosMedico>;
type EntityArrayResponseType = HttpResponse<IDadosMedico[]>;

@Injectable({ providedIn: 'root' })
export class DadosMedicoService {
    public resourceUrl = SERVER_API_URL + 'api/dados-medicos';

    constructor(private http: HttpClient) {}

    create(dadosMedico: IDadosMedico): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(dadosMedico);
        return this.http
            .post<IDadosMedico>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(dadosMedico: IDadosMedico): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(dadosMedico);
        return this.http
            .put<IDadosMedico>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDadosMedico>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDadosMedico[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(dadosMedico: IDadosMedico): IDadosMedico {
        const copy: IDadosMedico = Object.assign({}, dadosMedico, {
            data: dadosMedico.data != null && dadosMedico.data.isValid() ? dadosMedico.data.format(DATE_FORMAT) : null
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
            res.body.forEach((dadosMedico: IDadosMedico) => {
                dadosMedico.data = dadosMedico.data != null ? moment(dadosMedico.data) : null;
            });
        }
        return res;
    }
}
