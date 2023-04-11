import { TestBed } from '@angular/core/testing';

import { CanCuocService } from './can-cuoc.service';

describe('CanCuocService', () => {
  let service: CanCuocService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CanCuocService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
