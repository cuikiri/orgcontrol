import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICalendarioInstituicao } from 'app/shared/model/calendario-instituicao.model';

type EntityResponseType = HttpResponse<ICalendarioInstituicao>;
type EntityArrayResponseType = HttpResponse<ICalendarioInstituicao[]>;

@Injectable({ providedIn: 'root' })
export class CalendarioInstituicaoService {
    public resourceUrl = SERVER_API_URL + 'api/calendario-instituicaos';

    constructor(private http: HttpClient) {}

    create(calendarioInstituicao: ICalendarioInstituicao): Observable<EntityResponseType> {
        return this.http.post<ICalendarioInstituicao>(this.resourceUrl, calendarioInstituicao, { observe: 'response' });
    }

    update(calendarioInstituicao: ICalendarioInstituicao): Observable<EntityResponseType> {
        return this.http.put<ICalendarioInstituicao>(this.resourceUrl, calendarioInstituicao, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICalendarioInstituicao>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICalendarioInstituicao[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
