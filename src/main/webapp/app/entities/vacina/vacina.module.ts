import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    VacinaComponent,
    VacinaDetailComponent,
    VacinaUpdateComponent,
    VacinaDeletePopupComponent,
    VacinaDeleteDialogComponent,
    vacinaRoute,
    vacinaPopupRoute
} from './';

const ENTITY_STATES = [...vacinaRoute, ...vacinaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [VacinaComponent, VacinaDetailComponent, VacinaUpdateComponent, VacinaDeleteDialogComponent, VacinaDeletePopupComponent],
    entryComponents: [VacinaComponent, VacinaUpdateComponent, VacinaDeleteDialogComponent, VacinaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolVacinaModule {}
