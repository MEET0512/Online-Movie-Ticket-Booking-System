import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InputComponent } from '../component/input/input.component';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    InputComponent
  ],
  templateUrl: './login.component.html',
})
export class LoginComponent {

  registerForm: any = {
    fname: '',
    lname: '',
    email: '',
    pwd:'',
    cpwd:'',
    phone:''
  };

  formData: any = {
    uname: '',
    pwd: ''
  }

  onSubmitRegister() {
    console.log(this.registerForm);
  }

  onSubmitLogin() {
    console.log(this.formData);
  }
}
