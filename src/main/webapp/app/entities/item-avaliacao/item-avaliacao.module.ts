import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ItemAvaliacaoComponent,
    ItemAvaliacaoDetailComponent,
    ItemAvaliacaoUpdateComponent,
    ItemAvaliacaoDeletePopupComponent,
    ItemAvaliacaoDeleteDialogComponent,
    itemAvaliacaoRoute,
    itemAvaliacaoPopupRoute
} from './';

const ENTITY_STATES = [...itemAvaliacaoRoute, ...itemAvaliacaoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ItemAvaliacaoComponent,
        ItemAvaliacaoDetailComponent,
        ItemAvaliacaoUpdateComponent,
        ItemAvaliacaoDeleteDialogComponent,
        ItemAvaliacaoDeletePopupComponent
    ],
    entryComponents: [
        ItemAvaliacaoComponent,
        ItemAvaliacaoUpdateComponent,
        ItemAvaliacaoDeleteDialogComponent,
        ItemAvaliacaoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolItemAvaliacaoModule {}
