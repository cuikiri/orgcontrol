/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { ExameMedicoService } from 'app/entities/exame-medico/exame-medico.service';
import { IExameMedico, ExameMedico } from 'app/shared/model/exame-medico.model';

describe('Service Tests', () => {
    describe('ExameMedico Service', () => {
        let injector: TestBed;
        let service: ExameMedicoService;
        let httpMock: HttpTestingController;
        let elemDefault: IExameMedico;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ExameMedicoService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new ExameMedico(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a ExameMedico', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new ExameMedico(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a ExameMedico', async () => {
                const returnedFromService = Object.assign(
                    {
                        nomeMedico: 'BBBBBB',
                        crmMedico: 'BBBBBB',
                        especialidadeMedico: 'BBBBBB',
                        idadePaciente: 1,
                        obs: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of ExameMedico', async () => {
                const returnedFromService = Object.assign(
                    {
                        nomeMedico: 'BBBBBB',
                        crmMedico: 'BBBBBB',
                        especialidadeMedico: 'BBBBBB',
                        idadePaciente: 1,
                        obs: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
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

            it('should delete a ExameMedico', async () => {
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
