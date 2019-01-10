import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOpcaoRespAvalOptativaEconomica } from 'app/shared/model/opcao-resp-aval-optativa-economica.model';

type EntityResponseType = HttpResponse<IOpcaoRespAvalOptativaEconomica>;
type EntityArrayResponseType = HttpResponse<IOpcaoRespAvalOptativaEconomica[]>;

@Injectable({ providedIn: 'root' })
export class OpcaoRespAvalOptativaEconomicaService {
    public resourceUrl = SERVER_API_URL + 'api/opcao-resp-aval-optativa-economicas';

    constructor(private http: HttpClient) {}

    create(opcaoRespAvalOptativaEconomica: IOpcaoRespAvalOptativaEconomica): Observable<EntityResponseType> {
        return this.http.post<IOpcaoRespAvalOptativaEconomica>(this.resourceUrl, opcaoRespAvalOptativaEconomica, { observe: 'response' });
    }

    update(opcaoRespAvalOptativaEconomica: IOpcaoRespAvalOptativaEconomica): Observable<EntityResponseType> {
        return this.http.put<IOpcaoRespAvalOptativaEconomica>(this.resourceUrl, opcaoRespAvalOptativaEconomica, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOpcaoRespAvalOptativaEconomica>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOpcaoRespAvalOptativaEconomica[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
