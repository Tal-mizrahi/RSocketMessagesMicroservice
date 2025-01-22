package il.ac.afeka.cloud.rsocketmessagesmicroservice.crud

import il.ac.afeka.cloud.rsocketmessagesmicroservice.entities.MessageEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.query.Param
import reactor.core.publisher.Flux

interface RSocketCrud: ReactiveMongoRepository<MessageEntity, String> {
    fun findAllByIdNotNull(page: Pageable): Flux<MessageEntity>
    fun findAllByTarget(@Param("target") target: String, page: Pageable): Flux<MessageEntity>
}