import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    DependenteLegalComponent,
    DependenteLegalDetailComponent,
    DependenteLegalUpdateComponent,
    DependenteLegalDeletePopupComponent,
    DependenteLegalDeleteDialogComponent,
    dependenteLegalRoute,
    dependenteLegalPopupRoute
} from './';

const ENTITY_STATES = [...dependenteLegalRoute, ...dependenteLegalPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DependenteLegalComponent,
        DependenteLegalDetailComponent,
        DependenteLegalUpdateComponent,
        DependenteLegalDeleteDialogComponent,
        DependenteLegalDeletePopupComponent
    ],
    entryComponents: [
        DependenteLegalComponent,
        DependenteLegalUpdateComponent,
        DependenteLegalDeleteDialogComponent,
        DependenteLegalDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolDependenteLegalModule {}
