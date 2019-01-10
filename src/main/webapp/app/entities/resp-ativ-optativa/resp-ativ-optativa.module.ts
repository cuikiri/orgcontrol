import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    RespAtivOptativaComponent,
    RespAtivOptativaDetailComponent,
    RespAtivOptativaUpdateComponent,
    RespAtivOptativaDeletePopupComponent,
    RespAtivOptativaDeleteDialogComponent,
    respAtivOptativaRoute,
    respAtivOptativaPopupRoute
} from './';

const ENTITY_STATES = [...respAtivOptativaRoute, ...respAtivOptativaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RespAtivOptativaComponent,
        RespAtivOptativaDetailComponent,
        RespAtivOptativaUpdateComponent,
        RespAtivOptativaDeleteDialogComponent,
        RespAtivOptativaDeletePopupComponent
    ],
    entryComponents: [
        RespAtivOptativaComponent,
        RespAtivOptativaUpdateComponent,
        RespAtivOptativaDeleteDialogComponent,
        RespAtivOptativaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolRespAtivOptativaModule {}
