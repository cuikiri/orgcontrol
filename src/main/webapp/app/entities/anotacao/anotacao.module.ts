import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    AnotacaoComponent,
    AnotacaoDetailComponent,
    AnotacaoUpdateComponent,
    AnotacaoDeletePopupComponent,
    AnotacaoDeleteDialogComponent,
    anotacaoRoute,
    anotacaoPopupRoute
} from './';

const ENTITY_STATES = [...anotacaoRoute, ...anotacaoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AnotacaoComponent,
        AnotacaoDetailComponent,
        AnotacaoUpdateComponent,
        AnotacaoDeleteDialogComponent,
        AnotacaoDeletePopupComponent
    ],
    entryComponents: [AnotacaoComponent, AnotacaoUpdateComponent, AnotacaoDeleteDialogComponent, AnotacaoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolAnotacaoModule {}
