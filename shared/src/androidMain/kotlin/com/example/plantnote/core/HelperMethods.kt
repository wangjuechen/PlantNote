package com.example.plantnote.core

import java.util.UUID


actual fun generateUUID(): Long = UUID.randomUUID().toString().toLong()