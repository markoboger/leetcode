//Source: https://leetcode.com/problems/minimum-number-game/

var arr       = Array(5, 4, 2, 3)
var sortedArr = arr.sorted
sortedArr.grouped(2).next().toList
sortedArr.grouped(2).map(_.reverse).flatten.toArray.toList

object Solution {
    def numberGame(nums: Array[Int]): Array[Int] = {
        nums.sorted.grouped(2).map(_.reverse).flatten.toArray
    }
}

Solution.numberGame(Array(5, 4, 2, 3)).toList
