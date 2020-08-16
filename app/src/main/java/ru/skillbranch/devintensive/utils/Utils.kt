package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{
        val parts : List<String>? = fullName?.split(" ")

        var firstName =parts?.getOrNull(0)
        var lastName =parts?.getOrNull(1)
        if(firstName?.length == 0) firstName = null
        if(lastName?.length == 0) lastName = null
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var result : String = ""
//        val parts : List<String>? = fullName?.split(" ")
//        var firstName =parts?.getOrNull(0)
//        var lastName =parts?.getOrNull(1)

        for(name in payload?.toLowerCase()?.split(" ")) {
            var res : String = ""
            for (lit in name?.toList()) {
                val rs = when (lit) {
                    'а' -> "a"
                    'б' -> "b"
                    'в' -> "v"
                    'г' -> "g"
                    'д' -> "d"
                    'е' -> "e"
                    'ё' -> "e"
                    'ж' -> "zh"
                    'з' -> "z"
                    'и' -> "i"
                    'й' -> "i"
                    'к' -> "k"
                    'л' -> "l"
                    'м' -> "m"
                    'н' -> "n"
                    'о' -> "o"
                    'п' -> "p"
                    'р' -> "r"
                    'с' -> "s"
                    'т' -> "t"
                    'у' -> "u"
                    'ф' -> "f"
                    'х' -> "h"
                    'ц' -> "c"
                    'ч' -> "ch"
                    'ш' -> "sh"
                    'щ' -> "sh'"
                    'ъ' -> ""
                    'ы' -> "i"
                    'ь' -> ""
                    'э' -> "e"
                    'ю' -> "yu"
                    'я' -> "ya"
//                    ' ' -> divider
                    else -> lit.toString()
                }
                res += rs
            }
            if(result.length != 0) result += divider
            result +=  res.capitalize()
        }

        return result ?: ""
    }

//    Реализуй метод Utils.transliteration(payload divider)
//    принимающий в качестве аргумента строку (divider по умолчанию " ")
//    и возвращающий преобразованную строку из латинских символов, словарь
//    символов соответствия алфовитов доступен в ресурсах к заданию
//    Пример:
//    Utils.transliteration("Женя Стереотипов") //Zhenya Stereotipov
//    Utils.transliteration("Amazing Петр","_") //Amazing_Petr

    fun toInitials(firstName: String?, lastName: String?): String? {
        val fI : String? = firstName?.getOrNull(0)?.toUpperCase()?.toString()?.trim() ?:""
        val fL : String? = lastName?.getOrNull(0)?.toUpperCase()?.toString()?.trim() ?:""
        var result  :String? =  fI + fL
        if (result?.length ==0) result = null
        return result
    }

}