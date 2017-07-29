import java.util.*;
/**
 *This is a class for item collection. It can create a arraylist for the storage.
 *This class can add a item and get the arraylist.
 *And also for search options.
 *
 *@author (Luyang Ye)
 *@version (15/10/2015)
 */
public class ItemCollection
{
    private ArrayList<Item> itemList;
    /**
     * Constructor for itemlist.
     */
    public ItemCollection()
    {
        itemList = new ArrayList<Item>();
    }
    /**
     * Add a item to item list 
     */
    public void addItem(Item item)
    {
        itemList.add(item);
    }
    /**
     * Return this item list
     * @return Arraylist of item 
     */
    public ArrayList<Item> getList()
    {
        return itemList;
    }
    /**
     * Search name in this item list
     * @return Arraylist of item for search result
     */
    public ArrayList<Item> searchName(String name)
    {
        ArrayList<Item> searchList = new ArrayList<Item>();
        for(Item index:itemList)
        {
            String lowername = name.toLowerCase();
            if(index.getName().toLowerCase().contains(lowername))//to ignore upper and lower case
                searchList.add(index);
        }
        return searchList;
    }
    /**
     * Search level that equals to a number
     * @param level
     * @return Arraylist of item for search result
     */
    public ArrayList<Item> searchLvlEqual(int lvl)
    {
        ArrayList<Item> searchList = new ArrayList<Item>();
        for(Item index:itemList)
        {
            if(index.getLvl() == lvl)
                searchList.add(index);
        }
        return searchList;
    }
    /**
     * Search level that greater than a number
     * @param Level
     * @return Arraylist of item for search result
     */
    public ArrayList<Item> searchLvlGreater(int lvl)
    {
        ArrayList<Item> searchList = new ArrayList<Item>();
        for(Item index:itemList)
        {
            if(index.getLvl() > lvl)
                searchList.add(index);
        }
        return searchList;
    }
    /**
     * Search level that lower than a number
     * @param level
     * @return Arraylist of item for search result
     */
    public ArrayList<Item> searchLvlLower(int lvl)
    {
        ArrayList<Item> searchList = new ArrayList<Item>();
        for(Item index:itemList)
        {
            if(index.getLvl() < lvl)
                searchList.add(index);
        }
        return searchList;
    }
    /**
     * Search for discount
     * @param discount of item
     * @return Arraylist of item for search result
     */
    public ArrayList<Item> searchDiscount(int discount)
    {
        ArrayList<Item> searchList = new ArrayList<Item>();
        for(Item index:itemList)
        {
            if(index.getDiscount() == discount)
                searchList.add(index);
        }
        return searchList;
    }
    /**
     * Find out the position of an item in list by id
     * @param item's id
     * @return number represent place
     */
    public int searchPosition(int id)
    {
        int num = 0;
        for(Item index:itemList)
        {
            if(index.getId() == id)
                break;    
            num++;
        }
        return num;
    }
    /**
     * Clean this list
     */
    public void clean()
    {
        itemList.clear();
    }
    
    
}