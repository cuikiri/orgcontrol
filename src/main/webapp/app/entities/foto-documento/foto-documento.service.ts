import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFotoDocumento } from 'app/shared/model/foto-documento.model';

type EntityResponseType = HttpResponse<IFotoDocumento>;
type EntityArrayResponseType = HttpResponse<IFotoDocumento[]>;

@Injectable({ providedIn: 'root' })
export class FotoDocumentoService {
    public resourceUrl = SERVER_API_URL + 'api/foto-documentos';

    constructor(private http: HttpClient) {}

    create(fotoDocumento: IFotoDocumento): Observable<EntityResponseType> {
        return this.http.post<IFotoDocumento>(this.resourceUrl, fotoDocumento, { observe: 'response' });
    }

    update(fotoDocumento: IFotoDocumento): Observable<EntityResponseType> {
        return this.http.put<IFotoDocumento>(this.resourceUrl, fotoDocumento, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFotoDocumento>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFotoDocumento[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    queryByDocumentoId(req?: any, id?: number): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFotoDocumento[]>(`${this.resourceUrl}/documento/${id}`, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
