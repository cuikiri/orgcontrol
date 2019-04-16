import { Component, OnInit, ElementRef } from '@angular/core';
import { JhiLanguageService, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { Principal, AccountService, JhiLanguageHelper } from 'app/core';

import { IFotoUser, FotoUser } from 'app/shared/model/foto-user.model';
import { IPessoa } from 'app/shared/model/pessoa.model';

@Component({
    selector: 'jhi-settings',
    templateUrl: './settings.component.html'
})
export class SettingsComponent implements OnInit {
    error: string;
    success: string;
    settingsAccount: any;
    languages: any[];
    fotoUser: IFotoUser;
    pessoa: IPessoa;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private elementRef: ElementRef,
        private account: AccountService,
        private principal: Principal,
        private languageService: JhiLanguageService,
        private languageHelper: JhiLanguageHelper
    ) {}

    ngOnInit() {
        this.fotoUser = new FotoUser();
        this.principal.identity().then(account => {
            this.settingsAccount = this.copyAccount(account);
            this.pessoa = this.settingsAccount.pessoa;
            if (this.settingsAccount.foto) {
                this.fotoUser = this.settingsAccount.foto;
            }
        });
        this.languageHelper.getAll().then(languages => {
            this.languages = languages;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.fotoUser, this.elementRef, field, fieldContentType, idInput);
    }

    save() {
        if (this.fotoUser) {
            this.settingsAccount.foto = this.fotoUser;
        }

        this.account.save(this.settingsAccount).subscribe(
            () => {
                this.error = null;
                this.success = 'OK';
                this.principal.identity(true).then(account => {
                    this.settingsAccount = this.copyAccount(account);
                });
                this.languageService.getCurrent().then(current => {
                    if (this.settingsAccount.langKey !== current) {
                        this.languageService.changeLanguage(this.settingsAccount.langKey);
                    }
                });
            },
            () => {
                this.success = null;
                this.error = 'ERROR';
            }
        );
    }

    copyAccount(account) {
        return {
            activated: account.activated,
            email: account.email,
            firstName: account.firstName,
            langKey: account.langKey,
            lastName: account.lastName,
            login: account.login,
            imageUrl: account.imageUrl,
            foto: account.foto,
            pessoa: account.pessoa
        };
    }
}
