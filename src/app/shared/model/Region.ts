// @ts-ignore
import {City} from "./City";
import {Alert} from "./Alert";
import {User} from "./User";

export class Region {
  regionId: number;
  name: string;
  cities: City[];
  alerts: Alert[];
  users: Set<User>;

  constructor(
    regionId: number,
    name?: string,
    cities?: City[],
    alerts?: Alert[],
    users?: Set<User>
  ) {
    this.regionId = regionId;
    this.name = name || '';
    this.cities = cities || [];
    this.alerts = alerts || [];
    this.users = users || new Set<User>();
  }
}
