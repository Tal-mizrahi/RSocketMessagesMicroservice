package il.ac.afeka.cloud.rsocketmessagesmicroservice.services

import il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries.MessageBoundary
import il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries.SenderAndTargetBoundary
import il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries.TargetBoundary
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface RSocketService {
    fun create(message:MessageBoundary): Mono<MessageBoundary>
    fun getAll (size:Int, page:Int): Flux<MessageBoundary>
    fun searchByTarget(targets:Flux<TargetBoundary>):Flux<MessageBoundary>
    fun cleanup(): Mono<Void>
    fun getUrgentMessagesBySenderAndTarget(senderAndTargets: Flux<SenderAndTargetBoundary>) : Flux<MessageBoundary>
}