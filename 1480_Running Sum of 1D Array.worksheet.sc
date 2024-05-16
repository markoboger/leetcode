// Source: https://leetcode.com/problems/running-sum-of-1d-array/

// Solution 1

val nums = Array(1, 2, 3, 4)
nums(0) = nums(0)
nums(1) = nums(0) + nums(1)
nums(2) = nums(1) + nums(2)
nums(3) = nums(2) + nums(3)
nums.toList

for i <- 1 until nums.length
do nums(i) = nums(i - 1) + nums(i)
nums.toList

object Solution1 {
    def runningSum(nums: Array[Int]): Array[Int] =
        for i <- 1 until nums.length
        do nums(i) = nums(i - 1) + nums(i)
        nums
}
Solution1.runningSum(Array(1, 2, 3, 4)).toList
Solution1.runningSum(Array(1, 1, 1, 1, 1)).toList
Solution1.runningSum(Array(3, 1, 2, 10, 1)).toList

// Solution 2

val nums2 = List(1, 2, 3, 4)
nums2.foldLeft(0)(_ + _)
nums2.scanLeft(0)(_ + _).drop(1)
nums2.scanLeft(0)(_ + _).tail

val nums3 = Array(1, 2, 3, 4)
nums3.scanLeft(0)(_ + _).tail
nums3.scanLeft(0)(_ + _).tail.toList

object Solution {
    def runningSum(nums: Array[Int]): Array[Int] = {
        nums.scanLeft(0)(_ + _).tail
    }
}

Solution.runningSum(Array(1, 2, 3, 4)).toList
Solution.runningSum(Array(1, 1, 1, 1, 1)).toList
Solution.runningSum(Array(3, 1, 2, 10, 1)).toList
