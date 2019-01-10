import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    AdvertenciaComponent,
    AdvertenciaDetailComponent,
    AdvertenciaUpdateComponent,
    AdvertenciaDeletePopupComponent,
    AdvertenciaDeleteDialogComponent,
    advertenciaRoute,
    advertenciaPopupRoute
} from './';

const ENTITY_STATES = [...advertenciaRoute, ...advertenciaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AdvertenciaComponent,
        AdvertenciaDetailComponent,
        AdvertenciaUpdateComponent,
        AdvertenciaDeleteDialogComponent,
        AdvertenciaDeletePopupComponent
    ],
    entryComponents: [AdvertenciaComponent, AdvertenciaUpdateComponent, AdvertenciaDeleteDialogComponent, AdvertenciaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolAdvertenciaModule {}
