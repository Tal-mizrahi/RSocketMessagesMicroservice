package il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries

class TargetBoundary(

    var target: String?,
    var page: Int,
    var size: Int

) {

    constructor() : this(null, 0, 10)

    override fun toString(): String {
        return "{target=$target, " +
                "page=$page, " +
                "size=$size}"
    }


}