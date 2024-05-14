//Source: https://leetcode.com/problems/remove-element/

Array(3, 2, 2, 3).filter(_ != 3).mkString("(", ", ", ")")
Array(3, 2, 2, 3).filterNot(_ == 3).mkString("(", ", ", ")")
//Solution 1
object Solution1 {
    def removeElement(numbers: Array[Int], toBeRemoved: Int): Int = {
        val filtered = numbers.filter(_ != toBeRemoved)
        for (i <- 0 until filtered.length)
            numbers(i) = filtered(i)
        filtered.length
    }
}
Solution1.removeElement(Array(3, 2, 2, 3), 3)

//Solution 2
object Solution2 {
    def removeElement(nums: Array[Int], `val`: Int): Int = {
        nums.filter(_ != `val`).length
    }
}

Solution2.removeElement(Array(3, 2, 2, 3), 3)
