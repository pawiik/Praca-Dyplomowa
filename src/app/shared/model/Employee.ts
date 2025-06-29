import {MeasurementStation} from "./MeasurementStation";

export class Employee {
  employeeId: number;
  measurementStation: MeasurementStation;
  name: string;
  lastName: string;
  phoneNumber: string;
  address: string;

  constructor(
    employeeId: number,
    measurementStation: MeasurementStation,
    name?: string,
    lastName?: string,
    phoneNumber?: string,
    address?: string
  ) {
    this.employeeId = employeeId;
    this.measurementStation = measurementStation
    this.name = name || '';
    this.lastName = lastName || '';
    this.phoneNumber = phoneNumber || '';
    this.address = address || '';
  }
}
