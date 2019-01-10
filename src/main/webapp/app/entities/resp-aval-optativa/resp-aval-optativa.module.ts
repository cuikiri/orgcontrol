import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    RespAvalOptativaComponent,
    RespAvalOptativaDetailComponent,
    RespAvalOptativaUpdateComponent,
    RespAvalOptativaDeletePopupComponent,
    RespAvalOptativaDeleteDialogComponent,
    respAvalOptativaRoute,
    respAvalOptativaPopupRoute
} from './';

const ENTITY_STATES = [...respAvalOptativaRoute, ...respAvalOptativaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RespAvalOptativaComponent,
        RespAvalOptativaDetailComponent,
        RespAvalOptativaUpdateComponent,
        RespAvalOptativaDeleteDialogComponent,
        RespAvalOptativaDeletePopupComponent
    ],
    entryComponents: [
        RespAvalOptativaComponent,
        RespAvalOptativaUpdateComponent,
        RespAvalOptativaDeleteDialogComponent,
        RespAvalOptativaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolRespAvalOptativaModule {}
