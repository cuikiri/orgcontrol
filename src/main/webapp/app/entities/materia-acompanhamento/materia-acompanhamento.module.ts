import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    MateriaAcompanhamentoComponent,
    MateriaAcompanhamentoDetailComponent,
    MateriaAcompanhamentoUpdateComponent,
    MateriaAcompanhamentoDeletePopupComponent,
    MateriaAcompanhamentoDeleteDialogComponent,
    materiaAcompanhamentoRoute,
    materiaAcompanhamentoPopupRoute
} from './';

const ENTITY_STATES = [...materiaAcompanhamentoRoute, ...materiaAcompanhamentoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MateriaAcompanhamentoComponent,
        MateriaAcompanhamentoDetailComponent,
        MateriaAcompanhamentoUpdateComponent,
        MateriaAcompanhamentoDeleteDialogComponent,
        MateriaAcompanhamentoDeletePopupComponent
    ],
    entryComponents: [
        MateriaAcompanhamentoComponent,
        MateriaAcompanhamentoUpdateComponent,
        MateriaAcompanhamentoDeleteDialogComponent,
        MateriaAcompanhamentoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolMateriaAcompanhamentoModule {}
