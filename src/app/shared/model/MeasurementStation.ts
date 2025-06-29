import {City} from "./City";
import {Fall} from "./Fall";
import {Humidity} from "./Humidity";
import {Temperature} from "./Temperature";
import {UV} from "./UV";
import {Wind} from "./Wind";
import {Employee} from "./Employee";

export class MeasurementStation {
  stationId: number;
  address: string;
  regionId: number;
  city: City | undefined;
  falls: Fall[];
  humidities: Humidity[];
  temperatures: Temperature[];
  uvs: UV[];
  winds: Wind[];
  employees: Employee[];

  constructor(
    stationId: number,
    address?: string,
    regionId?: number,
    city?: City,
    falls?: Fall[],
    humidities?: Humidity[],
    temperatures?: Temperature[],
    uvs?: UV[],
    winds?: Wind[],
    employees?: Employee[]
  ) {
    this.stationId = stationId;
    this.address = address || '';
    this.regionId = regionId || 0;
    this.city = city;
    this.falls = falls || [];
    this.humidities = humidities || [];
    this.temperatures = temperatures || [];
    this.uvs = uvs || [];
    this.winds = winds || [];
    this.employees = employees || [];
  }
}
