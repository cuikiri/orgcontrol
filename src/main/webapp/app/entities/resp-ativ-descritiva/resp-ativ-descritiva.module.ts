import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    RespAtivDescritivaComponent,
    RespAtivDescritivaDetailComponent,
    RespAtivDescritivaUpdateComponent,
    RespAtivDescritivaDeletePopupComponent,
    RespAtivDescritivaDeleteDialogComponent,
    respAtivDescritivaRoute,
    respAtivDescritivaPopupRoute
} from './';

const ENTITY_STATES = [...respAtivDescritivaRoute, ...respAtivDescritivaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RespAtivDescritivaComponent,
        RespAtivDescritivaDetailComponent,
        RespAtivDescritivaUpdateComponent,
        RespAtivDescritivaDeleteDialogComponent,
        RespAtivDescritivaDeletePopupComponent
    ],
    entryComponents: [
        RespAtivDescritivaComponent,
        RespAtivDescritivaUpdateComponent,
        RespAtivDescritivaDeleteDialogComponent,
        RespAtivDescritivaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolRespAtivDescritivaModule {}
