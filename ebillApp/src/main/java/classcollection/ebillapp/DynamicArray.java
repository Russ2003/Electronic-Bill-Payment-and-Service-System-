package classcollection.ebillapp;


public class DynamicArray {
    public int[] array;
    public int count;
    private int size;

    public DynamicArray() {
        array = new int[1];
        count = 0;
        size = 1;
    }
    public void add(int data)
    {

        if (count == size) {
            growSize();
        }
        array[count] = data;
        count++;
    }


    public void growSize()
    {

        int temp[] = null;
        if (count == size) {


            temp = new int[size * 2];
            {
                for (int i = 0; i < size; i++) {
                    temp[i] = array[i];
                }
            }
        }

        array = temp;

        size = size * 2;
    }


}
