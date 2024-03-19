package com.tools.records

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform