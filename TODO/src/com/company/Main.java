package com.company;

import java.util.Scanner;

public class Main {

    private static Scanner in = new Scanner(System.in);
    private static TODOFunction myTodoList = new TODOFunction();


    public static void main(String[] args) {
	// write your code here


        int command = 0;
        printCommand();
        boolean exit = false;

        while(!exit)
        {
            System.out.println("enter your command/choice: ");

            command = in.nextInt();
            in.nextLine();


            switch(command){
                case 0:
                    printCommand();
                    break;
                case 1:
                    myTodoList.printTodoList();
                    break;
                case 2:
                    additem();
                    break;
                case 3:
                    updateItem();
                    break;
                case 4:
                    removeitem();
                    break;
                case 5:
                    searchitem();
                    break;
                case 6:
                    exit = true;
                    break;

                default:
                    System.out.println("please pick only from the given list");

            }

        }
    }
    public static void printCommand(){
        System.out.println("Press 0: To print instructions");
        System.out.println("Press 1: To print All List");
        System.out.println("Press 2: To Add List In Todo");
        System.out.println("Press 3: To Modify item in Todo");
        System.out.println("Press 4: To remove item from Todo");
        System.out.println("Press 5: To search an item from Todo");
        System.out.println("Press 6: To exit the App");
    }

    public static void additem(){
        System.out.println("enter item to be added in Todo List");
        myTodoList.addItem(in.nextLine());
    }
    public static void updateItem()
    {
        System.out.println("enter the item number");
       int index = in.nextInt();
       in.nextLine();
        System.out.println("enter new item to be added");
        String ni = in.nextLine();
        myTodoList.updateTodo(index-1, ni);

    }
    public static void removeitem(){
        System.out.println("enter the item number to be removed");
        int index = in.nextInt();
        in.nextLine();
        myTodoList.removeItem(index-1);
    }

    public static void searchitem(){
        System.out.println("enter the a string to be searched");
        String Search = in.nextLine();


        if(myTodoList.finditem(Search) == null){
            System.out.println("Item was not found in the ToDoList");
        }
        else{
            System.out.println(Search + " was found on your list at the position " + myTodoList.finditem(Search));
        }
    }

}
