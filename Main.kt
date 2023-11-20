fun main() {
    println("Enter a number (up to six digits): ")
    val input = readLine()

    try {
        val number = input?.toInt()
        if (number != null && number in 0..999999) {
            val result = convertToWords(number)
            println("Number in words: $result")
        } else {
            println("Please enter a valid number between 0 and 999999.")
        }
    } catch (e: NumberFormatException) {
        println("Invalid input. Please enter a valid number.")
    }
}

fun convertToWords(number: Int): String {
    val units = arrayOf("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val teens = arrayOf("ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    val tens = arrayOf("", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")

    if (number == 0) {
        return "zero"
    }

    var result = ""

    val hundredThousands = number / 100000
    val remainderAfterHundredThousands = number % 100000
    val tenThousands = remainderAfterHundredThousands / 10000
    val remainderAfterTenThousands = remainderAfterHundredThousands % 10000
    val thousands = remainderAfterTenThousands / 1000
    val remainderAfterThousands = remainderAfterTenThousands % 1000
    val hundreds = remainderAfterThousands / 100
    val remainder = remainderAfterThousands % 100
    val ten = remainder / 10
    val unit = remainder % 10

    if (hundredThousands > 0) {
        result += "${units[hundredThousands]} hundred"
        if (remainderAfterHundredThousands > 0) {
            result += if (remainderAfterTenThousands > 0 || remainderAfterThousands > 0) " and " else " "
        }
    }

    if (tenThousands > 0) {
        result += "${tens[tenThousands]}"
        if (thousands > 0 || remainderAfterThousands > 0) {
            result += if (remainderAfterThousands > 0) " " else " and "
        }
    }

    if (thousands > 0) {
        result += "${units[thousands]} thousand"
        if (remainderAfterThousands > 0) {
            result += if (hundreds > 0 || remainder > 0) " " else ""
        }
    }

    if (hundreds > 0) {
        result += "${units[hundreds]} hundred"
        if (remainder > 0) {
            result += " and "
        }
    }

    if (remainder in 10..19) {
        result += teens[remainder - 10]
    } else {
        if (ten > 0) {
            result += tens[ten]
            if (unit > 0) {
                result += " "
            }
        }

        if (unit > 0) {
            result += units[unit]
        }
    }

    return result.trim()
}
