import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    MateriaComponent,
    MateriaDetailComponent,
    MateriaUpdateComponent,
    MateriaDeletePopupComponent,
    MateriaDeleteDialogComponent,
    materiaRoute,
    materiaPopupRoute
} from './';

const ENTITY_STATES = [...materiaRoute, ...materiaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MateriaComponent,
        MateriaDetailComponent,
        MateriaUpdateComponent,
        MateriaDeleteDialogComponent,
        MateriaDeletePopupComponent
    ],
    entryComponents: [MateriaComponent, MateriaUpdateComponent, MateriaDeleteDialogComponent, MateriaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolMateriaModule {}
