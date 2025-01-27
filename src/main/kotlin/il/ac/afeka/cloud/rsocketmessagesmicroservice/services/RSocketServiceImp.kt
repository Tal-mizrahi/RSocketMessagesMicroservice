package il.ac.afeka.cloud.rsocketmessagesmicroservice.services

import il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries.MessageBoundary
import il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries.SenderAndTargetBoundary
import il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries.TargetBoundary
import il.ac.afeka.cloud.rsocketmessagesmicroservice.crud.RSocketCrud
import il.ac.afeka.cloud.rsocketmessagesmicroservice.exceptions.BadRequestException
import il.ac.afeka.cloud.rsocketmessagesmicroservice.utils.InputValidation
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap

@Service
class RSocketServiceImp(
    val rSocketCrud: RSocketCrud
): RSocketService {

    override fun create(message: MessageBoundary): Mono<MessageBoundary> {
        return Mono.just(message)
            .flatMap {
                it.id = null
                it.publicationTimestamp = ZonedDateTime.now()
                   .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"))

                if (it.urgent == null) {
                    it.urgent = false
                }

                if(!InputValidation.isValidEmail(it.sender)){
                    Mono.error {BadRequestException("Email is invalid!") }
                }
               else if(!InputValidation.isValidTitle(it.title)){
                    Mono.error {BadRequestException("Title must not be null") }
                }

                else{
                    Mono.just(it.toEntity())
                }

            }
            .flatMap { this.rSocketCrud.save(it) } // Save entity to database
            .map { MessageBoundary(it) } // Convert saved entity back to boundary
            .log()
    }


    override fun getAll(size: Int, page: Int): Flux<MessageBoundary> {

        return this.rSocketCrud
            .findAllByIdNotNull(PageRequest.of(page, size, Sort.Direction.DESC, "publicationTimestamp", "id"))
            .map { MessageBoundary(it) }
            .log()
    }

    override fun searchByTarget(targets: Flux<TargetBoundary>): Flux<MessageBoundary> {
        return targets
            .flatMap {
                this.rSocketCrud
                    .findAllByTarget(it.target!!,PageRequest.of(it.page, it.size, Sort.Direction.DESC, "publicationTimestamp", "id"))
                    .map { MessageBoundary(it)}
            }
            .log()
    }

    override fun cleanup(): Mono<Void> {
        return this.rSocketCrud
            .deleteAll()
            .log()
    }

    override fun getUrgentMessagesBySenderAndTarget(senderAndTargets: Flux<SenderAndTargetBoundary>): Flux<MessageBoundary> {
        return senderAndTargets
            .flatMap {
                if(!InputValidation.isValidEmail(it.target)) {
                    Flux.empty()
                } else {
                    this.rSocketCrud
                        .findAllByTargetAndSenderAndUrgentIsTrue(
                            it.target!!,
                            it.sender!!,
                            PageRequest.of(it.page, it.size, Sort.Direction.DESC, "publicationTimestamp", "id")
                        )
                        .map { MessageBoundary(it) }
                }
            }
            .log()
    }
}