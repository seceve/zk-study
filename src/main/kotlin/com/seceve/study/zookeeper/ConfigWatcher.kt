package com.seceve.study.zookeeper

import org.apache.zookeeper.WatchedEvent
import org.apache.zookeeper.Watcher

class ConfigWatcher(hosts: String): Watcher {

    var store: ActivityKeyValueStore? = null

    init {
        store = ActivityKeyValueStore()
        store!!.connect(hosts)
    }

    fun displayConfig(){
        if (store == null) return
        val value = store!!.read(ConfigUpdater.PATH, this)
        println("Read ${ConfigUpdater.PATH} as $value")
    }

    override fun process(event: WatchedEvent) {
        if (event.type== Watcher.Event.EventType.NodeDataChanged){
            displayConfig()
        }
    }

}

fun main(args: Array<String>) {
    val configWatcher = ConfigWatcher(Config.HOSTS)
    configWatcher.displayConfig()
    Thread.sleep(Long.MAX_VALUE)
}