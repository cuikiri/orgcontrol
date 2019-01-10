import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    CursoComponent,
    CursoDetailComponent,
    CursoUpdateComponent,
    CursoDeletePopupComponent,
    CursoDeleteDialogComponent,
    cursoRoute,
    cursoPopupRoute
} from './';

const ENTITY_STATES = [...cursoRoute, ...cursoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [CursoComponent, CursoDetailComponent, CursoUpdateComponent, CursoDeleteDialogComponent, CursoDeletePopupComponent],
    entryComponents: [CursoComponent, CursoUpdateComponent, CursoDeleteDialogComponent, CursoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolCursoModule {}
