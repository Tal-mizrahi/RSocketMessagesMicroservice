package il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries

class TargetBoundary(

    var target: String?,
    var page: Int = 0,
    var size: Int = 10


) {

    constructor() : this(null, 0, 10)

    override fun toString(): String {
        return "{target=$target, " +
                "page=$page, " +
                "size=$size}"
    }


}