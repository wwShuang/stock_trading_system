import { Component, ViewChild } from '@angular/core';
import {
  FormControl,
  FormBuilder,
  FormGroup,
  Validators,
  AbstractControl,
  ValidationErrors,
  ValidatorFn,
  UntypedFormGroup, UntypedFormBuilder
} from '@angular/forms'
import {
  ColumnItem,
  StockInfo,
  StockHistory,
  dailyStock,
  UserStockOrder,
  UserAllStock,
  userStock,
  stockDetailInfo
} from '../domain/stock'
import { ApiService } from '../service/api.service';
import {DatePipe} from "@angular/common";
import { ChangeDetectorRef } from '@angular/core';
import { NzDatePickerComponent } from 'ng-zorro-antd/date-picker';



@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})
export class StockComponent {
  chart: any;
  dataPoints: any = [];
  TradeCountControl: FormControl;

  showPagination = false;

  searchName: string = '';

  showSelectBox = false;

  showFitherBox = false;

  showDateBox = false;

  searchValue = '';

  visible = false;

  minValue: number  = 0;
  maxValue: number = 0;

  stockNumber: number[] = [];

  numberList: any[] = [ ];

  constructor(
    private fb: UntypedFormBuilder,
    private apiService: ApiService,
    private datePipe: DatePipe,
    private cdRef: ChangeDetectorRef
  ) {
    this.TradeCountControl = new FormControl('', [Validators.required, Validators.min(1), Validators.pattern(/^-?[0-9]+$/)])
  }
  @ViewChild('endDatePicker') endDatePicker!: NzDatePickerComponent;

  validateForm!: FormGroup;
  stockLoading = false;
  number: number = 0;
  isVisible = false;
  userStockLoading = true;

  showModal(number: number): void {
    this.isVisible = true;
  }

  handleOk(): void {
    this.isVisible = true;
  }

  handleCancel(): void {
    this.stockLoading = false;
    this.isVisible = false;
  }

  handleTradeCancle(): void {
    this.isTradeModalOpen = false;
  }

  showSearchModal(data: any) {
    this.searchValue = '';
    this.searchName = data;
    this.showSelectBox = true;
    this.showDateBox = false;
    this.showFitherBox = false;
  }

  showFitherModal(data: any) {
    this.searchName = data;
    this.minValue = 0;
    this.maxValue = 0;
    if(data != "Release Date")
    {
      this.showDateBox = false;
      this.showFitherBox = true;
      this.showSelectBox = false;
    }
    else{
      this.showFitherBox = false;
      this.showSelectBox = false;
      this.showDateBox = true;
    }
  }

  listOfColumns: ColumnItem[] = [
    {
      name: 'Number',
      sortOrder: null,
      sortFn: (a: StockInfo, b: StockInfo) => a.number - b.number,
      listOfFilter: [
        {text: '10001', value: 10001}],
      filterFn: (list: number[], item: StockInfo) => list.some(number => item.number == number),
      showFilter: true,
      showSelect: false
    },
    {
      name: 'Name',
      sortOrder: null,
      sortFn: (a: StockInfo, b: StockInfo) => a.name.localeCompare(b.name),
      listOfFilter: [],
      filterFn: (list: string[], item: StockInfo) => list.some(name => item.name.indexOf(name) !== -1),
      showFilter: false,
      showSelect: true
    },
    {
      name: 'Company',
      sortOrder: null,
      sortFn: (a: StockInfo, b: StockInfo) => a.company.localeCompare(b.company),
      listOfFilter: [],
      filterFn: null,
      showFilter: false,
      showSelect: true
    },
    {
      name: 'Release Date',
      sortOrder: null,
      sortFn: (a: StockInfo, b: StockInfo) => a.date.localeCompare(b.date),
      listOfFilter: [],
      filterFn: null,
      showFilter: false,
      showSelect: true
    },
  ];

  listOfDataInfo: StockInfo[] = [];

  listOfDisplayData = [...this.listOfDataInfo];

  trackByName(_: number, item: ColumnItem): string {
    return item.name;
  }

  reset(): void {
    this.searchValue = '';
    this.search();
  }

  search(): void {
    this.visible = false;
    this.showSelectBox = false;
    this.showDateBox = false;
    this.showFitherBox = false;
    if(this.searchName == "Name") {
      this.listOfDisplayData = this.listOfDataInfo.filter((item: StockInfo) => item.name.indexOf(this.searchValue) !== -1);
    }
    else
      this.listOfDisplayData = this.listOfDataInfo.filter((item: StockInfo) => item.company.indexOf(this.searchValue) !== -1);
    this.userStockLoading = false;
  }

  resetFilter(): void {
    this.maxValue = 0;
    this.minValue = 0;
    this.searchValue = '';
    this.search();
  }

  filterValve(): void {
    this.visible = false;
    this.showFitherBox = false;
    this.showDateBox = false;
    this.listOfDisplayData = [...this.listOfDataInfo];
    this.listOfDisplayData = this.listOfDataInfo.filter((item: StockInfo) => new Date(item.date) >= new Date(this.minValue) && new Date(item.date) <= new Date(this.maxValue));
  }

  resetFilters(): void {
    this.searchValue = '';
    this.userStockLoading = true;
    this.search();
  }

  resetSortAndFilters(): void {
    this.listOfColumns.forEach(item => {
      item.sortOrder = null;
    });
    this.resetFilters();
  }

  ngOnInit(): void {
    this.getStockInfo();
    this.getUserStockAll();
    this.getStockAll();
    this.getStockInfo1();
    this.getStockNumber();
    this.numberList = this.getStockNumber();
  }

  getStockInfo(): void {
    this.apiService.getAllStockInfo().subscribe({
      next: (stockInfo: StockInfo[]) => (this.listOfDataInfo = stockInfo),
      error: (error) => console.error('Error occurred ' + error),
    });
  }

  getStockInfo1(): void {
    this.apiService.getAllStockInfo().subscribe({
      next: (stockInfo: StockInfo[]) => (
        this.listOfDisplayData = stockInfo,
        this.userStockLoading = false
        ),
      error: (error) => console.error('Error occurred ' + error),
    });
  }

  getStockNumber(): any {
    this.numberList.length = 0;
    this.apiService.getStockNumber().subscribe(data => {
      for(let i=0; i<data.length; i++) {
        this.numberList.push({text: data[i],value:  data[i]});
      }
      this.listOfColumns.forEach(item => {
        if (item.name === 'Number') {
          item.listOfFilter.length = 0;
          for(let i=0; i<data.length; i++) {
            item.listOfFilter.push({text: data[i] + '',value:  data[i]});
          }
        }
        console.log(item.listOfFilter)
      });
    });
    return this.numberList
  }

  // dispose of history data
  dataHistoryStockPoint: StockHistory[] = [];

  disposeHistoryData(param: number): void {
    this.isVisible = true;
    this.apiService.getHistory(param).subscribe({
      next: (dataHistoryStock: StockHistory[]) => (
        this.dataHistoryStockPoint = dataHistoryStock,
          this.number = param,
          this.setHistoryData()
      ),
      error: (error) => console.error('Error occurred ' + error),
    });
  }

  // chart of history data
  setHistoryData(): any {
    this.dataPoints.length = 0;
    const newDataPoints = this.dataHistoryStockPoint
      .filter((_, index) => index % 14 === 0)  // set intervals
      .map(stockPoint => ({
        x: new Date(stockPoint.date),
        y: stockPoint.openingPrice
      }));

    for (let i = 0; i < newDataPoints.length; i++) {
      this.dataPoints.push({x: new Date(newDataPoints[i].x), y: Number(newDataPoints[i].y)});
    }
    this.stockLoading = true;
  }

  chartOptions = {
    theme: "light2",
    animationEnabled: true,
    zoomEnabled: true,
    title: {
      text: "History Data"
    },
    axisY: {
      labelFormatter: (e: any) => {
        var suffixes = ["", "K", "M", "B", "T"];

        var order = Math.max(Math.floor(Math.log(e.value) / Math.log(1000)), 0);
        if (order > suffixes.length - 1)
          order = suffixes.length - 1;

        var suffix = suffixes[order];
        return "$" + (e.value / Math.pow(1000, order)) + suffix;
      }
    },
    data: [{
      type: "line",
      xValueFormatString: "YYYY-MM-DD",
      yValueFormatString: "$#,###.##",

      dataPoints: this.dataPoints,
    }]
  }


  /*
   实现当日股票折线图
   */

  isDailyChartVisible: boolean = false
  ds: dailyStock[] = [];
  us: userStock = {};
  stockNames: string[] = [];
  stockNumbers: number[] = [];
  dailyChartData: any = [];
  selectedStock: string = "";

  formatDate(date: string | number | Date) {
    return this.datePipe.transform(date, 'HH:mm:ss','UTC');
  }


  getDailyStockByNumber(number: number, name: string): void {
    this.apiService.getDailyStockByNumber(number).subscribe(
      data => {
        this.isDailyChartVisible = false;
        this.ds = data;
        this.getDailyChartData(name);
      });
  }

  getUserStockAll(): void {
    this.apiService.getUserAllDetailStock().subscribe(
      data => {
        this.us = data;
        for (const key in data) {
          this.stockNumbers.push(+key)
          this.stockNames.push(data[key]['name'])
        }
        if (this.stockNames.length > 0) {
          this.selectedStock = this.stockNames[0]
          this.getDailyStockByNumber(this.stockNumbers[0], this.selectedStock)
        }
      });
  }


  onStockChange(event: any) {
    const name: string = event.target.value
    var i = this.stockNames.findIndex(str => str == name)
    this.getDailyStockByNumber(this.stockNumbers[i], this.stockNames[i])

  }


  // 股票实时图数据形式 ： { x: new Date(), y: 68650476424.8 }
  getDailyChartData(name: string): void {
    this.dailyChartData = [];
    for (let stock of this.ds) {
      const s = {
        y: stock.price,
        label: this.formatDate(stock.date)
      };
      this.dailyChartData.push(s)
    }
    this.daiyChartOptions.data[0].dataPoints = this.dailyChartData
    this.daiyChartOptions.data[0].name = name
    this.cdRef.detectChanges();
    this.isDailyChartVisible = true
  }

  daiyChartOptions = {
    animationEnabled: true,
    title: {
      text: "Stock Chart"
    },
    axisX: {
      title: "Price"
    },
    axisY: {
      title: "Time"
    },
    toolTip: {
      shared: true
    },
    legend: {
      cursor: "pointer",
    },
    data: [{
      type: "spline",
      showInLegend: true,
      name: "",
      dataPoints: []
    }
    ]
  }

  /*
  实现股票买入操作
   */

  isTradeModalOpen = false;
  order: any;
  stocks: stockDetailInfo = {};
  balance: number = 0;
  buyForm!: UntypedFormGroup;
  maxCount : number = 0;
  showMessage: boolean = false;


  getStockAll(): void {
    this.apiService.getDetailStock().subscribe(
      data => {
        this.stocks=data;
      });
  }

  closeMessage(): void {
    this.showMessage = false;
    window.location.reload();
  }

  // TODO:增加购买成功 弹出提示 / 及时刷新页面
  openTradeModal(): void {
    this.apiService.getUserInfo('yellen').subscribe(data => {
      this.balance = data.balance
      this.maxCount  = Math.floor(this.balance / this.stocks[this.number]['currentPrice'])
      console.log("最大值：",this.maxCount)
      this.buyForm = this.fb.group({
        number: [this.stocks[this.number]['number'], [Validators.required]],
        name: [this.stocks[this.number]['name'], [Validators.required]],
        price: [this.stocks[this.number]['currentPrice'],[Validators.required]],
        buyCount: [null, [Validators.required, Validators.pattern("^[0-9]*$"), Validators.max(this.maxCount),Validators.min(1)]]
      });
      this.isTradeModalOpen = true;
    });


  }

  closeModal(): void {
    this.isTradeModalOpen = false;
  }

  buyStock(number: number): void {
    if (this.buyForm.valid){
      console.log('购买数量：', this.buyForm.value.buyCount);
      this.closeModal();
      this.apiService.BuyStockByNumberAndCount(number, this.buyForm.value.buyCount).subscribe(data => {
        this.order = data;
        this.showMessage = true;
        this.isTradeModalOpen = false;
        this.isVisible = false;

      });
    }
    else {
      Object.values(this.buyForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }



startValue: Date | null = null;
  endValue: Date | null = null;

  disabledStartDate = (startValue: Date): boolean => {
    if (!startValue || !this.endValue) {
      return false;
    }
    return startValue.getTime() > this.endValue.getTime();
  };

  disabledEndDate = (endValue: Date): boolean => {
    if (!endValue || !this.startValue) {
      return false;
    }
    return endValue.getTime() <= this.startValue.getTime();
  };

  handleStartOpenChange(open: boolean): void {
    if (!open) {
      this.endDatePicker.open();
    }
    console.log('handleStartOpenChange', open);
  }



}
