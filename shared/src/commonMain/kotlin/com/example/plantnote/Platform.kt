package com.example.plantnote

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform