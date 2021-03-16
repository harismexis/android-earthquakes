package com.example.earthquakes.framework.util

// FROM STACK OVERFLOW
// https://stackoverflow.com/questions/35513636/multiple-variable-let-in-kotlin

inline fun <T: Any> guardLet(vararg elements: T?, closure: () -> Nothing): List<T> {
    return if (elements.all { it != null }) {
        elements.filterNotNull()
    } else {
        closure()
    }
}

inline fun <T: Any> ifLet(vararg elements: T?, closure: (List<T>) -> Unit) {
    if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    }
}

/*
// Will print
val (first, second, third) = guardLet("Hello", 3, Thing("Hello")) { return }
println(first)
println(second)
println(third)

// Will return
val (first, second, third) = guardLet("Hello", null, Thing("Hello")) { return }
println(first)
println(second)
println(third)

// Will print
ifLet("Hello", "A", 9) {
 (first, second, third) ->
 println(first)
 println(second)
 println(third)
}

// Won't print
ifLet("Hello", 9, null) {
 (first, second, third) ->
 println(first)
 println(second)
 println(third)
}
 */

// ------------------------------------------------------------

// Mixed types, all must not be null to calculate a new value

inline fun <T1: Any, T2: Any, R: Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2)->R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}
inline fun <T1: Any, T2: Any, T3: Any, R: Any> safeLet(p1: T1?, p2: T2?, p3: T3?, block: (T1, T2, T3)->R?): R? {
    return if (p1 != null && p2 != null && p3 != null) block(p1, p2, p3) else null
}
inline fun <T1: Any, T2: Any, T3: Any, T4: Any, R: Any> safeLet(p1: T1?, p2: T2?, p3: T3?, p4: T4?, block: (T1, T2, T3, T4)->R?): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null) block(p1, p2, p3, p4) else null
}
inline fun <T1: Any, T2: Any, T3: Any, T4: Any, T5: Any, R: Any> safeLet(p1: T1?, p2: T2?, p3: T3?, p4: T4?, p5: T5?, block: (T1, T2, T3, T4, T5)->R?): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null && p5 != null) block(p1, p2, p3, p4, p5) else null
}

/*
val risk = safeLet(person.name, person.age) { name, age ->
  // do something
}
 */

// Execute block of code when list has no null items

fun <T: Any, R: Any> Collection<T?>.whenAllNotNull(block: (List<T>)->R) {
    if (this.all { it != null }) {
        block(this.filterNotNull()) // or do unsafe cast to non null collectino
    }
}

fun <T: Any, R: Any> Collection<T?>.whenAnyNotNull(block: (List<T>)->R) {
    if (this.any { it != null }) {
        block(this.filterNotNull())
    }
}

/*
listOf("something", "else", "matters").whenAllNotNull {
    println(it.joinToString(" "))
} // output "something else matters"

listOf("something", null, "matters").whenAllNotNull {
    println(it.joinToString(" "))
} // no output

listOf("something", null, "matters").whenAnyNotNull {
    println(it.joinToString(" "))
} // output "something matters"
 */

// A slight change to have the function receive the list of items and do the same operations

fun <T: Any, R: Any> whenAllNotNull(vararg options: T?, block: (List<T>)->R) {
    if (options.all { it != null }) {
        block(options.filterNotNull()) // or do unsafe cast to non null collection
    }
}

fun <T: Any, R: Any> whenAnyNotNull(vararg options: T?, block: (List<T>)->R) {
    if (options.any { it != null }) {
        block(options.filterNotNull())
    }
}

/*
whenAllNotNull("something", "else", "matters") {
    println(it.joinToString(" "))
} // output "something else matters"
 */

// Use the first non-null item (Coalesce)

fun <T: Any> coalesce(vararg options: T?): T? = options.firstOrNull { it != null }
fun <T: Any> Collection<T?>.coalesce(): T? = this.firstOrNull { it != null }

/*
coalesce(null, "something", null, "matters")?.let {
    it.length
} // result is 9, length of "something"

listOf(null, "something", null, "matters").coalesce()?.let {
    it.length
}  // result is 9, length of "something"
 */

// --------------------------------------------------------------------

fun <T, U, R> Pair<T?, U?>.biLet(body: (T, U) -> R): R? {
    val first = first
    val second = second
    if (first != null && second != null) {
        return body(first, second)
    }
    return null
}

// --------------------------------------------------------------------

fun <T : Any> arrayIfNoNulls(vararg elements: T?): Array<T>? {
    if (null in elements) {
        return null
    }
    @Suppress("UNCHECKED_CAST")
    return elements as Array<T>
}

/*
fun example(first: String?, second: String?) {
    arrayIfNoNulls(first, second)?.let { (first, second) ->
        // Do something if each element is not null
    }
}
 */

// If you already have an array you can create a takeIfNoNulls function (inspired by takeIf and requireNoNulls)

fun <T : Any> Array<T?>.takeIfNoNulls(): Array<T>? {
    if (null in this) {
        return null
    }
    @Suppress("UNCHECKED_CAST")
    return this as Array<T>
}

/*
array?.takeIfNoNulls()?.let { (first, second) ->
    // Do something if each element is not null
}
 */

// -----------------------------------------------------------------


