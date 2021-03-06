import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    DoencaComponent,
    DoencaDetailComponent,
    DoencaUpdateComponent,
    DoencaDeletePopupComponent,
    DoencaDeleteDialogComponent,
    doencaRoute,
    doencaPopupRoute
} from './';

const ENTITY_STATES = [...doencaRoute, ...doencaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [DoencaComponent, DoencaDetailComponent, DoencaUpdateComponent, DoencaDeleteDialogComponent, DoencaDeletePopupComponent],
    entryComponents: [DoencaComponent, DoencaUpdateComponent, DoencaDeleteDialogComponent, DoencaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolDoencaModule {}
