/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ColaboradorService } from 'app/entities/colaborador/colaborador.service';
import { IColaborador, Colaborador } from 'app/shared/model/colaborador.model';

describe('Service Tests', () => {
    describe('Colaborador Service', () => {
        let injector: TestBed;
        let service: ColaboradorService;
        let httpMock: HttpTestingController;
        let elemDefault: IColaborador;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ColaboradorService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Colaborador(0, currentDate, currentDate, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dataCadastro: currentDate.format(DATE_FORMAT),
                        dataAdmissao: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Colaborador', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dataCadastro: currentDate.format(DATE_FORMAT),
                        dataAdmissao: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dataCadastro: currentDate,
                        dataAdmissao: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Colaborador(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Colaborador', async () => {
                const returnedFromService = Object.assign(
                    {
                        dataCadastro: currentDate.format(DATE_FORMAT),
                        dataAdmissao: currentDate.format(DATE_FORMAT),
                        salario: 1,
                        pai: 'BBBBBB',
                        mae: 'BBBBBB',
                        conjuge: 'BBBBBB',
                        obs: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dataCadastro: currentDate,
                        dataAdmissao: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Colaborador', async () => {
                const returnedFromService = Object.assign(
                    {
                        dataCadastro: currentDate.format(DATE_FORMAT),
                        dataAdmissao: currentDate.format(DATE_FORMAT),
                        salario: 1,
                        pai: 'BBBBBB',
                        mae: 'BBBBBB',
                        conjuge: 'BBBBBB',
                        obs: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dataCadastro: currentDate,
                        dataAdmissao: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Colaborador', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
