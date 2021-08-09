package db

import models.Employee
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID

class EmployeeTableTest extends AnyFlatSpec {

  val employeeTable = new EmployeeTable
  val employee: Employee = Employee(Some(UUID.randomUUID()),"Bhavya",24,"bhavya@gmail.com")

  "add" should "add the employee" in {
    val id = employeeTable.add(employee)
    assert(Some(id).nonEmpty)
    employeeTable.deleteById(id)
  }

  "getById" should "not return the employee when employee id does not exists" in {
    assertThrows[RuntimeException](employeeTable.getById(Some(UUID.randomUUID())))
  }

  it should "return the employee when employee id exists" in {
    val id = employeeTable.add(employee)
    assert(employeeTable.getById(id).nonEmpty)
    employeeTable.deleteById(id)
  }

  "getAll" should "return empty list when ListBuffer is empty" in {
    assert(employeeTable.getAll.isEmpty)
  }

  it should "be valid as ListBuffer should not be empty" in {
    val id = employeeTable.add(employee)
    assert(employeeTable.getAll.nonEmpty)
    employeeTable.deleteById(id)
  }

  "update" should "not update when employee id does not exists" in {
    val result = employeeTable.update(Some(UUID.randomUUID()),employee)
    assert(!result)
  }

  it should "update when employee id exists" in {
    val id = employeeTable.add(employee)
    val updatedEmployee: Employee = Employee(id,"Bhavya",24,"bhavya@gmail.com")

    assert(employeeTable.update(id,updatedEmployee))
    employeeTable.deleteById(id)
  }

  "deleteByID" should "not delete when employee id does not exists" in {
    assert(!employeeTable.deleteById(Some(UUID.randomUUID())))
  }

  it should "delete the employee when employee id exists" in {
    val id = employeeTable.add(employee)
    assert(employeeTable.deleteById(id))
  }

  "deleteAll" should "not delete when no employee exists" in {
    assert(!employeeTable.deleteAll())
  }

  it should "delete all employees when employees exists" in {
    employeeTable.add(employee)
    assert(employeeTable.deleteAll())
  }

}
