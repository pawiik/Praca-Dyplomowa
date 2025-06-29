import {MeasurementStation} from "./MeasurementStation";

export class Fall {
  measurementId: number;
  time: number;
  fall: number;
  measurementStationId: MeasurementStation

  constructor(
    measurementId: number,
    time: number,
    temperature: number,
    measurementStation: MeasurementStation
  ) {
    this.measurementId = measurementId;
    this.time = time || 0;
    this.fall = temperature || 0.0;
    this.measurementStationId = measurementStation;
  }
}
