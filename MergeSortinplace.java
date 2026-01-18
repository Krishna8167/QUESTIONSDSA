public class Sort {
  public static void main(string args) {
    int[] arr = {1, 2, 4, 5, 3};
    int ans = mergeSort(arr, 0, arr.length);
    System.out,println(Arrays.toString(ans));
    
  }

public void mergeSort(int[] arr, int s, int e) {

  if(e - s == 1) {
    return;
  }
  
  int mid = (s + e) / 2;

  mergeSort(arr, s, mid);
  mergeSort(arr, mid, e);

  merge(arr, s, mid, e);

}

public void merge(int[] arr, int s, int m, int e) {
  int[] mix = new int[e - s];

  int i = s;
  int j = m;
  int k = 0;

  while(i < m && j < e) {
    if(arr[i] < arr[j]) {
      mix[k] = arr[i];
      i++;
    } else {
      mix[k] = arr[j];
      j++;
    }
    k++;
  }

  // It may be possible that 1 of the array is not complete , so that next process ensures the merger completely.
  //Copy the remianing elements that are not not tbe compared.
  
  while(i < m) {
    mix[k] = arr[i];
    i++;
    k++;
  }

  while(j < e) {
     mix[k] = arr[j];
    j++;
    k++;
  }

  for (int l = 0; i< mix.length; l++) {
    arr[s+l] = mix[k];
  }
}
