/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { AdvertenciaService } from 'app/entities/advertencia/advertencia.service';
import { IAdvertencia, Advertencia } from 'app/shared/model/advertencia.model';

describe('Service Tests', () => {
    describe('Advertencia Service', () => {
        let injector: TestBed;
        let service: AdvertenciaService;
        let httpMock: HttpTestingController;
        let elemDefault: IAdvertencia;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(AdvertenciaService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Advertencia(0, 'AAAAAAA', currentDate, currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        data: currentDate.format(DATE_FORMAT),
                        dataAdvertencia: currentDate.format(DATE_FORMAT)
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

            it('should create a Advertencia', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        data: currentDate.format(DATE_FORMAT),
                        dataAdvertencia: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        data: currentDate,
                        dataAdvertencia: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Advertencia(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Advertencia', async () => {
                const returnedFromService = Object.assign(
                    {
                        nome: 'BBBBBB',
                        data: currentDate.format(DATE_FORMAT),
                        dataAdvertencia: currentDate.format(DATE_FORMAT),
                        horaAdvertencia: 'BBBBBB',
                        resumo: 'BBBBBB',
                        obs: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        data: currentDate,
                        dataAdvertencia: currentDate
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

            it('should return a list of Advertencia', async () => {
                const returnedFromService = Object.assign(
                    {
                        nome: 'BBBBBB',
                        data: currentDate.format(DATE_FORMAT),
                        dataAdvertencia: currentDate.format(DATE_FORMAT),
                        horaAdvertencia: 'BBBBBB',
                        resumo: 'BBBBBB',
                        obs: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        data: currentDate,
                        dataAdvertencia: currentDate
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

            it('should delete a Advertencia', async () => {
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
