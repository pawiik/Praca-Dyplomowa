class Humidity {
  measurementId: number;
  time: number; // This should be a Date or a string depending on your time format.
  humidity: number; // Assuming 'humidity' is the correct property name instead of 'temperature'.
  measurementStation: MeasurementStation; // Assuming 'MeasurementStation' is another class you have defined.

  constructor(
    measurementId: number,
    time?: number,
    humidity?: number,
    measurementStation?: MeasurementStation
  ) {
    this.measurementId = measurementId;
    this.time = time || 0;
    this.humidity = humidity || 0.0;
    this.measurementStation = measurementStation || new MeasurementStation();
  }
}
