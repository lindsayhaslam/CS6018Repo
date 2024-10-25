package lab6

import org.jetbrains.exposed.dao.id.IntIdTable


object User : IntIdTable() {}

//Define the Posts table for storing posts
object Posts : IntIdTable() {
    //The post
    val text = varchar("text", 255)
    //Timestamp of post creation
    val timestamp = long("timestamp")
}


