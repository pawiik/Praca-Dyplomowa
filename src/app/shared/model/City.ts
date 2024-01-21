import {Region} from "./Region";
import {MeasurementStation} from "./MeasurementStation";
import {User} from "./User";
export class City {
  private _cityId: number;
  private _cityName: string;
  private _region: Region;
  private _measurementStations: MeasurementStation[];
  private _users: User[];

  constructor(
    cityId: number,
    cityName?: string,
    region?: Region,
    measurementStations?: MeasurementStation[],
    users?: User[]
  ) {
    this._cityId = cityId;
    this._cityName = cityName || '';
    this._region = region || new Region();
    this._measurementStations = measurementStations || [];
    this._users = users || [];
  }


  get cityId(): number {
    return this._cityId;
  }

  set cityId(value: number) {
    this._cityId = value;
  }

  get cityName(): string {
    return this._cityName;
  }

  set cityName(value: string) {
    this._cityName = value;
  }

  get region(): Region {
    return this._region;
  }

  set region(value: Region) {
    this._region = value;
  }

  get measurementStations(): MeasurementStation[] {
    return this._measurementStations;
  }

  set measurementStations(value: MeasurementStation[]) {
    this._measurementStations = value;
  }

  get users(): User[] {
    return this._users;
  }

  set users(value: User[]) {
    this._users = value;
  }
}
