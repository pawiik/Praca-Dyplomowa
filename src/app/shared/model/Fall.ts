import {MeasurementStation} from "./MeasurementStation";

export class Fall {
  measurementId: number;
  time: number;
  temperature: number;
  measurementStation: MeasurementStation

  constructor(
    measurementId: number,
    time: number,
    temperature: number,
    measurementStation: MeasurementStation
  ) {
    this.measurementId = measurementId;
    this.time = time || 0;
    this.temperature = temperature || 0.0;
    this.measurementStation = measurementStation;
  }
}
