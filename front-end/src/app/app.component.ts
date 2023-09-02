import { Component } from '@angular/core';
import { ApiService } from './service/api.service';
import { UserInfo } from './domain/stock';
import { StorageService } from './store/storage.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-end';
  isCollapsed = false;

  constructor(private apiService: ApiService, private storage: StorageService) {}

  userInfo: UserInfo = {
    id: 0,
    account: '',
    balance: 0
  };

  ngOnInit(): void {
    // TODO 4 call loadShippers
    this.getUser("yellen");
  }

  getUser(account: any): void {
    this.apiService.getUserInfo(account).subscribe({
      next: (userInfo: UserInfo) => (
        this.userInfo = userInfo,
        this.storage.set("USER_BALANCE", userInfo.balance)
      ),
      error: (error) => console.error('Error occurred ' + error),
    });
  }
}
