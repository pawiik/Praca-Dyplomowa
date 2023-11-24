import {MeasurementStation} from "./MeasurementStation";

export class Humidity {
  measurementId: number;
  time: number;
  humidity: number;
  measurementStation: MeasurementStation

  constructor(
    measurementId: number,
    time: number,
    humidity: number,
    measurementStation: MeasurementStation
  ) {
    this.measurementId = measurementId;
    this.time = time || 0;
    this.humidity = humidity || 0.0;
    this.measurementStation = measurementStation;
  }
}
