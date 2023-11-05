class City {
  cityId: number;
  cityName: string;
  region: Region; // Assuming 'Region' is another class you have defined
  measurementStations: MeasurementStation[]; // Assuming 'MeasurementStation' is an array of another class or interface
  users: User[]; // Assuming 'User' is an array of another class or interface

  constructor(
    cityId?: number,
    cityName?: string,
    region?: Region,
    measurementStations?: MeasurementStation[],
    users?: User[]
  ) {
    if (cityId) this.cityId = cityId;
    this.cityName = cityName || '';
    this.region = region || new Region(); // Replace with actual default if necessary
    this.measurementStations = measurementStations || [];
    this.users = users || [];
  }
}
