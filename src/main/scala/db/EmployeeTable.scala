package db

import models.Employee

import java.util.UUID
import scala.collection.mutable.ListBuffer
import scala.math.Ordered.orderingToOrdered
import scala.util.{Failure, Success, Try}

class EmployeeTable {

  private val listBuffer = ListBuffer.empty[Employee]

  def add(employee: Employee): Option[UUID] = {
    Try { listBuffer.append(employee) } match {
      case Success(_) => listBuffer.last.id
      case Failure(_) => throw new RuntimeException("Invalid Operation")
    }
  }

  def getById(id: Option[UUID]): List[Employee] = {
    val list = filterListById(id)
    if(list != Nil) list else throw new RuntimeException("Employee does not exist")
  }

  def getAll: List[Employee] = {
    listBuffer.toList
  }

  def update(id: Option[UUID], updatedEmployee: Employee): Boolean = {
    val index = findIndexById(id)
    if(index != -1) { listBuffer.update(index,updatedEmployee); true } else false
  }

  def deleteById(id: Option[UUID]): Boolean = {
    val index = findIndexById(id)
    if(index != -1) { listBuffer.remove(index); true } else false
  }

  def deleteAll(): Boolean = {
    if(listBuffer.nonEmpty) { listBuffer.remove(0,listBuffer.length); true } else false
  }

  private def filterListById(id: Option[UUID]): List[Employee] = {
    listBuffer.filter(listBuffer => listBuffer.id.compareTo(id) == 0).toList
  }

  private def findIndexById(id: Option[UUID]): Int = {
    val list = filterListById(id)
    if(list != Nil) listBuffer.indexOf(list.head) else -1
  }
}
