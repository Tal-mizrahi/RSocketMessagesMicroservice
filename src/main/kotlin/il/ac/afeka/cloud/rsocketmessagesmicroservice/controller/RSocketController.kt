package il.ac.afeka.cloud.rsocketmessagesmicroservice.controller

import il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries.MessageBoundary
import il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries.PaginationBoundary
import il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries.SenderAndTargetBoundary
import il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries.TargetBoundary
import il.ac.afeka.cloud.rsocketmessagesmicroservice.services.RSocketService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Controller
class RSocketController(
    val rSocketService: RSocketService
){

    // java -jar rsc.jar -r send-message-req-resp --request -d "{\"sender\":\"kafka@gmail.com\", \"title\":\"kafkaTeam\", \"target\":\"group1\", \"urgent\":\"true\", \"messageDetails\":{\"x\":42}}" tcp://localhost:7001
    @MessageMapping("send-message-req-resp")
    fun createMessageReqResp (
        @Payload message:MessageBoundary):Mono<MessageBoundary> {
        return this.rSocketService.create(message)
    }

    // java -jar rsc.jar -r getAllMessages-req-stream --stream -d "{\"page\":0, \"size\":10}" tcp://localhost:7001
    @MessageMapping("getAllMessages-req-stream")
    fun getAllMessages (@Payload paginationBoundary: PaginationBoundary):Flux<MessageBoundary>{
        return this.rSocketService
            .getAll(paginationBoundary.size, paginationBoundary.page)
    }

    // java -jar rsc.jar -r getMessagesByTarget-channel --channel -d - tcp://localhost:7001

    // "{"target":"group1", "page":0, "size":10}"
    @MessageMapping("getMessagesByTarget-channel")
    fun channelByIds (@Payload targets:Flux<TargetBoundary>):Flux<MessageBoundary > {
        return this.rSocketService
            .searchByTarget(targets)
    }


    // java -jar rsc.jar -r cleanup-fire-and-forget --fnf tcp://localhost:7001
    @MessageMapping("cleanup-fire-and-forget")
    fun cleanup():Mono<Void>{
        return this.rSocketService
            .cleanup()
    }

    // java -jar rsc.jar -r getUrgentMessagesBySenderAndTarget-channel --channel -d - tcp://localhost:7001

    // {"sender":"kafka@gmail.com", "target":"group1", "page":0, "size":10}
    // {"sender":"kafka@gmail.com", "target":"group1@gmail.com", "page":0, "size":10}
    @MessageMapping("getUrgentMessagesBySenderAndTarget-channel")
    fun getUrgentMessagesBySenderAndTarget (@Payload senderAndTargets:Flux<SenderAndTargetBoundary>):Flux<MessageBoundary >{
        return this.rSocketService
            .getUrgentMessagesBySenderAndTarget(senderAndTargets)
    }
}


