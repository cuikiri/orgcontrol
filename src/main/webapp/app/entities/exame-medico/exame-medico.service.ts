import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IExameMedico } from 'app/shared/model/exame-medico.model';

type EntityResponseType = HttpResponse<IExameMedico>;
type EntityArrayResponseType = HttpResponse<IExameMedico[]>;

@Injectable({ providedIn: 'root' })
export class ExameMedicoService {
    public resourceUrl = SERVER_API_URL + 'api/exame-medicos';

    constructor(private http: HttpClient) {}

    create(exameMedico: IExameMedico): Observable<EntityResponseType> {
        return this.http.post<IExameMedico>(this.resourceUrl, exameMedico, { observe: 'response' });
    }

    update(exameMedico: IExameMedico): Observable<EntityResponseType> {
        return this.http.put<IExameMedico>(this.resourceUrl, exameMedico, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IExameMedico>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IExameMedico[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
