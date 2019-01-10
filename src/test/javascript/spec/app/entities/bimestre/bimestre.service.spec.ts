/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { BimestreService } from 'app/entities/bimestre/bimestre.service';
import { IBimestre, Bimestre } from 'app/shared/model/bimestre.model';

describe('Service Tests', () => {
    describe('Bimestre Service', () => {
        let injector: TestBed;
        let service: BimestreService;
        let httpMock: HttpTestingController;
        let elemDefault: IBimestre;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(BimestreService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Bimestre(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, currentDate, 0, 0, 0, 0);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dataInicio: currentDate.format(DATE_FORMAT),
                        dataFim: currentDate.format(DATE_FORMAT)
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

            it('should create a Bimestre', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dataInicio: currentDate.format(DATE_FORMAT),
                        dataFim: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dataInicio: currentDate,
                        dataFim: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Bimestre(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Bimestre', async () => {
                const returnedFromService = Object.assign(
                    {
                        abreviatura: 'BBBBBB',
                        nome: 'BBBBBB',
                        componente: 'BBBBBB',
                        dataInicio: currentDate.format(DATE_FORMAT),
                        dataFim: currentDate.format(DATE_FORMAT),
                        numero: 1,
                        atividadesPrevistas: 1,
                        atividadesDadas: 1,
                        atividadesRepostas: 1
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dataInicio: currentDate,
                        dataFim: currentDate
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

            it('should return a list of Bimestre', async () => {
                const returnedFromService = Object.assign(
                    {
                        abreviatura: 'BBBBBB',
                        nome: 'BBBBBB',
                        componente: 'BBBBBB',
                        dataInicio: currentDate.format(DATE_FORMAT),
                        dataFim: currentDate.format(DATE_FORMAT),
                        numero: 1,
                        atividadesPrevistas: 1,
                        atividadesDadas: 1,
                        atividadesRepostas: 1
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dataInicio: currentDate,
                        dataFim: currentDate
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

            it('should delete a Bimestre', async () => {
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
