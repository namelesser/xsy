 class Arr4 {
    public static void main(String[] agrs){
        int[][] arr=new int[3][];
        arr[0]=new int[]{3,5,8};
        arr[1]=new int[]{12,9};
        arr[2]=new int[]{7,0,6,4};
        int he=0;
        for(int a=0;a<arr.length;a++){
            for(int b=0;b<arr[a].length;b++){
                he+=arr[a][b];
            }
        }
        System.out.println(he);
    }
}