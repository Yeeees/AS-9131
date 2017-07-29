import java.util.*;
/**
 * This class is for item. 
 * In this class users can create a item or change the values of item's attributes.
 * In the program, discount is count automatically by rules.
 * @author (Luyang Ye)
 * @version (16/10/2015)
 */
public class Item
{
    private String itemName;
    private int itemId;
    private int itemCost;
    private int itemLvl;
    private int itemDiscount;
    /**
     * Constructor for item with default values
     */
    public Item()
    {
        itemName = "null";
        itemId = 1;
        itemCost = 1;
        itemLvl = 1;
        itemDiscount = 0;
    }
    /**
     * Constructor for item with parameters
     * @param item's name
     * @param item's id
     * @param item's cost
     * @param item's stock level
     * @param item's discount
     */
    public Item(String name,int id,int cost,int lvl,int discount)
    {
        if (name.trim().equals(""))
            itemName = "null";
        else
            itemName = name;
        if (id <= 1000 && id >= 1)
            itemId = id;
        else
            itemId = 1;
        if (cost > 0)
            itemCost = cost;
        else
            itemCost = 1;
        if (lvl <= 1000 && lvl >= 1)
            itemLvl = lvl;
        else
            itemLvl = 1;
        if (discount == 0 || 
            discount == 10 || 
            discount == 20)
            itemDiscount = discount;
        else
            itemDiscount = 0;
    }
    /**
     * Return item's name
     * @return String name
     */
    public String getName()
    {
        return this.itemName;
    }
    /**
     * Return item's id
     * @return integer ID
     */
    public int getId()
    {
        return this.itemId;
    }
    /**
     * Return item's cost
     * @return integer cost
     */
    public int getCost()
    {
        return this.itemCost;
    }
    /**
     * Return item's stock level
     * @return integer stock level
     */
    public int getLvl()
    {
        return this.itemLvl;
    }
    /**
     * Return item's discount
     * @return integer discount
     */
    public int getDiscount()
    {
        return this.itemDiscount;
    }
    /**
     * Set item's name
     * @param String name
     */
    public void setName(String name)
    {
        if (!name.trim().equals(""))
            this.itemName = name;
    }
    /**
     * Set item's id
     * @param integer id
     */
    public void setId(int id)
    {
        if(id <= 1000 && id >= 1)
            this.itemId = id;
    }
    /**
     * Set item's cost
     * @param integer cost
     */
    public void setCost(int cost)
    {
        if (cost > 0)
            this.itemCost = cost;
    }
    /**
     * Set item's stock level
     * @param integer stock level
     */
    public void setLvl(int lvl)
    {
        if (lvl <= 1000 && lvl >= 1)
        this.itemLvl = lvl;
    }
    /**
     * Set item's discount
     * @param integer discount
     */
    public void setDiscount(int discount)
    {
        if(discount == 0 ||
           discount == 10 ||
           discount == 20)
        this.itemDiscount = discount;
    }
    
}