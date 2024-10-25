package lab6

import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.Resources
import io.ktor.server.response.*
import io.ktor.server.routing.routing

import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

fun Application.configureResources() {
    install(Resources)

    routing {
        //Get all posts
        get<PostsResource> {
            call.respond(
                newSuspendedTransaction(Dispatchers.IO) {
                    Posts.selectAll().map {
                        PostResponse(
                            id = it[Posts.id].value,
                            text = it[Posts.text],
                            timestamp = it[Posts.timestamp]
                        )
                    }
                }
            )
        }

        // Get posts since a specific timestamp
        get<PostsResource.Since> {
            val sinceTimestamp = it.timestamp
            call.respond(
                newSuspendedTransaction(Dispatchers.IO) {
                    Posts.select { Posts.timestamp greaterEq sinceTimestamp }.map {
                        PostResponse(
                            id = it[Posts.id].value,
                            text = it[Posts.text],
                            timestamp = it[Posts.timestamp]
                        )
                    }
                }
            )
        }

        //Get post by ID
        get<PostsResource.ById> {
            val postId = it.id
            val post = newSuspendedTransaction(Dispatchers.IO) {
                Posts.select { Posts.id eq postId }.singleOrNull()?.let {
                    PostResponse(
                        id = it[Posts.id].value,
                        text = it[Posts.text],
                        timestamp = it[Posts.timestamp]
                    )
                }
            }
            if (post != null) {
                call.respond(post)
            } else {
                call.respondText("Post not found", status = HttpStatusCode.NotFound)
            }
        }

        //Create a new post
        post<PostsResource.Create> {
            val postRequest = call.receive<PostRequest>()
            val postId = newSuspendedTransaction(Dispatchers.IO, DBSettings.db) {
                Posts.insert {
                    it[text] = postRequest.text
                    it[timestamp] = System.currentTimeMillis()
                }[Posts.id].value
            }
            call.respondText("Post created with ID: $postId", status = HttpStatusCode.Created)
        }

        //Delete a post by ID
        delete<PostsResource.Delete> {
            val postId = it.id
            newSuspendedTransaction(Dispatchers.IO) {
                Posts.deleteWhere { Posts.id eq postId }
            }
            call.respondText("Post with ID: $postId deleted", status = HttpStatusCode.OK)
        }
    }
}

@Serializable
data class PostRequest(val text: String)

@Serializable
data class PostResponse(val id: Int, val text: String, val timestamp: Long)

@Resource("/posts")
class PostsResource {
    @Resource("since/{timestamp}")
    class Since(val parent: PostsResource = PostsResource(), val timestamp: Long)

    @Resource("{id}")
    class ById(val parent: PostsResource = PostsResource(), val id: Int)

    @Resource("create")
    class Create(val parent: PostsResource = PostsResource())

    @Resource("delete/{id}")
    class Delete(val parent: PostsResource = PostsResource(), val id: Int)
}