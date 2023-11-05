// @ts-ignore
class Region {
  regionId: number | null;
  name: string;
  cities: City[];
  alerts: Alert[];
  users: Set<User>;

  constructor(
    regionId?: number,
    name?: string,
    cities?: City[],
    alerts?: Alert[],
    users?: Set<User>
  ) {
    this.regionId = regionId ?? null;
    this.name = name || '';
    this.cities = cities || [];
    this.alerts = alerts || [];
    this.users = users || new Set<User>();
  }
}
