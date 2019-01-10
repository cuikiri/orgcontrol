/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { EnderecoService } from 'app/entities/endereco/endereco.service';
import { IEndereco, Endereco, TipoResidencia, EnderecoTipo, TipoLogradouro, TipoBairro, Regiao } from 'app/shared/model/endereco.model';

describe('Service Tests', () => {
    describe('Endereco Service', () => {
        let injector: TestBed;
        let service: EnderecoService;
        let httpMock: HttpTestingController;
        let elemDefault: IEndereco;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(EnderecoService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Endereco(
                0,
                TipoResidencia.CASA,
                EnderecoTipo.RESIDENCIAL,
                TipoLogradouro.RUA,
                'AAAAAAA',
                0,
                'AAAAAAA',
                TipoBairro.RESIDENCIAL,
                Regiao.CENTRAL,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA'
            );
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

            it('should create a Endereco', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Endereco(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Endereco', async () => {
                const returnedFromService = Object.assign(
                    {
                        tipoResidencia: 'BBBBBB',
                        tipoEndereco: 'BBBBBB',
                        tipoLogradouro: 'BBBBBB',
                        nome: 'BBBBBB',
                        numero: 1,
                        bairro: 'BBBBBB',
                        tipoBairoo: 'BBBBBB',
                        zona: 'BBBBBB',
                        cep: 'BBBBBB',
                        bloco: 'BBBBBB',
                        apto: 'BBBBBB',
                        complemento: 'BBBBBB',
                        cidade: 'BBBBBB'
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

            it('should return a list of Endereco', async () => {
                const returnedFromService = Object.assign(
                    {
                        tipoResidencia: 'BBBBBB',
                        tipoEndereco: 'BBBBBB',
                        tipoLogradouro: 'BBBBBB',
                        nome: 'BBBBBB',
                        numero: 1,
                        bairro: 'BBBBBB',
                        tipoBairoo: 'BBBBBB',
                        zona: 'BBBBBB',
                        cep: 'BBBBBB',
                        bloco: 'BBBBBB',
                        apto: 'BBBBBB',
                        complemento: 'BBBBBB',
                        cidade: 'BBBBBB'
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

            it('should delete a Endereco', async () => {
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
