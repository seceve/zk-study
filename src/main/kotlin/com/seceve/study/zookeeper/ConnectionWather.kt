package com.seceve.study.zookeeper

import org.apache.zookeeper.WatchedEvent
import org.apache.zookeeper.Watcher
import org.apache.zookeeper.ZooKeeper
import java.util.concurrent.CountDownLatch

open class ConnectionWather: Watcher {

    companion object {
        val SESSION_TIMEOUT = 5000
    }

    protected var zk: ZooKeeper? = null
    private var connectedSignal: CountDownLatch = CountDownLatch(1)

    fun connect(hosts: String){
        zk = ZooKeeper(hosts, SESSION_TIMEOUT, this)
        connectedSignal.await()
    }

    override fun process(event: WatchedEvent) {
        if (event.state == Watcher.Event.KeeperState.SyncConnected)
            connectedSignal.countDown()
    }

    fun close(){
        zk?.close()
    }

}