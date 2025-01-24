package il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries

class PaginationBoundary(
    var page: Int = 0,                   
    var size: Int = 10
) {

    constructor() : this(0, 10)

    override fun toString(): String {
        return "{page=$page," +
                " size=$size}"
    }


}