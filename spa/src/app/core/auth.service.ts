import { Injectable } from '@angular/core';
import { mainUrl } from '../../environments/environment';
import { User } from '../shared/models/User';
import { HttpClient } from '@angular/common/http';
import { Credentials } from '../shared/models/Credentials';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  signup(user: User) {
    return this.http.post(mainUrl + 'users', user);
  }

  login(creds: Credentials) {
    return this.http.post(mainUrl + 'login', creds);
  }
}
