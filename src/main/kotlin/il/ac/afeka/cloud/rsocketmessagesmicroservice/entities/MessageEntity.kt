package il.ac.afeka.cloud.rsocketmessagesmicroservice.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "messages")
class MessageEntity (
    @Id var id: String?,
    var sender: String?,
    var title: String?,
    var publicationTimestamp: String?,
    var target: String?,
    var urgent: Boolean?,
    var messageDetails: Map<String, Any>?
)  {

    constructor() : this(null, null, null, null, null, null, null)

    override fun toString(): String {
        return "{id=$id," +
                " sender=$sender," +
                " title=$title," +
                " publicationTimestamp=$publicationTimestamp," +
                " target=$target," +
                " urgent=$urgent," +
                " messageDetails=$messageDetails}"
    }
}