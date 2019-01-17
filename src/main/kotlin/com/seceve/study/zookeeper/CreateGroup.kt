package com.seceve.study.zookeeper

import org.apache.zookeeper.*
import java.util.concurrent.CountDownLatch

class CreateGroup: Watcher{

    override fun process(event: WatchedEvent) {
        if (event.state == Watcher.Event.KeeperState.SyncConnected)
            connectedSignal.countDown()
    }

    companion object {

        val SESSION_TIMEOUT = 900

    }

    var zk: ZooKeeper? = null
    var connectedSignal: CountDownLatch = CountDownLatch(1)

    fun connect(hosts: String){
        zk = ZooKeeper(hosts, SESSION_TIMEOUT, this)
        connectedSignal.await()
    }

    fun create(groupName: String){
        val path = "/$groupName"
        val createdPath = zk!!.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
        println("Created $createdPath")
    }

    fun close(){
        zk!!.close()
    }


}

fun main(args: Array<String>) {
    val zks = CreateGroup()
    zks.connect(Config.HOSTS)
    zks.create("a")
    zks.close()
}