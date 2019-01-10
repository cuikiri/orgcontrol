import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    RespAvalDescritivaComponent,
    RespAvalDescritivaDetailComponent,
    RespAvalDescritivaUpdateComponent,
    RespAvalDescritivaDeletePopupComponent,
    RespAvalDescritivaDeleteDialogComponent,
    respAvalDescritivaRoute,
    respAvalDescritivaPopupRoute
} from './';

const ENTITY_STATES = [...respAvalDescritivaRoute, ...respAvalDescritivaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RespAvalDescritivaComponent,
        RespAvalDescritivaDetailComponent,
        RespAvalDescritivaUpdateComponent,
        RespAvalDescritivaDeleteDialogComponent,
        RespAvalDescritivaDeletePopupComponent
    ],
    entryComponents: [
        RespAvalDescritivaComponent,
        RespAvalDescritivaUpdateComponent,
        RespAvalDescritivaDeleteDialogComponent,
        RespAvalDescritivaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolRespAvalDescritivaModule {}
