import { NzTableFilterFn, NzTableFilterList, NzTableSortFn, NzTableSortOrder } from "ng-zorro-antd/table";

export interface UserInfo {
    id: number;
    account: string;
    balance: number;
}

export interface StockInfo {
    id: number;
    number: number;
    name: string;
    company:string;
    date: string;
}

export interface ColumnItem {
    name: string;
    sortOrder: NzTableSortOrder | null;
    sortFn: NzTableSortFn<StockInfo> | null;
    listOfFilter: NzTableFilterList;
    filterFn: NzTableFilterFn<StockInfo> | null;
    showFilter: boolean;
    showSelect: boolean;
}

export interface UserAllStock {
    number: number;
    name: string;
    company: string;
    purchaseDate: string;
    currentPrice: number;
    count: number;
    profit: number;
}

export interface UserStockOrder {
    id: number;
    number: number;
    date: string;
    price: number;
    count: number;
    state: number;
    currentPrice: number;
}

export interface UserColumnItem {
    name: string;
    sortOrder: NzTableSortOrder | null;
    sortFn: NzTableSortFn<UserAllStock> | null;
    listOfFilter: NzTableFilterList;
    filterFn: NzTableFilterFn<UserAllStock> | null;
    width: string;
    showFilter: boolean;
    showSelect: boolean;
}

export interface StockHistory {
    // date: Date;
    // openingPrice: number;
    id: number,
    number: number,
    date: string,
    highestPrice: number,
    lowestPrice: number,
    openingPrice: number,
    amplitude: number,
    tradingVolume: number
}

//用来展示用户持有股票信息
export interface userStock{
    [key: number]: detailInfo;
}

export interface detailInfo{
    [key: string]: any;
}

//用来展示购买想要的股票信息（非用户持有）
export interface stockDetailInfo{
  [key: number]: detailInfo;
}

//每日股票信息
export interface dailyStock{
    id : number
    number : number
    date : string
    price : number
}
