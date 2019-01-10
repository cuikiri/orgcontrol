import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IColaborador } from 'app/shared/model/colaborador.model';

type EntityResponseType = HttpResponse<IColaborador>;
type EntityArrayResponseType = HttpResponse<IColaborador[]>;

@Injectable({ providedIn: 'root' })
export class ColaboradorService {
    public resourceUrl = SERVER_API_URL + 'api/colaboradors';

    constructor(private http: HttpClient) {}

    create(colaborador: IColaborador): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(colaborador);
        return this.http
            .post<IColaborador>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(colaborador: IColaborador): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(colaborador);
        return this.http
            .put<IColaborador>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IColaborador>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IColaborador[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(colaborador: IColaborador): IColaborador {
        const copy: IColaborador = Object.assign({}, colaborador, {
            dataCadastro:
                colaborador.dataCadastro != null && colaborador.dataCadastro.isValid()
                    ? colaborador.dataCadastro.format(DATE_FORMAT)
                    : null,
            dataAdmissao:
                colaborador.dataAdmissao != null && colaborador.dataAdmissao.isValid() ? colaborador.dataAdmissao.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dataCadastro = res.body.dataCadastro != null ? moment(res.body.dataCadastro) : null;
            res.body.dataAdmissao = res.body.dataAdmissao != null ? moment(res.body.dataAdmissao) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((colaborador: IColaborador) => {
                colaborador.dataCadastro = colaborador.dataCadastro != null ? moment(colaborador.dataCadastro) : null;
                colaborador.dataAdmissao = colaborador.dataAdmissao != null ? moment(colaborador.dataAdmissao) : null;
            });
        }
        return res;
    }
}
