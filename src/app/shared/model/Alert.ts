import {Region} from "./Region";

export class Alert {
  alertId: number;
  startTime: number;
  endTime: number;
  region: Region;
  message: string;
  alertType: number;

  constructor(alertId: number, startTime?: number, endTime?: number, region?: Region, message?: string, alertType?: number) {
    this.alertId = alertId;
    this.startTime = startTime || 0;
    this.endTime = endTime || 0;
    this.region = region || new Region();
    this.message = message || '';
    this.alertType = alertType || 0;
  }
}


