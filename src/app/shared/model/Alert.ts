class Alert {
  alertId: number;
  startTime: number;
  endTime: number;
  region: Region;
  message: string;
  alertType: number;

  constructor(alertId?: number, startTime?: number, endTime?: number, region?: Region, message?: string, alertType?: number) {
    if (alertId) this.alertId = alertId;
    this.startTime = startTime || 0;
    this.endTime = endTime || 0;
    this.region = region || new Region(); // Replace with actual default if necessary
    this.message = message || '';
    this.alertType = alertType || 0;
  }
}

// Assuming the 'Region' class might look something like this:
class Region {
  // Define the properties and constructor for Region based on how you've structured it in your backend.
}
