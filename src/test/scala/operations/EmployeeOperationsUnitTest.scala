package operations

import db.EmployeeTable
import models.Employee
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID

class EmployeeOperationsUnitTest extends AnyFlatSpec {

  val mockedEmployeeTable: EmployeeTable = mock[EmployeeTable]
  val employeeOperations = new EmployeeOperations(mockedEmployeeTable)

  val employee: Employee = Employee(name = "Bhavya",age = 24,emailId = "bhavya@gmail.com")

  "add" should "not add as employeeTable throws Exception" in {
    intercept[RuntimeException]{
      when(mockedEmployeeTable.add(employee)) thenThrow(throw new RuntimeException("Invalid operation"))
    }
    assertThrows[RuntimeException](employeeOperations.add(employee))
  }

  it should "add the employee" in {
    when(mockedEmployeeTable.add(employee)) thenReturn Option(UUID.randomUUID())
    assert(employeeOperations.add(employee).nonEmpty)
  }

  "getById" should "throw an exception when employee id does not exists" in {
    intercept[RuntimeException]{
      when(mockedEmployeeTable.getById(employee.id)) thenThrow(throw new NoSuchElementException)
    }
    assertThrows[RuntimeException](employeeOperations.getById(employee.id))
  }

  it should "return the employee when employee id exists" in {
    val uuid = UUID.randomUUID()
    when(mockedEmployeeTable.getById(Some(uuid))) thenReturn List(employee)
    assert(employeeOperations.getById(Some(uuid)).nonEmpty)
  }

  "getAll" should "return empty list when ListBuffer is empty" in {
    when(mockedEmployeeTable.getAll) thenReturn List()
    assert(employeeOperations.getAll.isEmpty)
  }

  it should "return list of employees when ListBuffer is not empty" in {
    when(mockedEmployeeTable.getAll) thenReturn List(employee)
    assert(employeeOperations.getAll.nonEmpty)
  }

  "update" should "not add as employeeTable returns false" in {
    when(mockedEmployeeTable.update(employee.id,employee)) thenReturn false
    assert(!employeeOperations.update(employee.id,employee))
  }

  it should "add the employee" in {
    when(mockedEmployeeTable.update(employee.id,employee)) thenReturn true
    assert(employeeOperations.update(employee.id,employee))
  }

  "deleteByID" should "not delete the employee when employee id is not valid" in {
    val uuid = UUID.randomUUID()
    when(mockedEmployeeTable.deleteById(Some(uuid))) thenReturn false
    assert(!employeeOperations.deleteById(Some(uuid)))
  }

  it should "delete the employee when employee id is valid" in {
    val uuid = UUID.randomUUID()
    when(mockedEmployeeTable.deleteById(Some(uuid))) thenReturn true
    assert(employeeOperations.deleteById(Some(uuid)))
  }

  "deleteAll" should "not delete all employees as ListBuffer is empty" in {
    when(mockedEmployeeTable.deleteAll()) thenReturn false
    assert(!employeeOperations.deleteAll())
  }

  it should "delete all employees as ListBuffer is not empty" in {
    when(mockedEmployeeTable.deleteAll()) thenReturn true
    assert(employeeOperations.deleteAll())
  }

}
