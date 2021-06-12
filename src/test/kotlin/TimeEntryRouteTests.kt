import com.jetbrains.handson.httpapi.module
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class TimeEntryRouteTests{

    @Test
    fun testGetTimeEntry() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/timeEntry/100").apply {
                assertEquals(
                    """{"id":"100","time":"9:00","timestamp":"2021-06-13 00:24:41+Z","problems":[]}""", response.content
                )
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

}