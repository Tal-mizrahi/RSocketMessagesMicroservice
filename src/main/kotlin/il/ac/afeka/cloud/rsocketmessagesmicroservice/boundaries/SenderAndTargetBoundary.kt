package il.ac.afeka.cloud.rsocketmessagesmicroservice.boundaries

class SenderAndTargetBoundary(
    var sender: String?,
    var target: String?,
    var page: Int = 0,
    var size: Int = 10
){

    constructor() : this(null, null, 0, 10)

    override fun toString(): String {
        return "{sender=$sender, " +
                "target=$target, " +
                "page=$page, " +
                "size=$size}"
    }


}

