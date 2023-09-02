import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  dailyStock,
  StockHistory,
  StockInfo,
  UserAllStock,
  userStock,
  UserStockOrder,
  UserInfo,
  stockDetailInfo
} from '../domain/stock';
import { Observable, catchError, retry, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  getStockNumber(): Observable<number[]> {
    return this.http.get<number[]>('/api/stock/getNumber')
      .pipe(
        retry(3),
        catchError(this.handleError),
      );
  }

  getAllStockInfo(): Observable<StockInfo[]> {
    return this.http.get<StockInfo[]>('/api/stock/get')
      .pipe(
        retry(3),
        catchError(this.handleError),
      );
  }

  getHistory(number:number): Observable<StockHistory[]> {
    return this.http.get<StockHistory[]>(`/api/stock/${number}`)
      .pipe(
        retry(3),
        catchError(this.handleError),
      );
  }

  getUserStock(): Observable<UserAllStock[]> {
    return this.http.get<UserAllStock[]>('/api/user/allDetail')
      .pipe(
        retry(3),
        catchError(this.handleError),
      );
  }

  getUserStockOrderByNumber(number: number): Observable<UserStockOrder[]> {
    return this.http.get<UserStockOrder[]>(`/api/user/detail/${number}`)
      .pipe(
        retry(3),
        catchError(this.handleError),
      );
  }

  getUserAllDetailStock(): Observable<userStock> {
    return this.http.get<userStock>('/api/user/allDetail')
        .pipe(
            retry(3),
            catchError(this.handleError),
        );
  }

  getDetailStock(): Observable<userStock> {
    return this.http.get<stockDetailInfo>(`/api/stock/AllStockInfo`)
      .pipe(
        retry(3),
        catchError(this.handleError),
      );
  }

  getDailyStockByNumber(number: number):Observable<dailyStock[]>{
    return this.http.get<dailyStock[]>(`/api/stock/daily/${number}`)
        .pipe(
            retry(3),
            catchError(this.handleError),
        );
  }

  BuyStockByNumberAndCount(number: number , count : number):Observable<UserStockOrder>{
    return this.http.put<UserStockOrder>(`/api/user/buy/${number}_${count}`,{number :number,count:count})
        .pipe(
            retry(3),
            catchError(this.handleError),
        );
  }

  getUserInfo(account: any): Observable<UserInfo> {
    return this.http.get<UserInfo>(`/api/user/account/${account}`)
      .pipe(
        retry(3),
        catchError(this.handleError),
      );
  }
  sellStock(number: any, account: any): any {
    return this.http.put(`/api/user/sell/${number}_${account}`, { number, account })
      .pipe(
        retry(3),
        catchError(this.handleError),
      );
  }


  private handleError(error: HttpErrorResponse) {
    console.error("An error occurred:", error.error);
    return throwError(() => new Error("Please try again later."));
  }

}
