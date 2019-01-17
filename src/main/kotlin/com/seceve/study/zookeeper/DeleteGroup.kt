package com.seceve.study.zookeeper

import kotlin.system.exitProcess

class DeleteGroup: ConnectionWather(){

    fun delete(groupName: String){
        val path = "/$groupName"
        if (zk == null) exitProcess(0)

        val children = zk!!.getChildren(path, false)
        for (child in children) zk!!.delete("$path/$child", -1)
        zk!!.delete(path, -1)
    }

}

fun main(args: Array<String>) {
    val deleteGroup = DeleteGroup()
    deleteGroup.connect(Config.HOSTS)
    deleteGroup.delete("a")
    deleteGroup.close()
}