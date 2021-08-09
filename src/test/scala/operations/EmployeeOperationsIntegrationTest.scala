package operations

import db.EmployeeTable
import models.Employee
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID

class EmployeeOperationsIntegrationTest extends AnyFlatSpec{

  val employeeTable = new EmployeeTable
  val employeeOperations = new EmployeeOperations(employeeTable)

  "add" should "add the employee" in {
    val employee: Employee = Employee(name = "Bhavya",age = 24,emailId = "bhavya@gmail.com")

    val id = employeeOperations.add(employee)
    assert(Some(id).nonEmpty)
    employeeOperations.deleteById(id)
  }

  "getById" should "throw an exception when employee id does not exists" in {
    assertThrows[RuntimeException](employeeOperations.getById(Some(UUID.randomUUID())))
  }

  it should "return the employee when employee id exists" in {
    val employee: Employee = Employee(name = "Bhavya",age = 24,emailId = "bhavya@gmail.com")
    val id = employeeOperations.add(employee)

    assert(employeeOperations.getById(id).nonEmpty)
    employeeOperations.deleteById(id)
  }

  "getAll" should "return empty list when ListBuffer is empty" in {
    assertThrows[RuntimeException](employeeOperations.getById(Some(UUID.randomUUID())))
  }

  it should "return list of employees when ListBuffer is not empty" in {
    val employee: Employee = Employee(name = "Bhavya",age = 24,emailId = "bhavya@gmail.com")
    val id = employeeOperations.add(employee)

    assert(employeeOperations.getById(id).nonEmpty)
    employeeOperations.deleteById(id)
  }

  "update" should "not update as employee id does not exists" in {
    val employee: Employee = Employee(name = "Bhavya",age = 24,emailId = "bhavya@gmail.com")

    assert(!employeeOperations.update(Some(UUID.randomUUID()),employee))
  }

  it should "update the employee" in {
    val employee: Employee = Employee(name = "Bhavya",age = 24,emailId = "bhavya@gmail.com")
    val id = employeeOperations.add(employee)

    val updatedEmployee: Employee = Employee(name = "Bhanu",age = 24,emailId = "bhavya@gmail.com")

    assert(employeeOperations.update(id,updatedEmployee))
    employeeOperations.deleteById(id)
  }

  "deleteByID" should "not delete the employee when employee id is not valid" in {
    assert(!employeeOperations.deleteById(Some(UUID.randomUUID())))
  }

  it should "delete the employee when employee id is valid" in {
    val employee: Employee = Employee(name = "Bhavya",age = 24,emailId = "bhavya@gmail.com")
    val id = employeeOperations.add(employee)

    assert(employeeOperations.deleteById(id))
  }

  "deleteAll" should "not delete all employees as ListBuffer is empty" in {
    assert(!employeeOperations.deleteAll())
  }

  it should "delete all employees as ListBuffer is not empty" in {
    val employee: Employee = Employee(name = "Bhavya",age = 24,emailId = "bhavya@gmail.com")
    employeeOperations.add(employee)

    assert(employeeOperations.deleteAll())
  }

}
