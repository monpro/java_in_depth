package com.monpro.thread.sorting;

public class MergeSort {

    public static void merge(int[] arr, int start, int mid, int high){
        int[] temp = new int[high - start + 1];

        int i = start, j = mid + 1;
        int index = 0;

        while(i <= mid && j <= high){
            if(arr[i] < arr[j]){
                temp[index++] = arr[i++];
            }else{
                temp[index++] = arr[j++];
            }
        }

        while(i <= mid){
            temp[index++] = arr[i++];
        }

        while(j <= high){
            temp[index++] = arr[j++];
        }

        for(int k = 0; k < temp.length; k++){
            arr[start + k] = temp[k];
        }
    }

    public static void mergeSort(int[] arr, int low, int high){
        int mid = low + (high - low) / 2;
        if(low < high){
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,1,4,5,6,7,8};
        mergeSort(arr, 0, arr.length -1);
        for(int i : arr){
            System.out.println(i);
        }
    }
}
