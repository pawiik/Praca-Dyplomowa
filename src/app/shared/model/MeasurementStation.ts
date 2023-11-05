class MeasurementStation {
  stationId: number;
  address: string;
  regionId: number;
  city: City;
  falls: Fall[];
  humidities: Humidity[];
  temperatures: Temperature[];
  uvs: UV[];
  winds: Wind[];
  employees: Employee[];

  constructor(
    stationId?: number,
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
    if (stationId) this.stationId = stationId;
    this.address = address || '';
    this.regionId = regionId || 0;
    this.city = city || new City();
    this.falls = falls || [];
    this.humidities = humidities || [];
    this.temperatures = temperatures || [];
    this.uvs = uvs || [];
    this.winds = winds || [];
    this.employees = employees || [];
  }
}
