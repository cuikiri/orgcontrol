import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAgendaColaborador } from 'app/shared/model/agenda-colaborador.model';

type EntityResponseType = HttpResponse<IAgendaColaborador>;
type EntityArrayResponseType = HttpResponse<IAgendaColaborador[]>;

@Injectable({ providedIn: 'root' })
export class AgendaColaboradorService {
    public resourceUrl = SERVER_API_URL + 'api/agenda-colaboradors';

    constructor(private http: HttpClient) {}

    create(agendaColaborador: IAgendaColaborador): Observable<EntityResponseType> {
        return this.http.post<IAgendaColaborador>(this.resourceUrl, agendaColaborador, { observe: 'response' });
    }

    update(agendaColaborador: IAgendaColaborador): Observable<EntityResponseType> {
        return this.http.put<IAgendaColaborador>(this.resourceUrl, agendaColaborador, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAgendaColaborador>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAgendaColaborador[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
