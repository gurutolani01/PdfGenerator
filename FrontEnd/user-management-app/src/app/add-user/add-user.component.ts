import { Component, OnInit } from '@angular/core';
import { UserService } from '../user-service.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
// Import necessary modules and services
// ...

export class AddUserComponent implements OnInit {
  user: any = {};

  constructor(private userService: UserService) {}
  ngOnInit() {
    
  }

  addUser() {
    this.userService.addUser(this.user).subscribe(
      response => {
        console.log(this.user);  // Log the response for debugging
      },
      error => {
        console.error(error);  // Log the error for debugging
      }
    );
    
    // this.userService.addUser(this.user).subscribe(response => {
    //   console.log(response);  // Handle the response as needed
    // });
  }
}

