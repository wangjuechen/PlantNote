package com.example.plantnote.core

import platform.Foundation.NSUUID


actual fun generateUUID(): Long = NSUUID.UUID().UUIDString.toLong()