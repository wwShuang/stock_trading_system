import {ChangeDetectorRef, Component, ViewChild} from '@angular/core';
import {UserColumnItem, UserAllStock, UserStockOrder, userStock} from '../domain/stock'
import { ApiService } from '../service/api.service';
import { StorageService } from '../store/storage.service'
import { DatePipe } from '@angular/common';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from "@angular/forms";
import { NzDatePickerComponent } from 'ng-zorro-antd/date-picker';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})

export class UserComponent {

  constructor(private apiService: ApiService, private storage: StorageService,
              private datePipe: DatePipe, private fb: UntypedFormBuilder,private cdRef: ChangeDetectorRef) {}
  @ViewChild('endDatePicker') endDatePicker!: NzDatePickerComponent;
  isVisible = false;
  isTableVisible = true;
  userStockLoading = true;
  currentPrice: any;
  isSellVisible = false;
  totalCount: any;
  sellForm!: UntypedFormGroup;

  showPagination = false;

  modalLoading = true;

  visible = false;

  searchValue = '';

  showSelectBox = false;

  showFitherBox = false;

  showDateBox = false;

  minValue: number  = 0;
  maxValue: number = 0;

  stockNumber: number[] = [];

  searchName: string = '';

  numberList: any[] = [ ];


  // 日期格式转换
  formatDate(date: string | number | Date) {
    return this.datePipe.transform(date, 'yyyy-MM-dd HH:mm:SS');
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
    if(data != "PurchaseDate")
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

  showModal(data: UserAllStock): void  {
    this.isVisible = true;
    this.isTableVisible = true;
    this.currentPrice = data.currentPrice;
    this.totalCount = data.count;
    this.storage.set("STOCK_NUMBER", data.number);
    this.getUserStockOrderByNumber(data.number);
  }


  switchTable(): void {
    this.isTableVisible = !this.isTableVisible;
    if(!this.isTableVisible) {
      this.ngAfterViewInit();
    }
  }


  handleOk(): void {
    // this.storage.remove("STOCK_NUMBER");
    this.isVisible = false;
    this.isSellVisible = true;
    this.sellForm = this.fb.group({
      totalCount: [this.totalCount, [Validators.required]],
      currentPrice: [this.currentPrice, [Validators.required]],
      sellCount: [null, [Validators.required, Validators.pattern("^[0-9]*$"), Validators.max(this.totalCount)]],
      remember: [true]
    });
  }

  handleCancel(): void {
    this.currentPrice = 0;
    this.totalCount = 0;
    this.storage.remove("STOCK_NUMBER");
    this.isVisible = false;
  }

  handleSellCancel(): void {
    this.isSellVisible = false;
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
  listOfColumns: UserColumnItem[] = [
    {
      name: 'Number',
      sortOrder: null,
      sortFn: (a: UserAllStock, b: UserAllStock) =>  a.number - b.number,
      listOfFilter: [
        {text: '10001', value: 10001}],
      filterFn: (list: number[], item: UserAllStock) => list.some(number => item.number == number),
      width: '120px',
      showFilter: true,
      showSelect: false
    },
    {
      name: 'Name',
      sortOrder: null,
      sortFn: (a: UserAllStock, b: UserAllStock) => a.name.localeCompare(b.name),
      listOfFilter: [],
      filterFn: null,
      width: '120px',
      showFilter: false,
      showSelect: true
    },
    {
      name: 'Company',
      sortFn:  (a: UserAllStock, b: UserAllStock) => a.company.localeCompare(b.company),
      sortOrder: null,
      listOfFilter: [ ],
      filterFn: null,
      width: '130px',
      showFilter: false,
      showSelect: true
    },
    {
      name: 'PurchaseDate',
      sortFn: (a: UserAllStock, b: UserAllStock) => a.purchaseDate.localeCompare(b.purchaseDate),
      sortOrder: null,
      listOfFilter: [ ],
      filterFn: (purchaseDate: string, item: UserAllStock) => item.purchaseDate.indexOf(purchaseDate) !== -1,
      width: '150px',
      showFilter: false,
      showSelect: true
    },
    {
      name: 'CurrentPrice',
      sortFn: (a: UserAllStock, b: UserAllStock) =>  a.currentPrice - b.currentPrice,
      sortOrder: null,
      listOfFilter: [ ],
      filterFn: null,
      width: '140px',
      showFilter: false,
      showSelect: true
    },
    {
      name: 'Count',
      sortFn: (a: UserAllStock, b: UserAllStock) => a.count - b.count,
      sortOrder: null,
      listOfFilter: [ ],
      filterFn: null,
      width: '120px',
      showFilter: false,
      showSelect: true
    },
    {
      name: 'Profit',
      sortFn: (a: UserAllStock, b: UserAllStock) => a.profit - b.profit,
      sortOrder: null,
      listOfFilter: [ ],
      filterFn: null,
      width: '120px',
      showFilter: false,
      showSelect: true
    }
  ];

  listOfData: UserAllStock[] = [ ];
  userStockDetail: UserStockOrder[] = [];

  listOfDisplayData = [...this.listOfData];

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
      this.listOfDisplayData = this.listOfData.filter((item: UserAllStock) => item.name.indexOf(this.searchValue) !== -1);
    }
    else
      this.listOfDisplayData = this.listOfData.filter((item: UserAllStock) => item.company.indexOf(this.searchValue) !== -1);
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
    this.listOfDisplayData = [...this.listOfData];
    if(this.searchName == "PurchaseDate") {
      this.listOfDisplayData = this.listOfData.filter((item: UserAllStock) => new Date(item.purchaseDate) >= new Date(this.minValue) && new Date(item.purchaseDate) <= new Date(this.maxValue));
    }
    if(this.searchName == "CurrentPrice"){
      this.listOfDisplayData = this.listOfData.filter((item: UserAllStock) => item.currentPrice >= this.minValue && item.currentPrice <= this.maxValue);
    }
    if(this.searchName == "Count")
      this.listOfDisplayData = this.listOfData.filter((item: UserAllStock) => item.count >= this.minValue && item.count <= this.maxValue);
    if(this.searchName == "Profit")
      this.listOfDisplayData = this.listOfData.filter((item: UserAllStock) => item.profit >= this.minValue && item.profit <= this.maxValue);
  }

  trackByName(_: number, item: UserColumnItem): string {
    return item.name;
  }

  resetFilters(): void {
    this.listOfColumns.forEach(item => {
      if (item.name === 'Number') {
        item.listOfFilter = this.numberList;
      }
    });
    this.searchValue = '';
    this.search();
  }

  resetSortAndFilters(): void {
    this.listOfColumns.forEach(item => {
      item.sortOrder = null;
    });
    this.resetFilters();
  }

  ngOnInit(): void {
    this.getUserStock();
    this.getUserStockDetail();
    this.getUserStock1();
    this.getStockNumber();
  }

  getUserStock1(): void {
    this.apiService.getUserStock().subscribe({
      next: (userAllStock: UserAllStock[]) => (
          this.listOfDisplayData = Object.entries(userAllStock).map(([id, stock]) => ({  ...stock }),
        )
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
  }

  showMessage: boolean = false;

  sellStock(number: any, count: number): void {
    this.apiService.sellStock(number, count).subscribe({
      next: () => (
        this.showMessage = true
      ),
      error: (error: string) => console.error('Error occurred ' + error),
    });
  }

  closeMessage(): void {
    // this.isSellVisible = false;
    // this.getUserStock();
    // this.cdRef.detectChanges();
    this.showMessage = false;
    window.location.reload();

  }


  getUserStockOrderByNumber(number:number): void {
    this.apiService.getUserStockOrderByNumber(number).subscribe({
      next: (userAllStock: UserStockOrder[]) => (
          this.userStockDetail = Object.entries(userAllStock).map(([id, stock]) => ({  ...stock }))
      ),
      error: (error) => console.error('Error occurred ' + error),
    });
  }

  getUserStock(): void {
    this.apiService.getUserStock().subscribe({
      next: (userAllStock: UserAllStock[]) => (
        this.userStockLoading = false,
        this.listOfData = Object.entries(userAllStock).map(([id, stock]) => ({  ...stock }))
      ),
      error: (error) => console.error('Error occurred ' + error),
    });
  }

  dataPoints: any = [];
  dataPoints1: any = [];
  dataPoints2: any = [];
  dataPoints3: any = [];
  dataPoints4: any = [];
  chart: any;

  // 用户某只股票折线图
  UserStockChartOptions = {
		theme: "light2",
    zoomEnabled: true,
    exportEnabled: true,
    animationEnabled: true,
    // title: {
    //   text:"Bitcoin Closing Price"
    // },
    subtitles: [{
      fontSize: 24,
      horizontalAlign: "center",
      verticalAlign: "center",
      dockInsidePlotArea: true
    }],
    toolTip:{
			shared:true
		},
    legend:{
			cursor: "pointer",
			verticalAlign: "bottom",
			horizontalAlign: "right",
			dockInsidePlotArea: true,
			itemclick: function(e: any) {
				if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
					e.dataSeries.visible = false;
				} else{
					e.dataSeries.visible = true;
				}
				e.chart.render();
			}
		},
    axisX:{
            valueFormatString: "YYYY MMM DD HH:MM:ss",
			crosshair: {
				enabled: true,
				snapToDataPoint: true
			}
		},
		axisY: {
      valueFormatString: "#,###.00",
			crosshair: {
				enabled: true
			}
		},
      data: [{
        type: "line",
        name: "Sell Price",
        showInLegend: true,
        yValueFormatString: "$#,###.00",
        xValueType: "dateTime",
        dataPoints: this.dataPoints
      },{
        type: "line",
        name: "Buy Price",
        showInLegend: true,
        yValueFormatString: "$#,###.00",
        xValueType: "dateTime",
        dataPoints: this.dataPoints3
      },{
        type: "line",
        name: "Sell Count",showInLegend: true,
        yValueFormatString: "#,###",
        xValueType: "dateTime",
        dataPoints: this.dataPoints1
      },{
        type: "line",
        name: "Buy Count",
        showInLegend: true,
        yValueFormatString: "#,###",
        xValueType: "dateTime",
        dataPoints: this.dataPoints4
      },
        {
          type: "line",
          name: "Total Price",
          showInLegend: true,
          yValueFormatString: "$#,###.00",
          xValueType: "dateTime",
          dataPoints: this.dataPoints2
        }]
  }

  getChartInstance(chart: object) {
    this.chart = chart;
  }

  ngAfterViewInit() {
    this.dataPoints.length = 0;
    this.dataPoints1.length = 0;
    this.dataPoints2.length = 0;
    this.dataPoints3.length = 0;
    this.dataPoints4.length = 0;
    for(let i = 0; i < this.userStockDetail.length; i++) {
      this.dataPoints2.push({x: new Date(this.formatDate(this.userStockDetail[i].date) || ''), y: Number(this.userStockDetail[i].currentPrice) });
      if(this.userStockDetail[i].state == 1) {
        this.dataPoints.push({x: new Date(this.formatDate(this.userStockDetail[i].date) || ''), y: Number(this.userStockDetail[i].price) });
        this.dataPoints1.push({x: new Date(this.formatDate(this.userStockDetail[i].date) || ''), y: Number(this.userStockDetail[i].count)});
      }
      else{
        this.dataPoints3.push({x: new Date(this.formatDate(this.userStockDetail[i].date) || ''), y: Number(this.userStockDetail[i].price) });
        this.dataPoints4.push({x: new Date(this.formatDate(this.userStockDetail[i].date) || ''), y: Number(this.userStockDetail[i].count)});
      }
    }
    // console.log(this.dataPoints)
	}

  us : userStock = {};
  pieData : any = [];
  isPieVisible : boolean = false;

  getUserStockDetail(): void {
    this.apiService.getUserAllDetailStock().subscribe(
        data => {
          this.us = data;
          // console.log("from api first:", this.dataOfPie)
          this.getPieData();
        });
  }


  getPieData():void{
    for(const key in this.us){
      if (this.us.hasOwnProperty(key)) {
        const detail = this.us[key];
        const d= {
          y: detail['currentPrice'] * detail['count'],
          // y: detail['profit'],
          name: detail['name']
        };
        this.pieData.push(d)
      }
    }
    this.chartOptions.data[0].dataPoints=this.pieData
    this.isPieVisible=true
    // console.log("get data and vis :", this.pieData)
  }
  /*
   1.获取并封装数据
   2.画用户股票的饼图
    */

  chartOptions = {
    animationEnabled: true,
    title:{
      text: "Owned Stock"
    },
    data: [{

      type: "doughnut",
      indexLabel: "{name}: {y}",
      innerRadius: "90%",
      yValueFormatString: "#,##0",
      dataPoints: []
    }]
  }

  submitForm(): void {
    if (this.sellForm.valid) {
      console.log('submit', this.sellForm.value.sellCount);
      this.sellStock(this.storage.getItem("STOCK_NUMBER"), this.sellForm.value.sellCount);
    } else {
      Object.values(this.sellForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }
}
