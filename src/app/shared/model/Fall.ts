class Fall {
  measurementId: number;
  time: number; // This should be a Date or a string depending on your time format
  temperature: number;
  measurementStation: MeasurementStation; // Assuming 'MeasurementStation' is another class you have defined

  constructor(
    measurementId?: number,
    time?: number,
    temperature?: number,
    measurementStation?: MeasurementStation
  ) {
    if (measurementId) this.measurementId = measurementId;
    this.time = time || 0;
    this.temperature = temperature || 0.0;
    this.measurementStation = measurementStation || new MeasurementStation(); // Replace with actual default if necessary
  }
}
