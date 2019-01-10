import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOpcaoRespAvalOptativa } from 'app/shared/model/opcao-resp-aval-optativa.model';

type EntityResponseType = HttpResponse<IOpcaoRespAvalOptativa>;
type EntityArrayResponseType = HttpResponse<IOpcaoRespAvalOptativa[]>;

@Injectable({ providedIn: 'root' })
export class OpcaoRespAvalOptativaService {
    public resourceUrl = SERVER_API_URL + 'api/opcao-resp-aval-optativas';

    constructor(private http: HttpClient) {}

    create(opcaoRespAvalOptativa: IOpcaoRespAvalOptativa): Observable<EntityResponseType> {
        return this.http.post<IOpcaoRespAvalOptativa>(this.resourceUrl, opcaoRespAvalOptativa, { observe: 'response' });
    }

    update(opcaoRespAvalOptativa: IOpcaoRespAvalOptativa): Observable<EntityResponseType> {
        return this.http.put<IOpcaoRespAvalOptativa>(this.resourceUrl, opcaoRespAvalOptativa, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOpcaoRespAvalOptativa>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOpcaoRespAvalOptativa[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
