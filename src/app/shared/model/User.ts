import {Region} from "./Region";
import {City} from "./City";

export class User {
  id: string;
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
    email: string,
    regions: Region[],
    city: City | null
  ) {
    this.id = id;
    this.account = account;
    this.name = name;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.emailAddress = email;
    this.regions = regions;
    this.city = city;
  }
}
