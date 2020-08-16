package ru.skillbranch.devintensive.extension

fun String.truncate(value:Int = 16):String?{
    return this.take(value) + if(this.trim().length>value) "..." else ""
}

fun String.stripHtml():String{
    return this.replace("\\<.*?>".toRegex(), "").replace("\\s+".toRegex()," ")
}
