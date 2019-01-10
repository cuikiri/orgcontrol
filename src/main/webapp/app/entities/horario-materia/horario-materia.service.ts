import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHorarioMateria } from 'app/shared/model/horario-materia.model';

type EntityResponseType = HttpResponse<IHorarioMateria>;
type EntityArrayResponseType = HttpResponse<IHorarioMateria[]>;

@Injectable({ providedIn: 'root' })
export class HorarioMateriaService {
    public resourceUrl = SERVER_API_URL + 'api/horario-materias';

    constructor(private http: HttpClient) {}

    create(horarioMateria: IHorarioMateria): Observable<EntityResponseType> {
        return this.http.post<IHorarioMateria>(this.resourceUrl, horarioMateria, { observe: 'response' });
    }

    update(horarioMateria: IHorarioMateria): Observable<EntityResponseType> {
        return this.http.put<IHorarioMateria>(this.resourceUrl, horarioMateria, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IHorarioMateria>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IHorarioMateria[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
