package com.pepi.simpleappforwork.common.util

//compile time safety turns statement into expression
val <T> T.exhaustive: T
    get() = this