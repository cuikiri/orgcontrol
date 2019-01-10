import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    HorarioMateriaComponent,
    HorarioMateriaDetailComponent,
    HorarioMateriaUpdateComponent,
    HorarioMateriaDeletePopupComponent,
    HorarioMateriaDeleteDialogComponent,
    horarioMateriaRoute,
    horarioMateriaPopupRoute
} from './';

const ENTITY_STATES = [...horarioMateriaRoute, ...horarioMateriaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        HorarioMateriaComponent,
        HorarioMateriaDetailComponent,
        HorarioMateriaUpdateComponent,
        HorarioMateriaDeleteDialogComponent,
        HorarioMateriaDeletePopupComponent
    ],
    entryComponents: [
        HorarioMateriaComponent,
        HorarioMateriaUpdateComponent,
        HorarioMateriaDeleteDialogComponent,
        HorarioMateriaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolHorarioMateriaModule {}
