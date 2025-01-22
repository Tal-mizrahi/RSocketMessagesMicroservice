package il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries

import il.ac.afeka.cloud.rsocketmessagesmicroservice.entities.MessageEntity

class MessageBoundary(
    var id: String?,
    var sender: String?,
    var title: String?,
    var publicationTimestamp: String?,
    var target: String?,
    var urgent: Boolean?,
    var messageDetails: Map<String, Any>?
)  {

    constructor() : this(null, null, null, null, null, null, null)

    constructor(entity: MessageEntity) : this(){
        this.id = entity.id
        this.sender = entity.sender
        this.title = entity.title
        this.publicationTimestamp = entity.publicationTimestamp
        this.target = entity.target
        this.urgent = entity.urgent
        this.messageDetails = entity.messageDetails

    }

    fun toEntity(): MessageEntity {
        return MessageEntity(
            this.id,
            this.sender,
            this.title,
            this.publicationTimestamp,
            this.target,
            this.urgent,
            this.messageDetails
        )
    }

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