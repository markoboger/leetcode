object Solution {

    def twoSum(nums: List[Int], target: Int): List[Int] =
        val res = for (
          i <- 0 until numbers.length;
          j <- i + 1 until numbers.length
        ) yield {
            if numbers(i) + numbers(j) == target then (i, j) else (0, 0)
        }
        res.filter(_ != (0, 0)).head.toList
}

val numbers = List(2, 7, 11, 15)
val target  = 9

Solution.twoSum(numbers.toList, target)
//Solution.twoSum(List(2, 4, 3), 6)
