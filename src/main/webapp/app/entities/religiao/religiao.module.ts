import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ReligiaoComponent,
    ReligiaoDetailComponent,
    ReligiaoUpdateComponent,
    ReligiaoDeletePopupComponent,
    ReligiaoDeleteDialogComponent,
    religiaoRoute,
    religiaoPopupRoute
} from './';

const ENTITY_STATES = [...religiaoRoute, ...religiaoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ReligiaoComponent,
        ReligiaoDetailComponent,
        ReligiaoUpdateComponent,
        ReligiaoDeleteDialogComponent,
        ReligiaoDeletePopupComponent
    ],
    entryComponents: [ReligiaoComponent, ReligiaoUpdateComponent, ReligiaoDeleteDialogComponent, ReligiaoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolReligiaoModule {}
