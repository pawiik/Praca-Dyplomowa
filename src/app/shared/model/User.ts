import {Region} from "./Region";
import {City} from "./City";

export class User {
  userId: string;
  account: {};
  name: string;
  lastName: string;
  phoneNumber: number | null;
  address: string;
  emailAddress: string;
  regions: Region[];
  city: City | null;

  constructor(
    id: string,
    account:{},
    name: string,
    lastName: string,
    phoneNumber: number | null,
    address: string,
    emailAddress: string,
    regions: Region[],
    city: City | null
  ) {
    this.userId = id;
    this.account = account;
    this.name = name;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.emailAddress = emailAddress;
    this.regions = regions;
    this.city = city;
  }
}
