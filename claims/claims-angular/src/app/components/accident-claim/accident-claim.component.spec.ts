import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccidentClaimComponent } from './accident-claim.component';

describe('AccidentClaimComponent', () => {
  let component: AccidentClaimComponent;
  let fixture: ComponentFixture<AccidentClaimComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccidentClaimComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AccidentClaimComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
