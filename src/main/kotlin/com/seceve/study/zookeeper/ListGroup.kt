package com.seceve.study.zookeeper

class ListGroup: ConnectionWather() {

    fun list(groupName: String){

        val path = "/$groupName"

        if (zk == null) System.exit(0)

        val children = zk!!.getChildren(path, false)

        if (children.isEmpty()) System.exit(0)

        for (child in children){
            println("$child")
        }

    }

}

fun main(args: Array<String>) {
    val listGroup = ListGroup()
    listGroup.connect(Config.HOSTS)
    listGroup.list("a")
    listGroup.close()
}