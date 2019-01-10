/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ChapaService } from 'app/entities/chapa/chapa.service';
import { IChapa, Chapa } from 'app/shared/model/chapa.model';

describe('Service Tests', () => {
    describe('Chapa Service', () => {
        let injector: TestBed;
        let service: ChapaService;
        let httpMock: HttpTestingController;
        let elemDefault: IChapa;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ChapaService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Chapa(0, 'AAAAAAA', currentDate, 0, false, 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dataCadastro: currentDate.format(DATE_FORMAT)
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

            it('should create a Chapa', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dataCadastro: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dataCadastro: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Chapa(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Chapa', async () => {
                const returnedFromService = Object.assign(
                    {
                        nome: 'BBBBBB',
                        dataCadastro: currentDate.format(DATE_FORMAT),
                        totaVotos: 1,
                        vencedor: true,
                        obs: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dataCadastro: currentDate
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

            it('should return a list of Chapa', async () => {
                const returnedFromService = Object.assign(
                    {
                        nome: 'BBBBBB',
                        dataCadastro: currentDate.format(DATE_FORMAT),
                        totaVotos: 1,
                        vencedor: true,
                        obs: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dataCadastro: currentDate
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

            it('should delete a Chapa', async () => {
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
