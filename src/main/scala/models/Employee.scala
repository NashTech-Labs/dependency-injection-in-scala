package models

import java.util.UUID

case class Employee(
                     id: Option[UUID] = None,
                     name: String,
                     age: Int,
                     emailId: String
                   )
