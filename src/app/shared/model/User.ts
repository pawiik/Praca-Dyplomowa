import {Region} from "./Region";
import {City} from "./City";

export class User {
  id: number | null;
  name: string;
  lastName: string;
  phoneNumber: number | null;
  address: string;
  email: string;
  regions: Region[];
  city: City;
  password: string | undefined;
  confirmPassword: string | undefined;

  constructor(
    id: number | null,
    name: string,
    lastName: string,
    phoneNumber: number | null,
    address: string,
    email: string,
    regions: Region[],
    city: City
  ) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.email = email;
    this.regions = regions;
    this.city = city;
  }
}
