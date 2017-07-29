import java.util.*;
/**
 * This class is for supplier.
 * Users can create a new supplier in this class.
 * Also it's available for get and set options.
 * 
 * @author (Luyang Ye) 
 * @version (21/10/2015)
 */
public class Supplier
{
    private String supplierName;
    private int phoneNumber;
    private Item item;
    private ItemCollection itemList;
    /**
     * Constructor for object of class Supplier by default values
     */
    public Supplier()
    {
       supplierName = "null";
       phoneNumber = 0;
       itemList = new ItemCollection();
    }
    /**
     * Constructor for object of supplier with parameters
     * @param Supplier's name
     * @param Supplier's phone number
     * @param ArrayList for Supplier's items
     */
    public Supplier(String name, int number, ItemCollection temp)
    {
        supplierName = name;
        phoneNumber = number;
        itemList = new ItemCollection();//create a new item list and send the items into new list
        for(Item index:temp.getList())
        {
            itemList.addItem(index);
        }
    }
    /**
     * Return supplier's name
     * @return name
     */
    public String getName()
    {
        return this.supplierName;
    }
    /**
     * Return supplier's phone number
     * @return phone number
     */
    public int getNumber()
    {
        return this.phoneNumber;
    }
    /**
     * Return supplier's item list
     * @return Arraylist of item list
     */
    public ArrayList<Item> getSupplyList()
    {
        return this.itemList.getList();
    }
    /**
     * Set supplier's name
     * @param name
     */
    public void setName(String name)
    {
        this.supplierName = name;
    }
    /**
     * Set supplier's phone number
     * @param phone number
     */
    public void setNumber(int number)
    {
        this.phoneNumber = number;
    }
    /**
     * Add an new item
     * @param Item type
     */
    public void addItem(Item item)
    {
        this.itemList.addItem(item);
    }
    /**
     * Search item's position in this supplier's item list
     * @return search result
     */
    public int searchItemPosition(int id)
    {
        int num = 0;
        for(Item index:this.itemList.getList())
        {
            if(index.getId() == id)
                break;    
            num++;
        }
        return num;
    }
    /**
     * Search item's name in this supplier's item'list
     * @return search result
     */
    public ArrayList<Item> searchItem(String name)
    {
        ArrayList<Item> searchList = new ArrayList<Item>();
        for(Item index:this.itemList.getList())
        {
            String lowername = name.toLowerCase();
            if(index.getName().contains(lowername))
                searchList.add(index);
        }
        return searchList;
    }
    
}
