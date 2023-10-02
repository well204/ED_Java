public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        list.add(3,4);
        list.add(4, 5);
        list.add(5,4);
        list.add(6,7);

        //list.showArray();
        System.out.println (list.findLastOcurreence(4));
    } 
}

