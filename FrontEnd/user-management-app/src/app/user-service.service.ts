import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) {}

  addUser(user: any): Observable<string> {
  
    
    return this.http.post<any>(`${this.apiUrl}/add`, user);
  }

  searchUser(username: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/search/${username}`);
  }
  // downloadUserDetailsPDF(username: string): Observable<Blob> {
  //   const options = { responseType: 'arraybuffer' as 'json' };
  //   return this.http.get(`${this.apiUrl}/download-pdf/${username}`, options);
  // }

  
  
  // downloadUserDetailsPDF(username: string): Observable<Blob> {
  //   const options = { responseType: 'arraybuffer' as 'json' };
  //   return this.http.get(`${this.apiUrl}/download-pdf/${username}`, options).pipe(
  //     map((data: any) => new Blob([data], { type: 'application/pdf' }))
  //   );
  // }
  downloadUserDetailsPDF(username:string): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/download-pdf/${username}`, { responseType: 'blob' });
  }
}

