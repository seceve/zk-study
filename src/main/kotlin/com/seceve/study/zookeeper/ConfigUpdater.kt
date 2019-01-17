package com.seceve.study.zookeeper

import java.util.*
import java.util.concurrent.TimeUnit

class ConfigUpdater(hosts: String) {

    companion object {

        val PATH = "/config"

    }

    var store: ActivityKeyValueStore? = null
    var random = Random()

    init {
        store = ActivityKeyValueStore()
        store!!.connect(hosts)
    }

    fun run() {

        while (true){
            val value = "${random.nextInt()} "
            store!!.write(PATH, value)
            println("Set $PATH to $value")
            TimeUnit.SECONDS.sleep(random.nextInt(10).toLong())
        }

    }



}

fun main(args: Array<String>) {
    val configUpdater = ConfigUpdater(Config.HOSTS)
    configUpdater.run()
}