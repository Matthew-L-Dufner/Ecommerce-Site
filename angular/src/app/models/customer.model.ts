export interface Customer {
  customerId: number | null,
  firstName: String,
  lastName: String,
  email: String,
  password: String,
  phone: number,
  birthDate: number,
  created: number,
}

export interface createCustomerDTO extends Omit<Customer, 'customerId'> {

}
