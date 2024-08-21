package assignment_pro;

import java.util.Scanner;

public class Menu {
    private String[] menuItems;
    private int count = 0;
    private int size;

    public Menu(int size) {
        if (size < 1) {
            size = 10;
        }
        this.size = size;
        this.menuItems = new String[this.size];
    }

    public void addMenuItem(String menuItem) {
        if (this.count < this.size) {
            menuItems[count] = menuItem;
            count++;
        }
    }

//    public int getChoice() {
//        int choice = 0;
//        if (count > 0) {
//            for (int i = 0; i < count; i++) {
//                System.out.println((i + 1) + ". " + menuItems[i]);
//            }
//        }
//        System.out.print("Please select an option: ");
//        Scanner sc = new Scanner(System.in);
//        if (sc.hasNextInt()) {
//            choice = sc.nextInt();
//        }
//        return choice;
//    }
    public void showMenu(){
        for(int i=0;i<size; i++){
            System.out.println(this.menuItems[i]);
        }
    }
}
