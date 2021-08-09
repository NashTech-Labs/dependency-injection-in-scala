package injector

import db.EmployeeTable
import operations.EmployeeOperations

class Injector {
  val employeeTable = new EmployeeTable
  val employeeOperations = new EmployeeOperations(employeeTable)

}
