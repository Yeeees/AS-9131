import java.util.*;
/**
 * It's a class for supplier list.
 * This class can creat a object of arraylist for supplier.
 * Users can search and add new supplier in this list.
 * 
 * @author (Luyang Ye) 
 * @version (21/10/2015)
 */
public class SupplierList
{
    private ArrayList<Supplier> supplierList;
    /**
     * Constructor for objects of class Supplier List
     */
    public SupplierList()
    {
        supplierList = new ArrayList<Supplier>();
    }
    /**
     * Add a new supplier into the list
     * @param A new supplier
     */
    public void addSupplier(Supplier supplier)
    {
        supplierList.add(supplier);
    }
    /**
     * Return this arraylist of supplier list
     * @return ArrayList of supplier
     */
    public ArrayList<Supplier> getList()
    {
        return supplierList;
    }
    /**
     * Search a name in supplier list
     * @param supplier's name
     * @return ArrayList of search result 
     */
    public ArrayList<Supplier> searchName(String name)
    {
        ArrayList<Supplier> searchList = new ArrayList<Supplier>();
        String lowername = name.toLowerCase();        
        for(Supplier index:supplierList)
        {
            if(index.getName().toLowerCase().contains(lowername))//ignore upper or lower case
                searchList.add(index);
        }
        return searchList;
    }
    /**
     * Search a phone number in supplier list
     * @param supplier's phone number
     * @return ArrayList of search result 
     */
    public ArrayList<Supplier> searchPhone(int phone)
    {
        ArrayList<Supplier> searchList = new ArrayList<Supplier>();        
        for(Supplier index:supplierList)
        {
            if(index.getNumber() == phone)
                searchList.add(index);
        }
        return searchList;
    }
    /**
     * Search a item in supplier list
     * @param item's name
     * @return ArrayList of search result 
     */
    public ArrayList<Supplier> searchItem(String item)
    {
        ArrayList<Supplier> searchList = new ArrayList<Supplier>();        
        for(Supplier index:supplierList)
        {
            if(index.searchItem(item).size() != 0)
                searchList.add(index);
        }
        return searchList;
    }
    /**
     * Search the position of supplier in supplier list
     * @param supplier's phone number
     * @return integer of position number
     */
    public int searchPosition(int phone)
    {
        int num = 0;
        for(Supplier index:supplierList)
        {
            if(index.getNumber() == phone)
                break;    
            num++;
        }
        return num;
    }
    /**
     * Clean this Arraylist
     */
    public void clean()
    {
        supplierList.clear();
    }
    
    
}
