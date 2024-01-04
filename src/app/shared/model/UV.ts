import {MeasurementStation} from "./MeasurementStation";

export class UV {
  measurementId: number;
  time: number;
  uv: number;
  measurementStationId: MeasurementStation;

  constructor(
    measurementId: number,
    time: number,
    uv: number,
    measurementStation: MeasurementStation
  ) {
    this.measurementId = measurementId;
    this.time = time;
    this.uv = uv;
    this.measurementStationId = measurementStation;
  }
}
