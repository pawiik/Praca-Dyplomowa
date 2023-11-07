import {MeasurementStation} from "./MeasurementStation";

export class Employee {
  employeeId: number;
  measurementStation: MeasurementStation; // Assuming 'MeasurementStation' is another class you have defined
  name: string;
  lastName: string;
  phoneNumber: string; // Phone numbers should be strings to preserve leading zeros and formatting
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
    this.measurementStation = measurementStation // Replace with actual default if necessary
    this.name = name || '';
    this.lastName = lastName || '';
    this.phoneNumber = phoneNumber || '';
    this.address = address || '';
  }
}
