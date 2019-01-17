package com.seceve.study.zookeeper

import org.apache.zookeeper.CreateMode
import org.apache.zookeeper.Watcher
import org.apache.zookeeper.ZooDefs
import java.nio.charset.Charset

class ActivityKeyValueStore: ConnectionWather() {

    companion object {

        val CHARSET = Charset.forName("UTF-8")

    }

    fun write(path: String, value: String){

        if (zk == null) return

        val stat = zk!!.exists(path, false)

        if (stat == null)
            zk!!.create(path, value.toByteArray(CHARSET), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
        else
            zk!!.setData(path, value.toByteArray(CHARSET), -1)

    }

    fun read(path: String, wather: Watcher): String{
        val data = zk!!.getData(path, wather, null)
        return String(data, CHARSET)
    }

}