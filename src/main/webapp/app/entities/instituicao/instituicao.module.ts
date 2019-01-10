import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    InstituicaoComponent,
    InstituicaoDetailComponent,
    InstituicaoUpdateComponent,
    InstituicaoDeletePopupComponent,
    InstituicaoDeleteDialogComponent,
    instituicaoRoute,
    instituicaoPopupRoute
} from './';

const ENTITY_STATES = [...instituicaoRoute, ...instituicaoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        InstituicaoComponent,
        InstituicaoDetailComponent,
        InstituicaoUpdateComponent,
        InstituicaoDeleteDialogComponent,
        InstituicaoDeletePopupComponent
    ],
    entryComponents: [InstituicaoComponent, InstituicaoUpdateComponent, InstituicaoDeleteDialogComponent, InstituicaoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolInstituicaoModule {}
