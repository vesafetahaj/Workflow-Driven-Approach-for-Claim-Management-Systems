import { TestBed } from '@angular/core/testing';

import { AccidentClaimService } from './accident-claim.service';

describe('AccidentClaimService', () => {
  let service: AccidentClaimService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccidentClaimService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
