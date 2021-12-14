//Given an array of integers nums which is sorted in ascending order, and an integer target,
//        write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
//        You must write an algorithm with O(log n) runtime complexity.
//
//        Constraints:
//
//        1 <= nums.length <= 104
//        -104 < nums[i], target < 104
//        All the integers in nums are unique.
//        nums is sorted in ascending order.

package binarySearch;

import java.util.Arrays;
import java.util.Objects;

public class BinarySearchSolution {
    public int search(final int[] nums, int target) {
        assert Arrays.equals(nums, sort(nums));
        assert nums.length > 0;
        return s(nums, 0, nums.length-1, target);
    }

    private int s(final int[] nums, int left, int right, int target) {
        if(right < left) {
            return -1;
        }
        if(left == right) {
            if(nums[left] == target) {
                return left;
            }
            return -1;
        }

        if((right - left) == 1) {
            if(nums[left] == target) {
                return left;
            } else if(nums[right] == target) {
                return right;
            } else {
                return -1;
            }
        }

        if((right - left) % 2 == 0) {
            int middle = (right + left) / 2;
            int inTheMiddle = nums[middle];
            int compare = Integer.compare(target, inTheMiddle);
            if(compare == 0) {
                return middle;
            } else  if(compare < 0) { //compare < 0 - so we are moving to left side
                return s(nums, left, middle - 1, target);
            } else { //compare > 0
                return s(nums, middle + 1, right, target);
            }
        }

        //last case when situation is even:
        //0 1 2  3 ==> (3 - 0) % 2 == 1 ( 3/ 2 == 1.5)
        //|      |
        //V    _ V
        // x x x x

        int middle = (right + 1 + left) / 2;
        int inTheMiddle = nums[middle];
        int compare = Integer.compare(target, inTheMiddle );
        if(compare == 0) {
            return middle;
        } else  if(compare < 0) {
            return s(nums, left, middle - 1, target);
        } else { //compare > 0
            return s(nums, middle + 1, right, target);
        }
    }

    //used only in test
    private static int[] sort(int[] array) {
        int[] copy = Arrays.copyOf(array, array.length);
        Arrays.sort(copy);
        return copy;
    }
}


// Test classes

package binarySearch;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BinarySearchSolutionTest {
    @Test
    void cornerCase1El() {
        int search = new BinarySearchSolution().search(new int[] {1}, 1);
        assertEquals(0, search);
    }

    @Test
    void cornerCase1ENotfound() {
        int search = new BinarySearchSolution().search(new int[] {1}, 100);
        assertEquals(-1, search);
    }

    @Test
    void corner2elements() {
        int search = new BinarySearchSolution().search(new int[] {1,100}, 100);
        assertEquals(1, search);
    }

    @Test
    void corner2elements_left() {
        int search = new BinarySearchSolution().search(new int[] {3,100}, 3);
        assertEquals(0, search);
    }

    @Test
    void corner2elements_notfound() {
        int search = new BinarySearchSolution().search(new int[] {1,100}, -20);
        assertEquals(-1, search);
    }

    @Test
    void corner2elements_notfound2() {
        int search = new BinarySearchSolution().search(new int[] {1,100}, 50);
        assertEquals(-1, search);
    }

    @Test
    void corner2elements_notfound3() {
        int search = new BinarySearchSolution().search(new int[] {1,100}, 150);
        assertEquals(-1, search);
    }

    @Test
    void test_001() {
        int search = new BinarySearchSolution().search(new int[] {1,50, 51, 100}, 150);
        assertEquals(-1, search);
    }

    @Test
    void test_002() {
        int search = new BinarySearchSolution().search(new int[] {1,50, 51, 100, 150}, 150);
        assertEquals(4, search);
    }

    @Test
    void test_003() {
        int search = new BinarySearchSolution().search(new int[] {1,50, 51, 100, 150}, 51);
        assertEquals(2, search);
    }

    @Test
    void bigArray() {
        int[] array = IntStream.rangeClosed(0, 1000).toArray();
        int search = new BinarySearchSolution().search(array, 510);
        assertEquals(510, search);
    }

    @Test
    void name() {
        log.info("{}", 2%2);
        log.info("{}", 20%2);
        log.info("{}", 21%2);
    }
}
