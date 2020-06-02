import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../core/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignUpComponent implements OnInit {

  genders = ['Male', 'Female'];
  signupForm: FormGroup;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      firstname: new FormControl('', Validators.required),
      lastname: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      birthday: new FormControl(''),
      gender: new FormControl('Male')
    });
  }

  signUp() {
    console.log(this.signupForm.value.birthday);
    this.authService.signup(this.signupForm.value).subscribe( data => {
      if (data) {
         this.router.navigate(['home']);
      }
    });
  }

}
