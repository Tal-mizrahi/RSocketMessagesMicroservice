package il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries

class SenderAndTargetBoundary(
    var sender: String?,
    var target: String?,
    var page: Int,
    var size: Int
){

    constructor() : this(null, null, 0, 10)

    override fun toString(): String {
        return "{sender=$sender, " +
                "target=$target, " +
                "page=$page, " +
                "size=$size}"
    }


}

