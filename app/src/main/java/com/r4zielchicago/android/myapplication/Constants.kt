package com.r4zielchicago.android.myapplication

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {

    companion object {
        val ts = Timestamp(System.currentTimeMillis()).time.toString()

        const val API_KEY = "b6bc9ce85bf5a179e988c25b9fb2e6be"
        const val PRIVATE_KEY = "ed5047a179e4476ead80a8fbe5474697746ef454"
        const val limit = "100"

        fun hash(): String {
            val input = "$ts$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")

            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}