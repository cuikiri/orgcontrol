import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ModuloComponent,
    ModuloDetailComponent,
    ModuloUpdateComponent,
    ModuloDeletePopupComponent,
    ModuloDeleteDialogComponent,
    moduloRoute,
    moduloPopupRoute
} from './';

const ENTITY_STATES = [...moduloRoute, ...moduloPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ModuloComponent, ModuloDetailComponent, ModuloUpdateComponent, ModuloDeleteDialogComponent, ModuloDeletePopupComponent],
    entryComponents: [ModuloComponent, ModuloUpdateComponent, ModuloDeleteDialogComponent, ModuloDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolModuloModule {}
