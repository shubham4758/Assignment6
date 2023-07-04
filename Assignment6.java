// 1. Solution



import java.util.*;

public class PermutationReconstruction {
    public int[] reconstructPermutation(String s) {
        int n = s.length() + 1;
        int[] perm = new int[n];
        
        // Initialize the initial permutation [0, 1, 2, ..., n]
        for (int i = 0; i < n; i++) {
            perm[i] = i;
        }
        
        // Process the string 's' to reconstruct the permutation
        int left = 0, right = 0;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == 'I') {
                // If 'I' encountered, reverse the segment [left, right]
                reverseSegment(perm, left, right);
                // Move the left pointer to the right of the current segment
                left = i + 1;
            } else {
                // If 'D' encountered, move the right pointer to the right
                right = i + 1;
            }
        }
        
        // Reverse the last segment (if necessary)
        reverseSegment(perm, left, right);
        
        return perm;
    }
    
    // Helper function to reverse a segment of the permutation array
    private void reverseSegment(int[] perm, int left, int right) {
        while (left < right) {
            int temp = perm[left];
            perm[left] = perm[right];
            perm[right] = temp;
            left++;
            right--;
        }
    }
    
    // Helper function to print the permutation (for testing purposes)
    private void printPermutation(int[] perm) {
        for (int num : perm) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        PermutationReconstruction solution = new PermutationReconstruction();
        String s = "IDID";
        int[] perm = solution.reconstructPermutation(s);
        solution.printPermutation(perm);
    }
}



// 2. Solution


public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / n][mid % n];

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        int target = 3;

        SearchMatrix solution = new SearchMatrix();
        boolean found = solution.searchMatrix(matrix, target);
        System.out.println("Target found: " + found);
    }
}



// 3. Solution


public class ValidMountainArray {
    public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        int i = 0;

        // Step 1: Check if array length is at least 3
        if (n < 3) {
            return false;
        }

        // Step 2: Find the peak of the mountain (i.e., index where the array is strictly increasing)
        while (i < n - 1 && arr[i] < arr[i + 1]) {
            i++;
        }

        // Step 3: Check if the peak is neither the first nor the last element of the array
        if (i == 0 || i == n - 1) {
            return false;
        }

        // Step 4: Check if the elements to the right of the peak are in strictly decreasing order
        while (i < n - 1 && arr[i] > arr[i + 1]) {
            i++;
        }

        // Step 5: If all conditions are met, the array is a valid mountain array
        return i == n - 1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1};
        ValidMountainArray solution = new ValidMountainArray();
        boolean isValidMountain = solution.validMountainArray(arr);
        System.out.println("Is the array a valid mountain array? " + isValidMountain);
    }
}




// 4. Solution


import java.util.HashMap;

public class MaxEqualSubarrayLength {
    public int findMaxLength(int[] nums) {
        int maxLength = 0;
        int count = 0;
        HashMap<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, -1); // To handle the case where the first element is 0

        for (int i = 0; i < nums.length; i++) {
            count += nums[i] == 0 ? -1 : 1;

            if (countMap.containsKey(count)) {
                maxLength = Math.max(maxLength, i - countMap.get(count));
            } else {
                countMap.put(count, i);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1};
        MaxEqualSubarrayLength solution = new MaxEqualSubarrayLength();
        int maxLength = solution.findMaxLength(nums);
        System.out.println("Maximum length of contiguous subarray with an equal number of 0s and 1s: " + maxLength);
    }
}



// 5. Solution


import java.util.Arrays;

public class MinProductSum {
    public int minProductSum(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); // Sort nums1 in non-decreasing order
        Arrays.sort(nums2); // Sort nums2 in non-decreasing order

        int n = nums1.length;
        int minProductSum = 0;

        for (int i = 0; i < n; i++) {
            minProductSum += nums1[i] * nums2[n - 1 - i];
        }

        return minProductSum;
    }

    public static void main(String[] args) {
        int[] nums1 = {5, 3, 4, 2};
        int[] nums2 = {4, 2, 2, 5};
        MinProductSum solution = new MinProductSum();
        int minProduct = solution.minProductSum(nums1, nums2);
        System.out.println("Minimum product sum: " + minProduct);
    }
}



// 6. Solution


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoubledArray {
    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if (n % 2 != 0) {
            return new int[0]; // If the length of the changed array is odd, it cannot be a doubled array
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : changed) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> originalList = new ArrayList<>();
        for (int num : changed) {
            if (countMap.getOrDefault(num, 0) > 0) {
                int twiceNum = num * 2;
                if (countMap.getOrDefault(twiceNum, 0) > 0) {
                    originalList.add(num);
                    countMap.put(num, countMap.get(num) - 1);
                    countMap.put(twiceNum, countMap.get(twiceNum) - 1);
                } else {
                    return new int[0]; // If the doubled value doesn't exist or its count is 0, return empty array
                }
            }
        }

        int[] original = new int[originalList.size()];
        for (int i = 0; i < originalList.size(); i++) {
            original[i] = originalList.get(i);
        }
        return original;
    }

    public static void main(String[] args) {
        int[] changed = {1, 3, 4, 2, 6, 8};
        DoubledArray solution = new DoubledArray();
        int[] original = solution.findOriginalArray(changed);
        for (int num : original) {
            System.out.print(num + " ");
        }
    }
}


// 7. Solution


public class SpiralMatrix {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int num = 1;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;

        while (num <= n * n) {
            // Fill the top row from left to right
            for (int i = left; i <= right; i++) {
                matrix[top][i] = num++;
            }
            top++;

            // Fill the rightmost column from top to bottom
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++;
            }
            right--;

            // Fill the bottom row from right to left
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = num++;
            }
            bottom--;

            // Fill the leftmost column from bottom to top
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = num++;
            }
            left++;
        }

        return matrix;
    }

    public static void main(String[] args) {
        int n = 3;
        SpiralMatrix solution = new SpiralMatrix();
        int[][] matrix = solution.generateMatrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}



// 8. Solution



public class SparseMatrixMultiplication {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int m = mat1.length;
        int k = mat1[0].length;
        int n = mat2[0].length;

        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k; j++) {
                if (mat1[i][j] != 0) {
                    for (int l = 0; l < n; l++) {
                        result[i][l] += mat1[i][j] * mat2[j][l];
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] mat1 = {{1, 0, 0}, {-1, 0, 3}};
        int[][] mat2 = {{7, 0, 0}, {0, 0, 0}, {0, 0, 1}};

        SparseMatrixMultiplication solution = new SparseMatrixMultiplication();
        int[][] result = solution.multiply(mat1, mat2);

        for (int[] row : result) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}

