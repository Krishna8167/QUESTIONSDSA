public class Sort {
  public static void main(string args) {
    int[] arr = {1, 2, 4, 5, 3};
    mergeSort(arr);
  }

public int[] mergeSort(int[] arr) {

  if(arr.length == 1) {
    return;
  }
  
  int mid = arr.length / 2;

  int[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
  int[] right = mergeSort(Arrays.copyOfRange(arr, mid,arr.length));

  mix(left, right);
}

public int[] mix(int[] l, int[] r) {
  int[] mix = new int[l.length + r.length)];

  int i, j, k = 0;

  while(i < l.length && j < r.length) {

    if(l[i] < r[j]) {
      mix[k] = l[i];
      i++;
    } else {
      mix[k] = r[j];
      j++;
    }
    k++;
  }

  // It may be possible that 1 of the array is not complete , so that next process ensures the merger completely.
  //Copy the remianing elements that are not not tbe compared.
  
  while(i < l.length) {
    l[i] = mix[k];
    i++;
    k++;
  }

  while(j < r.length) {
    r[j] = mix[k];
    j++;
    k++;
  }

  return mix;
  }
}
