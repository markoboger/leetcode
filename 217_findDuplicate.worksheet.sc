//Solution f1
//  Required features: Array, Set, distinct, size

object Solution {
    def containsDuplicate2(nums: Array[Int]): Boolean = {
        nums.length != nums.toSet.size
    }
    def containsDuplicate(nums: Array[Int]): Boolean = {
        nums.length != nums.distinct.size
    }
}
Solution.containsDuplicate(Array(1, 2, 3, 1))
Solution.containsDuplicate(Array(1, 2, 3, 4))
Solution.containsDuplicate(Array(1, 1, 1, 3, 3, 4, 3, 2, 4, 2))

Array(1, 1, 1, 3, 3, 4, 3, 2, 4, 2).distinct.mkString("(", ", ", ")")

// Solution 2
//   Refactored to one line and using indentation
object Solution2:
    def containsDuplicate(nums: Array[Int]): Boolean = nums.length != nums.distinct.size

Solution2.containsDuplicate(Array(1, 2, 3, 1))
Solution2.containsDuplicate(Array(1, 2, 3, 4))
Solution2.containsDuplicate(Array(1, 1, 1, 3, 3, 4, 3, 2, 4, 2))

// Solution 3
//   Refactored to global namespace
def containsDuplicate(nums: Array[Int]): Boolean = nums.length != nums.distinct.size
containsDuplicate(Array(1, 2, 3, 1))

// Solution in Python
// class Solution:
//     def containsDuplicate(self, nums: List[int]) -> bool:
//         return len(set(nums))!=len(nums)

//Solution in Kotlin
// class Solution {
//     fun containsDuplicate(nums: IntArray): Boolean {
//         return nums.toSet().size != nums.size
//     }
// }

// Solution in Java
// class Solution {
//     public boolean containsDuplicate(int[] nums) {
//         return nums.length != Arrays.stream(nums).distinct().count();
//     }
// }
