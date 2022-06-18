import java.util.Scanner;

public class comparison {
	
	//This function takes the array and two indexes and swap them with each other
	public static void swap(int elems[], int i, int j) {
		int tempElement = elems[i];
		elems[i] = elems[j];
		elems[j] = tempElement;
	}

	//Implementation of the algorithm for shuffling permutations
	public static void permutations(int a[], int no) {
		for(int j =  no - 1; j > 0; j--) {
			double U = Math.random();
			int k = (int)Math.floor((j+1)*U);
			swap(a, j, k);
		}
	}
	
	//This function takes last index of the given array as pivot
	//It then places the pivot at its correct position
	//All smaller elements are placed to the left greater elements to right of corrected pivot
	public static int partition(int a[], int left, int right){
		//pivot element
		int p = a[right];
		 //Index of the smaller element which will indicate the right position of pivot at any iteration
		int i = left - 1;
		for (int j = left;j < right;j++) {
			//If the element is smaller than the pivot
			if (a[j] < p) {
				//Index of smaller element is increamented
				i++;
				swap(a, i, j);
			}
		}
		swap(a, i + 1, right);
		return i + 1;
	}

	//The function that implements QuickSort
	public static int quickSort(int elems[], int leftElement, int rightElement){
		//Counter for number of comparisons
		int comparisons = 0;
		int[] s = new int[150];

		int topElement = -1;

		s[++topElement] = leftElement;
		s[++topElement] = rightElement;

		while (topElement >= 0) {
			rightElement = s[topElement--];
			leftElement = s[topElement--];
			
			comparisons += rightElement - leftElement;

			int p = partition(elems, leftElement, rightElement);
			if (p - 1 > leftElement) {
				s[++topElement] = leftElement;
				s[++topElement] = p - 1;
			}

			if (p + 1 < rightElement) {
				s[++topElement] = p + 1;
				s[++topElement] = rightElement;
			}
		}
		return comparisons;
	}
	
	//The function that implements merge sort
	public static int mergeSort(int[] elems){
		//Counter for comparisons 
		int comparisons = 0;
        for (int i = 1; i <= elems.length - 1; i = i * 2){
            for (int leftElement = 0; leftElement < elems.length - 1; leftElement += 2 * i){
                int rightElement = Math.min(leftElement + 2 * i - 1, elems.length - 1);
                int midElement = leftElement + i - 1;
                if (midElement > rightElement)
                    midElement = rightElement;

                comparisons += merge(elems, leftElement, midElement, rightElement);
            }
        }
        return comparisons;
    }

	//Helper function to merge two arrays
    public static int merge(int[] elems, int lowElement, int midElement, int highElement){
    	//Counter for comparisons
    	int comparisons = 0;
        int[] tempElement = new int[highElement - lowElement + 1];

        int left = lowElement;
        int right = midElement + 1;
        int tempIndex = 0;

        while ((left <= midElement) && (right <= highElement)){
        	comparisons++;
            if (elems[left] < elems[right]){
                tempElement[tempIndex] = elems[left];
                left++;
            }
            else{
                tempElement[tempIndex] = elems[right];
                right++;
            }
            tempIndex++;
        }

        if (left <= midElement){
            while (left <= midElement){
                tempElement[tempIndex] = elems[left];
                left++;
                tempIndex++;
            }
        }
        if (right <= highElement){
            while (right <= highElement){
                tempElement[tempIndex] = elems[right];
                right++;
                tempIndex++;
            }
        }

        for (int i = 0; i < tempElement.length; i++){
        	elems[lowElement + i] = tempElement[i];
        }
        return comparisons;
    }
    
    public static void main(String[] args) {
    	int cmpByQuickSort = 0, cmpByMergeSort = 0;
    	Scanner scan = new Scanner(System.in);
    	System.out.print("Enter an integer: ");
    	int n = scan.nextInt();
    	int arr[] = new int[n];
    	for(int i = 0;i<n;i++) {
    		arr[i]= i+1;
    	}
    	System.out.print("Enter no of trails: ");
    	int trails = scan.nextInt();
    	
    	for(int i = 1; i<=trails;i++) {
	    	permutations(arr, n);
	    	int qArr[] = arr.clone();
	    	int mArr[] = arr.clone();
	    	cmpByQuickSort += quickSort(qArr, 0, n-1);
	    	cmpByMergeSort += mergeSort(mArr);
    	}

    	double avgCmpQuickSort = cmpByQuickSort / (double)trails;
    	double avgCmpMergeSort = cmpByMergeSort / (double)trails;

    	System.out.println("Average No. of Comparisons by Quick Sort: "+avgCmpQuickSort);
    	System.out.println("Average No. of Comparisons by Merge Sort: "+avgCmpMergeSort);
    	
    }
}
