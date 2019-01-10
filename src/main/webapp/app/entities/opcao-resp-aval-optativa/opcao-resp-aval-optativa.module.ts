import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    OpcaoRespAvalOptativaComponent,
    OpcaoRespAvalOptativaDetailComponent,
    OpcaoRespAvalOptativaUpdateComponent,
    OpcaoRespAvalOptativaDeletePopupComponent,
    OpcaoRespAvalOptativaDeleteDialogComponent,
    opcaoRespAvalOptativaRoute,
    opcaoRespAvalOptativaPopupRoute
} from './';

const ENTITY_STATES = [...opcaoRespAvalOptativaRoute, ...opcaoRespAvalOptativaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OpcaoRespAvalOptativaComponent,
        OpcaoRespAvalOptativaDetailComponent,
        OpcaoRespAvalOptativaUpdateComponent,
        OpcaoRespAvalOptativaDeleteDialogComponent,
        OpcaoRespAvalOptativaDeletePopupComponent
    ],
    entryComponents: [
        OpcaoRespAvalOptativaComponent,
        OpcaoRespAvalOptativaUpdateComponent,
        OpcaoRespAvalOptativaDeleteDialogComponent,
        OpcaoRespAvalOptativaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolOpcaoRespAvalOptativaModule {}
