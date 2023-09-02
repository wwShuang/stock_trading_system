import { Injectable } from '@angular/core' ;
 
@Injectable({
  providedIn: 'root'
})
export class StorageService {
 
  constructor() { }

  set(key: string, value: any): void {
    localStorage.setItem(key, JSON.stringify(value)); // 只能是字符串
  }

  get(key: string): any {
    return JSON.parse(localStorage.getItem(key) || '{}');
  }
  
  remove(key: string): void {
    localStorage.removeItem(key);
  }
  
  getItem(key: string): any {
    return localStorage.getItem(key)?localStorage.getItem(key) || '{}'.replace('"','').replace('"','') :"" ;
  }

  getObj(key: string): any {
    return localStorage.getItem(key)?JSON.parse(localStorage.getItem(key) || '{}'):null;
  }

  clear() {
    localStorage.clear();
  }
}