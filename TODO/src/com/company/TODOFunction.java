package com.company;

import java.util.ArrayList;

public class TODOFunction {
    private ArrayList<String> todoList = new ArrayList<String>();
//    Add List Item

    public void addItem(String item){
     todoList.add(item);
    }
    //remove list item
    public void removeItem(int index)
    {
        String myitem = todoList.get(index);
        todoList.remove(index);
    }
    //print the entire list
    public void printTodoList(){
        System.out.println("TodoList consists of:  " + todoList.size()+" "+"items");

        for(int i = 0;i<todoList.size();i++)
        {
            System.out.println("item at position "+(i+1)+" "+"is "+todoList.get(i));
        }
    }



    // update the list
    public void updateTodo(int index,String list){

        todoList.set(index, list);
        System.out.println("updation completed at position: "+ index + 1);
    }

    //search feature for user
    public String finditem(String searchitem){

        int index = todoList.indexOf(searchitem);


        if (index == (-1))
        {
            return null;
        }
        else
        {
            return Integer.toString(index);
        }
    }
}
