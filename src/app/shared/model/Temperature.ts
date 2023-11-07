import {MeasurementStation} from "./MeasurementStation";

export class Temperature {
  measurementId: number;
  time: number;
  temperature: number;
  measurementStation: MeasurementStation;

  constructor(
    measurementId: number,
    time: number,
    temperature: number,
    measurementStation: MeasurementStation
  ) {
    this.measurementId = measurementId;
    this.time = time;
    this.temperature = temperature;
    this.measurementStation = measurementStation;
  }
}
