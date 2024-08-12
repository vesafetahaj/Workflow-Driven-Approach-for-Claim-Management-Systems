import { Component } from '@angular/core';
import { ProcessService } from '../services/process.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  formData = {
    name: '',
    email: ''
  };

  constructor(private processService: ProcessService) {}

  submitForm() {
    this.processService.startProcess(this.formData).subscribe(response => {
      alert('Form submitted successfully');
    });
  }
}
