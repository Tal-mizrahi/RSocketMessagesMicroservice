package il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries

class PaginationBoundary(
    var page: Int,
    var size: Int
) {

    constructor() : this(0, 10)

    override fun toString(): String {
        return "{page=$page," +
                " size=$size}"
    }


}