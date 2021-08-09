package operations

import db.EmployeeTable
import models.Employee

import java.util.UUID

class EmployeeOperations (
  employeeTable: EmployeeTable
) {

  def add(employee: Employee): Option[UUID] = {
    employeeTable.add(employee)
  }

  def getById(id: Option[UUID]): List[Employee] = {
    employeeTable.getById(id)
  }

  def getAll: List[Employee] = {
    employeeTable.getAll
  }

  def update(id: Option[UUID], updatedEmployee: Employee): Boolean = {
    employeeTable.update(id, updatedEmployee)
  }

  def deleteById(id: Option[UUID]): Boolean = {
    employeeTable.deleteById(id)
  }

  def deleteAll(): Boolean = {
    employeeTable.deleteAll()
  }

}
