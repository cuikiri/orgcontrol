import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IObservacaoProfessor } from 'app/shared/model/observacao-professor.model';

type EntityResponseType = HttpResponse<IObservacaoProfessor>;
type EntityArrayResponseType = HttpResponse<IObservacaoProfessor[]>;

@Injectable({ providedIn: 'root' })
export class ObservacaoProfessorService {
    public resourceUrl = SERVER_API_URL + 'api/observacao-professors';

    constructor(private http: HttpClient) {}

    create(observacaoProfessor: IObservacaoProfessor): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(observacaoProfessor);
        return this.http
            .post<IObservacaoProfessor>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(observacaoProfessor: IObservacaoProfessor): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(observacaoProfessor);
        return this.http
            .put<IObservacaoProfessor>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IObservacaoProfessor>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IObservacaoProfessor[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(observacaoProfessor: IObservacaoProfessor): IObservacaoProfessor {
        const copy: IObservacaoProfessor = Object.assign({}, observacaoProfessor, {
            data:
                observacaoProfessor.data != null && observacaoProfessor.data.isValid() ? observacaoProfessor.data.format(DATE_FORMAT) : null
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
            res.body.forEach((observacaoProfessor: IObservacaoProfessor) => {
                observacaoProfessor.data = observacaoProfessor.data != null ? moment(observacaoProfessor.data) : null;
            });
        }
        return res;
    }
}
