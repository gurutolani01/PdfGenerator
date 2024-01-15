import { Component, OnInit } from '@angular/core';
import { UserService } from '../user-service.service';

@Component({
  selector: 'app-search-user',
  templateUrl: './search-user.component.html',
  styleUrl: './search-user.component.css'
})
// Import necessary modules and services
// ...

export class SearchUserComponent implements OnInit {
  username: any;
  userDetails: any;

  constructor(private userService: UserService) {}
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  searchUser() {
    this.userService.searchUser(this.username).subscribe(user => {
      this.userDetails = user;
      console.log(this.userDetails);  // Handle the user details as needed
    });
  }
  downloadUserDetailsPDF() {
    this.userService.downloadUserDetailsPDF(this.username).subscribe(
      data => {
        this.downloadFile(data, `user_details_${this.username}.pdf`);
      },
      error => {
        console.error('Error downloading PDF:', error);
      }
    );
  }

  private downloadFile(data: any, filename: string) {
    const blob = new Blob([data], { type: 'application/pdf' });
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = filename;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }

  // downloadUserDetailsPDF() {
  //   this.userService.downloadUserDetailsPDF(this.username).subscribe(
  //     data => {
  //       this.downloadFile(data, `user_details_${this.username}.pdf`);
  //     },
  //     error => {
  //       console.error('Error downloading PDF:', error);
  //     }
  //   );
  // }
  // for pdf generating in frontend
  // private downloadFile(data: any, filename: string) {
  //   const blob = new Blob([data], { type: 'application/pdf' });
  //   const url = window.URL.createObjectURL(blob);
  //   const link = document.createElement('a');
  //   link.href = url;
  //   link.download = filename;
  //   document.body.appendChild(link);
  //   link.click();
  //   document.body.removeChild(link);
  // }

  // for pdf generating in backend
  // downloadUserDetailsPDF() {
  //   this.userService.downloadUserDetailsPDF(this.username).subscribe(data => {
  //     const blob = new Blob([data], { type: 'application/pdf' });
  //     const link = document.createElement('a');
  //     link.href = window.URL.createObjectURL(blob);
  //     link.download = 'user_details.pdf';
  //     link.click();
  //   });
  // }
}
