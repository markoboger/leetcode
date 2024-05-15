object Solution {

    def twoSum(nums: List[Int], target: Int): List[Int] =
        val res = for (
          i <- 0 until numbers.length;
          j <- i + 1 until numbers.length
        ) yield {
            if numbers(i) + numbers(j) == target then (i, j) else ()
        }
}

val numbers = Array(2, 7, 11, 15)
val target  = 9
