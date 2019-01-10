import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOpcaoRespAtividade } from 'app/shared/model/opcao-resp-atividade.model';

type EntityResponseType = HttpResponse<IOpcaoRespAtividade>;
type EntityArrayResponseType = HttpResponse<IOpcaoRespAtividade[]>;

@Injectable({ providedIn: 'root' })
export class OpcaoRespAtividadeService {
    public resourceUrl = SERVER_API_URL + 'api/opcao-resp-atividades';

    constructor(private http: HttpClient) {}

    create(opcaoRespAtividade: IOpcaoRespAtividade): Observable<EntityResponseType> {
        return this.http.post<IOpcaoRespAtividade>(this.resourceUrl, opcaoRespAtividade, { observe: 'response' });
    }

    update(opcaoRespAtividade: IOpcaoRespAtividade): Observable<EntityResponseType> {
        return this.http.put<IOpcaoRespAtividade>(this.resourceUrl, opcaoRespAtividade, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOpcaoRespAtividade>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOpcaoRespAtividade[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
