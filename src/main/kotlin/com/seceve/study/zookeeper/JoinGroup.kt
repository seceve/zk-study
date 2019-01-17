package com.seceve.study.zookeeper

import org.apache.zookeeper.CreateMode
import org.apache.zookeeper.ZooDefs

class JoinGroup: ConnectionWather() {

    fun join(groupName: String, memberName: String){
        val path = "/$groupName/$memberName"
        val createdPath = zk?.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL)
        if (zk != null)
            println("Created $createdPath")
    }

}

fun main(args: Array<String>) {
    val joinGroup = JoinGroup()
    joinGroup.connect(Config.HOSTS)
    joinGroup.join("a", "b")
    Thread.sleep(Long.MAX_VALUE)
}