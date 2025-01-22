package il.ac.afeka.cloud.rsocketmessagesmicroservice.services

import il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries.MessageBoundary
import il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries.TargetBoundary
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface RSocketService {
    fun create(message:MessageBoundary): Mono<MessageBoundary>
    fun getAll (size:Int, page:Int): Flux<MessageBoundary>
    fun searchByTarget(targets:Flux<TargetBoundary>):Flux<MessageBoundary>
    fun cleanup(): Mono<Void>
//    fun getById (id:String):Mono<DemoBoundary>
//    fun update (id:String, update:DemoBoundary):Mono<Void>
//    fun bind (parentId:String, childId:String):Mono<Void>
//    fun unbind (parentId:String, childId:String):Mono<Void>
//    fun unbindChildren (parentId:String):Mono<Void>
//    fun getChildren (parentId:String, size:Int, page:Int): Flux<DemoBoundary>
//    fun getParents (childId:String, size:Int, page:Int): Flux<DemoBoundary>
//    fun cleanup():Mono<Void>
//
//    fun searchByPattern (pattern:Flux<PatternBoundary>):Flux<DemoBoundary>
}