//Source: https://leetcode.com/problems/string-to-integer-atoi/

val c = '9'
c.isDigit
c.toInt
c.toString.toInt

val str1 = "42"
str1.toInt
val str2 = "987654321987654321".toList
Int.MaxValue
Int.MinValue

str1.toList.take(1).mkString.toInt

val str3 = "  +0047b7"
str3.trim.toList match {
    case Nil         => 0
    case '-' :: rest => -1 * rest.takeWhile(_.isDigit).mkString.toInt
    case '+' :: rest => rest.takeWhile(_.isDigit).mkString.toInt
    case rest        => rest.takeWhile(_.isDigit).mkString.toInt
}

object Solution {
    def myAtoi(s: String): Int = {
        val b: BigInt = s.trim.toList match {
            case Nil         => 0
            case '-' :: rest => -1 * rest.takeWhile(_.isDigit).mkString.toInt
            case '+' :: rest => rest.takeWhile(_.isDigit).mkString.toInt
            case rest =>
                rest.takeWhile(_.isDigit).mkString.toInt
        }
        if b > Int.MaxValue then Int.MaxValue
        else if b < Int.MinValue then Int.MinValue
        else b.toInt
    }
}

Solution.myAtoi("42")
Solution.myAtoi("   -42")
Solution.myAtoi("4193 with words")
//Solution.myAtoi("987654321987654321")
