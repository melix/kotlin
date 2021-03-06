
// FILE: 1.kt

package test

inline fun myrun(s: () -> Unit) {
    val z = "myrun"
    s()
}

inline fun test(crossinline s: () -> Unit) {
    {
        val z = 1;
        myrun(s)
        val x = 1;
    }()
}

// FILE: 2.kt

import test.*

fun box(): String {
    var result = "fail"

    test {
        result = "OK"
    }

    return result
}


// FILE: 1.smap
SMAP
1.kt
Kotlin
*S Kotlin
*F
+ 1 1.kt
test/_1Kt$test$1
+ 2 1.kt
test/_1Kt
*L
1#1,19:1
7#2,3:20
*S KotlinDebug
*F
+ 1 1.kt
test/_1Kt$test$1
*L
14#1:20,3
*E


// FILE: 2.smap

SMAP
2.kt
Kotlin
*S Kotlin
*F
+ 1 2.kt
_2Kt
+ 2 1.kt
test/_1Kt
*L
1#1,16:1
12#2,6:17
*S KotlinDebug
*F
+ 1 2.kt
_2Kt
*L
8#1:17,6
*E

SMAP
1.kt
Kotlin
*S Kotlin
*F
+ 1 1.kt
test/_1Kt$test$1
+ 2 1.kt
test/_1Kt
+ 3 2.kt
_2Kt
*L
1#1,19:1
7#2,2:20
9#2:24
9#3,2:22
*S KotlinDebug
*F
+ 1 1.kt
test/_1Kt$test$1
*L
14#1:20,2
14#1:24
*E
