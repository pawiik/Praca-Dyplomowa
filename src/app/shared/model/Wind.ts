import {MeasurementStation} from "./MeasurementStation";

export class Wind {
  measurementId: number;
  time: number;
  wind: number;
  measurementStation: MeasurementStation;

  constructor(
    measurementId: number,
    time: number,
    wind: number,
    measurementStation: MeasurementStation
  ) {
    this.measurementId = measurementId;
    this.time = time;
    this.wind = wind;
    this.measurementStation = measurementStation;
  }
}
