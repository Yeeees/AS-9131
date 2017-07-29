import java.util.*;
import java.io.*;
/**
 * This is a program for recording and managing data of items and suppliers.
 * Users can enter valid number to choose the option they want.
 * Items in can be added, deleted, updated and searched.
 * Suppliers can also be added, deleted, updated and searched.
 * Each Supplier has their own Supply list.
 * Users can search for a particular item from these lists and also can edit the list.
 * The program will read the data when it starts and rewrite the data when it ends.
 * All the changes of data must follow by related rules, otherwise the changes won't work.
 * 
 * @author (Luyang Ye)
 * @version (21/10/2015)
 */
public class GroceryShop
{
    private Item item;
    private ItemCollection itemList;
    private SupplierList supplierList;
    /**
     * Constructor for this class
     */    
    public GroceryShop()
    {
        itemList = new ItemCollection();
        
    }
    /**
     * Start this program, display the menu.
     * Get option and provides functions
     */
    public void start()
    {
        int option = 0;
        read();
        while (option != 7)
        {
           displayMenu();        
           option = getOption(7);//get a choice
           switch (option)
           {
               case 1:addItem();break;
               case 2:listItems();break;
               case 3:updateItem();break;
               case 4:removeItems();break;
               case 5:help();break;
               case 6:supply();break;
               case 7:exit();break;
               default:
                    System.out.println("Again Please.");
           }
        }
    }
    /**
     * Let user to enter a option number
     * 
     * @param The size of the menu, restrict user to enter right options.
     * @return A integer, the number of option.
     */
    public int getOption(int size)
    {
        Scanner scString = new Scanner(System.in);
        int num = 0;
        try
        {
            String temp = scString.nextLine();
            num = Integer.parseInt(temp);//transform String to integer
            while (num<1 || 
               num>size)
               {
                   System.out.println("Choose an number from 1 to " + size);
                   System.out.print("Again please: ");
                   String temp2 = scString.nextLine();
                   num = Integer.parseInt(temp2);
                }
            
        }
        catch(Exception e)
        {
            System.out.println("Please enter a valid number.");
            num = 0;
        }
        return num;
    }
    /**
     * Display the main menu
     */    
    public void displayMenu()
    {
        System.out.println("Welcome to the My Grocery Shop");
        System.out.println("=============================");
        System.out.println("(1)Add Items");
        System.out.println("(2)List Items");
        System.out.println("(3)Update Items");
        System.out.println("(4)Remove Items");
        System.out.println("(5)Display Help");
        System.out.println("(6)Display Supply Menu");
        System.out.println("(7)Exit Shop");
        System.out.print("Choose an option from 1-6 please: ");
    }
    /**
     * Read data from items.txt
     * This method will splite the data can send create an for each line.
     * Then it wll send the item to a Arraylist called itemlist and keep it.
     * 
     */
    public void read()
    {
        String filename = "items.txt";
        String[] fields;
        try
        {
            FileReader inputFile = new FileReader(filename);
            Scanner parser = new Scanner(inputFile);
            while(parser.hasNextLine())
            {
                String line = parser.nextLine();
                fields = line.split(","); //split the data to a array               
                Item item = new Item(fields[0],Integer.parseInt(fields[1]),Integer.parseInt(fields[2]),Integer.parseInt(fields[3]),Integer.parseInt(fields[4]));//create the item
                itemList.addItem(item);
            }
            inputFile.close();
        }
        catch(FileNotFoundException exception)
        {
            System.out.println(filename + " not found");
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
    }
    /**
     * Create a item and send it to the itemList.
     */
    public void addItem()
    {
        String name = checkName();//check methods
        int id = checkId();
        int cost = checkCost();
        int lvl = checkLvl();
        int discount = checkDiscount(lvl);
        item = new Item(name,id,cost,lvl,discount);//create the item
        itemList.addItem(item);
    }
   /**
    * Check the name of the item to prevent any error
    * @retrun the name
    */
    public String checkName()
    {
        Scanner scString = new Scanner(System.in);
        boolean check = true;
        String newName = "null";
        try
        {            
            while (check)
            {
                System.out.print("Please type a name: ");
                newName = scString.nextLine();
                if(newName.trim().length() != 0)//check the length to prevent null
                {          
                    check = false;
                }
                else
                { 
                    System.out.println("The name can not be blank.");
                    check = true;
                }       
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return newName;
    }
    /**
     * Check the id number and prevent it's already exist
     * @return integer number
     */
    public int checkId()
    {
        int id = 0;
        Scanner scString = new Scanner(System.in);
        boolean check = true;        
            while (check)
            {
                try
              {
                System.out.print("Please enter the item's ID: ");                
                String temp = scString.nextLine();
                id = Integer.parseInt(temp);
                if(id < 1 || id > 1000)
                {
                    System.out.println("ID should between 1 to 1000");
                    check = true;
                }
                int count = 0;                
                for(Item index:itemList.getList())//search the id in itemlist. If find the same then the count value will plus one.
                {    
                    if(index.getId() == id)
                        count++;
                }
                if(count == 0)
                    check = false;
                else
                {
                    check = true;
                    System.out.println("This ID is already exist.");            
                }
              }
              catch(Exception e)
              {
                  System.out.println("Please enter a valid number");
                  id = 0;
                  check = true;
                  
              }
              
        }
        return id;
    }
    /**
     * Check item's price
     * @return integer cost
     */
    public int checkCost()
    {
        Scanner scString = new Scanner(System.in);
        int cost = 0;
        boolean check = true;        
        while(check)
        {
            try
            {       
                System.out.print("Please enter the item's cost: ");
                String temp = scString.nextLine();
                cost = Integer.parseInt(temp);
                if (cost <= 0)
                {
                    check = true;
                    System.out.println("Item's cost must greater than 0.");
                }
                else
                    check = false;
            }
            catch(Exception e)
            {
               System.out.println("Please enter a valid number");
               cost = 0;
               check = true;
            }
        }
        
        return cost;
    }
    /**
     * Check stock level and make sure it is valid
     * @return integer number level
     */
    public int checkLvl()
    {
        Scanner scString = new Scanner(System.in);
        int lvl = 0;
        boolean check = true;
        while(check)
        {
            try
            {        
                System.out.print("Please enter the item's stock-level: ");
                String temp = scString.nextLine();
                lvl = Integer.parseInt(temp);
                if (lvl <= 0 || lvl >= 1000)
                {
                    System.out.println("Stock level should between 1 to 1000");
                    check = true;
                }
                else
                    check = false;
            }
            
        
            catch(Exception e)
            {
                System.out.println("Please enter a valid number");
                lvl = 0;
                check = true;
            }
        }
        return lvl;
    }
    /**
     * Arrange the discount
     * @param a temp number for stock level
     * @return integer of discount
     */
    public int checkDiscount(int temp)
    {    
        int lvl = temp;
        if(lvl >= 0 &&
           lvl <= 10)
           return 0;
        else if(lvl >= 11 &&
                lvl <= 20)
           return 10;
        else
            return 20;
    }
    /**
     * Get option and list items by user's option
     * 
     */
    public void listItems()
    {
        int option = 0;
        while (option != 5)
        {
           listMenu();        
           option = getOption(5);
           switch (option)
           {
               case 1:listItems(itemList.getList());break;
               case 2:searchName();break;
               case 3:searchLvl();break;
               case 4:searchDiscount();break;
               case 5:break;
               default:
                    System.out.println("Choose a number from 1-5.");
           }
        }
        
    }
    /**
     * Enter a name and check wether this name exist or not
     */
    public void searchName()
    {
        String name = checkName();
        if(itemList.searchName(name).size() == 0)
            System.out.println("No such item.");
        else
            listItems(itemList.searchName(name));
        
    }
    /**
     * Get option for searching stock level, can be equal greater or lower
     */
    public void searchLvl()
    {
        int option = 0;
        while (option != 4)
        {
           levelMenu();        
           option = getOption(4);
           switch (option)
           {
               case 1:searchLvlEqual();break;
               case 2:searchLvlGreater();break;
               case 3:searchLvlLower();break;
               case 4:break;
               default:
                    System.out.println("Choose a number from 1-5.");
           }
        }
        
    }
    /**
     * Search for the level equals to a number
     */
    public void searchLvlEqual()
    {
        int lvl = checkLvl();
        if(itemList.searchLvlEqual(lvl).size() == 0)
            System.out.println("No such item.");
        else
            listItems(itemList.searchLvlEqual(lvl));
        
    }
    /**
     * Search for the level greater than a number
     */
    public void searchLvlGreater()
    {
        int lvl = checkLvl();
        if(itemList.searchLvlGreater(lvl).size() == 0)
            System.out.println("No such item.");
        else
            listItems(itemList.searchLvlGreater(lvl));
        
    }
    /**
     * Search for the level lower than to a number
     */
    public void searchLvlLower()
    {
        int lvl = checkLvl();
        if(itemList.searchLvlLower(lvl).size() == 0)
            System.out.println("No such item.");
        else
            listItems(itemList.searchLvlLower(lvl));
        
    }
    /**
     * Search for items have particular discount.
     * The discount only can be in 0 10 or 20
     */
    public void searchDiscount()
    {
        Scanner scString = new Scanner(System.in);
        int discount = 0;
        boolean check = true;
        while(check)
        {
            try
            {        
                System.out.print("Please enter the item's discount(from 0 or 10 or 20): ");
                String temp = scString.nextLine();
                discount = Integer.parseInt(temp);
                if (discount == 0 ||
                    discount == 10 ||
                    discount == 20)
                    check = false;
                else
                    {
                        System.out.println("Please enter a valid discount.");
                        check = true;                        
                    }
            }
            
        
            catch(Exception e)
            {
                System.out.println("Please enter a valid number");
                discount = 0;
                check = true;
            }
        }
        if(itemList.searchDiscount(discount).size() == 0)
            System.out.println("No such item.");
        else
            listItems(itemList.searchDiscount(discount));
        
    }
    /**
     * List all the items in a Arraylist of items
     */
    public void listItems(ArrayList<Item> temp)
    {    
        for(Item index:temp)
        {    
            System.out.print("Name: ");
            System.out.print(index.getName() + "   ");
            System.out.print("ID: ");
            System.out.print(index.getId() + "   ");
            System.out.print("Cost: ");
            System.out.print(index.getCost() + "   ");
            System.out.print("Stock-level: ");
            System.out.print(index.getLvl() + "   ");
            System.out.print("Discount: ");
            System.out.println(index.getDiscount() + "%");
        }    
    }
    /**
     * Display menu for level search
     */
    public void levelMenu()
    {
        System.out.println("Choose a option");
        System.out.println("================");
        System.out.println("(1)Equals to a number");
        System.out.println("(2)Greater than a number");
        System.out.println("(3)Lower than a number");
        System.out.println("(4)Exit");
        System.out.println("Choose a number from 1-4.");
    }
    /**
     * Display menu for List menu
     */
    public void listMenu()
    {
        System.out.println("Display the list");
        System.out.println("================");
        System.out.println("(1)List all the ltems");
        System.out.println("(2)List by name");
        System.out.println("(3)List by stock-level");
        System.out.println("(4)List by discount");
        System.out.println("(5)Exit");
        System.out.println("Choose a number from 1-5.");
    }
    /**
     * Method for update item.
     * First it will check the ID provided from user whether it is exist or not.
     * Then it will find this particular item.
     * Finally it let user to enter the new values of item update them.
     */
    public void updateItem()
    {
        System.out.println("Now you can update the item.");
        System.out.println("=============================");
        int id = checkIdExist();      
        int position = itemList.searchPosition(id);
        String name = checkName();
        int lvl = checkLvl();
        int discount = checkDiscount(lvl);
        itemList.getList().get(position).setName(name);
        itemList.getList().get(position).setLvl(lvl);
        itemList.getList().get(position).setDiscount(discount);
    }
    /**
     * Check the id to prevent it's not existing.
     * @return this id
     */
    public int checkIdExist()
    {
        int id = 0;
        Scanner scString = new Scanner(System.in);
        boolean check = true;        
            while (check)
            {
                try
              {
                System.out.print("Please enter the item's ID: ");                
                String temp = scString.nextLine();
                id = Integer.parseInt(temp);             
                if(id < 1 || id > 1000)
                {
                    System.out.println("ID should between 1 to 1000");
                    check = true;
                }
                int count = 0;                
                for(Item index:itemList.getList())
                {    
                    if(index.getId() == id)
                        count++;
                }
                if(count == 0)
                {   
                    check = true;
                    System.out.println("This ID is not exist.");     
                }
                else
                    check = false;              
              }
              catch(Exception e)
              {
                  System.out.println("Please enter a valid number");
                  id = 0;
                  check = true;                  
              }              
        }
        return id;
    }
    /**
     * Method for remove a particular item
     */
    public void removeItems()
    {
        System.out.println("Now you can delete the item.");
        System.out.println("=============================");
        int id = checkIdExist();      
        int position = itemList.searchPosition(id);
        if(itemList.getList().get(position).getLvl() >= 3)
            System.out.println("Sorry, you cant delete this item.");
        else
        {
            itemList.getList().remove(position);
            System.out.println("You have already deleted item " + id);
        }
    }
    /**
     * Display HEIP
     */
    public void help()
    {
        System.out.println("               HELP MENU                 ");
        System.out.println("==========================================");
        System.out.println("This is a program that can help the grocery ");
        System.out.println("shop's owner manage his or her store. When it ");
        System.out.println("start it can automatically load in previous data.");
        System.out.println("PRESS 1. You can add a new item by following rules: ");
        System.out.println("(1) Item's name can't be blank.");
        System.out.println("(2) Item's ID must be unique and between 1 to 1000.");
        System.out.println("(3) Item's cost must be grearter than 0.");
        System.out.println("(4) Item's stock level must between 1 to 1000.");
        System.out.println("(5) Item;s discount will be arranged by the program.");
        System.out.println("PRESS 2. You can list the items by following choices");
        System.out.println("(1) List all the items");
        System.out.println("(2) List by name");
        System.out.println("(3) List by level");
        System.out.println("(4) List by discount");
        System.out.println("PRESS 3. You can update a item by entering ID");
        System.out.println("PRESS 4. You can remove a item by entering ID");
        System.out.println("PRESS 5. HELP");
        System.out.println("PRESS 6. Entering supplier menu");
        System.out.println("In this menu, You can check and list all the");
        System.out.println("suppliers and update them. Also you can search");
        System.out.println("for a paticular item and find out wich supplier can");
        System.out.println("provide it.");
        System.out.println("PRESS 7. EXIT");
        System.out.println("The program will write all the data into the file");
        System.out.println("and finally exit the program.");
    }
    /**
     * Method for exit program.
     * Write the data back to the file and clean the program
     * 
     */
    public void exit()
    {
        write();
        itemList.clean();
        System.out.println("Thank you for using GS!");
    }
    /**
     * Write the data back to the txt.
     * Splite and write into the file.
     */
    public void write()
    {
        try
        {
            PrintWriter outputFile = new PrintWriter("items.txt");
            for(Item index:itemList.getList())//read the values in itemlist
            {
                outputFile.print(index.getName());
                outputFile.print(",");
                outputFile.print(index.getId());
                outputFile.print(",");
                outputFile.print(index.getCost());
                outputFile.print(",");
                outputFile.print(index.getLvl());
                outputFile.print(",");
                outputFile.println(index.getDiscount());
            }
            outputFile.close();
        }
        catch(IOException e)
        {
            System.out.println("Saving Error!");
        }
    }
    /**
     * Supply's main menu
     */
    public void supply()
    {
        int option = 0;
        readSupplier();
        while (option != 5)
        {
           displaySupplyMenu();        
           option = getOption(5);
           switch (option)
           {
               case 1:addSupplier();break;
               case 2:listSupply();break;
               case 3:updateSupplier();break;
               case 4:removeSupplier();break;
               case 5:supplierExit();break;
               default:
                    System.out.println("Again Please.");
           }
        }
    }
    /**
     * Display supply menu
     */
    public void displaySupplyMenu()
    {
        System.out.println("This is supplier menu");
        System.out.println("======================");
        System.out.println("(1)Add supplier");
        System.out.println("(2)Find supply");
        System.out.println("(3)Update supplier");
        System.out.println("(4)Remove supplier");
        System.out.println("(5)Exit menu");
        System.out.print("Choose an option from 1-5 please: ");
    }
    /**
     * Read the data from supplier's file
     */
    public void readSupplier()
    {
        String filename = "supplier.txt";
        supplierList = new SupplierList();
        String[] supFields;//array for supplier's attributes
        String[] itemFields;//array for supplier's items
        //ItemCollection itemList;
        try
        {
            FileReader inputFile = new FileReader(filename);
            Scanner parser = new Scanner(inputFile);
            while(parser.hasNextLine())//for each line
            {
                ItemCollection supItemList = new ItemCollection();
                String line = parser.nextLine();
                supFields = line.split(",");//splite this line into three values name phone and items
                itemFields = supFields[2].split(" ");//splite the third value
                int i;
                for(i=0;i < itemFields.length/5;i++)//each item has 5 attributes, splite the string of items and put attributes into a item object
                {
                    Item item = new Item(itemFields[0+5*i],Integer.parseInt(itemFields[1+5*i]),Integer.parseInt(itemFields[2+5*i]),Integer.parseInt(itemFields[3+5*i]),Integer.parseInt(itemFields[4+5*i]));
                    supItemList.addItem(item);
                }
                Supplier supplier = new Supplier(supFields[0],Integer.parseInt(supFields[1]),supItemList);//create new supplier
                supplierList.addSupplier(supplier);
            }
            inputFile.close();
        }
        catch(FileNotFoundException exception)
        {
            System.out.println(filename + " not found");
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
        catch(Exception e)
        {
            System.out.println("Remove all the invalid supplier.");
        }
        
    }
    /**
     * Add a supplier
     */
    public void addSupplier()
    {
        String name = checkName();
        int phone = checkPhoneExist();
        Supplier supplier = new Supplier();
        supplier.setName(name);
        supplier.setNumber(phone);
        supplierList.addSupplier(supplier);
    }
    /**
     * Check if the phone number existing
     * @return a valid phone number
     */
    public int checkPhoneExist()
    {
        int number = 0;
        Scanner scString = new Scanner(System.in);
        boolean check = true;        
            while (check)
            {
                try
              {
                System.out.print("Please enter the supplier's phone number: ");                
                String temp = scString.nextLine();
                number = Integer.parseInt(temp);                
                int count = 0;                
                for(Supplier index:supplierList.getList())
                {    
                    if(index.getNumber() == number)
                        count++;
                }
                if(count == 0)
                {
                    check = false;
                }
                else
                {
                    
                    check = true;
                    System.out.println("This phone number is exist.");
                }
              }
              catch(Exception e)
              {
                  System.out.println("Please enter a valid number");
                  number = 0;
                  check = true;
                  
              }
              
        }
        return number;
    }
    /**
     * Check the phone to prevent it's not existing
     * @return a existing phone number
     */
    public int checkPhone()
    {
        int number = 0;
        Scanner scString = new Scanner(System.in);
        boolean check = true;        
        while (check)
        {
                try
              {
                System.out.print("Please enter the supplier's phone number: ");                
                String temp = scString.nextLine();
                number = Integer.parseInt(temp);                
                int count = 0;                
                for(Supplier index:supplierList.getList())
                {    
                    if(index.getNumber() == number)
                        count++;
                }
                if(count == 0)
                {
                    check = true;
                    System.out.println("This phone number is not exist.");
                }
                else
                {
                    check = false;
                }
              }
              catch(Exception e)
              {
                  System.out.println("Please enter a valid number");
                  number = 0;
                  check = true;
                  
              }
              
        }
        return number;
        }
    /**
     * List the suppliers in different options
     */
    public void listSupply()
    {
        int option = 0;
        while (option != 5)
        {
           listSupplierMenu();        
           option = getOption(5);
           switch (option)
           {
               case 1:listSuppliers(supplierList.getList());break;
               case 2:searchSupplierName();break;
               case 3:searchSupplierPhone();break;
               case 4:searchSupply();break;
               case 5:break;
               default:
                    System.out.println("Choose a number from 1-5.");
           }
        }
        
    }
    /**
     * list the information of a supplier and the items he or her provides
     */
    public void listSuppliers(ArrayList<Supplier> temp)
    {    
        for(Supplier index:temp)
        {    
            System.out.print(index.getName() + ",");
            System.out.println(" Telephone number: " + index.getNumber());
            System.out.println("This supplier can provides: ");
            listItems(index.getSupplyList());
        }    
    }
    /**
     * Search for a name of supplier
     */
    public void searchSupplierName()
    {
        String name = checkName();
        if(supplierList.searchName(name).size() == 0)
            System.out.println("No such supplier.");
        else
            listSuppliers(supplierList.searchName(name));
    }
    /**
     * Search for a phone number
     */
    public void searchSupplierPhone()
    {
        int number = checkPhone();
        if(supplierList.searchPhone(number).size() == 0)
            System.out.println("No such supplier.");
        else
            listSuppliers(supplierList.searchPhone(number));
    }
    /**
     * Search for a particular item of the supplier
     */
    public void searchSupply()
    {
        String item = checkName();
        if(supplierList.searchItem(item).size() == 0)
            System.out.println("No such supplier.");
        else
            listSuppliers(supplierList.searchItem(item));
    }
    /**
     * Display supplier's menu
     */
    public void listSupplierMenu()
    {
        System.out.println("Display the Supplier's list");
        System.out.println("================");
        System.out.println("(1)List all the suppliers");
        System.out.println("(2)List by name");
        System.out.println("(3)List by phone number");
        System.out.println("(4)List by a particular item");
        System.out.println("(5)Exit");
        System.out.println("Choose a number from 1-5.");
    }
    /**
     * Update a Supplier.
     * Search for this supplier's phone number and locate this supplier
     * 
     */
    public void updateSupplier()
    {
        System.out.println("Now you can update the Supplier.");
        System.out.println("================================");
        int phone = checkPhone();      
        int supPosition = supplierList.searchPosition(phone);
        int option = 0;
        while (option != 7)
        {
           displaySupplierMenu(supPosition,phone);        
           option = getOption(7);
           switch (option)
           {
               case 1:addSupplierItem(supPosition);break;
               case 2:listSuppliers(supplierList.searchPhone(phone));break;
               case 3:updateSupplierItem(supPosition);break;
               case 4:removeSupplierItems(supPosition);break;
               case 5:updateSupplierName(supPosition);break;
               case 6:updateSupplierPhone(supPosition);break;
               case 7:break;
               default:
                    System.out.println("Again Please.");
           }
        }
        
    }
    /**
     * Change supplier's name
     * @param supplier's position in supplier list
     */
    public void updateSupplierName(int supPosition)
    {
        String name = checkName();
        supplierList.getList().get(supPosition).setName(name);
    }
    /**
     * Change supplier's phone number
     * @param supplier's position in supplier list
     */
    public void updateSupplierPhone(int supPosition)
    {
        int number = checkPhoneExist();
        supplierList.getList().get(supPosition).setNumber(number);
    }
    /**
     * Display supplier's menu
     * @param supplier's position in supplier list
     * @param supplier's phone number
     */
    public void displaySupplierMenu(int position, int phone)
    {
        System.out.println("Supplier's name: " + supplierList.getList().get(position).getName());//get supplierlist and get the particular supplier and get his or her name
        System.out.println("Phone number: " + phone);
        System.out.println("=============================");
        System.out.println("(1)Add Items");
        System.out.println("(2)List Items");
        System.out.println("(3)Update Items");
        System.out.println("(4)Remove Items");
        System.out.println("(5)Update supplier's name");
        System.out.println("(6)Update supplier's phone number");
        System.out.println("(7)Exit");
        System.out.print("Choose an option from 1-5 please: ");
    }
    /**
     * Add a item for this supplier
     * @param supplier's position in supplier list
     */
    public void addSupplierItem(int supPosition)
    {
        System.out.println("Now you can add a item");
        System.out.println("=======================");
        String name = checkName();
        int id = checkSupplierItemId(supPosition);
        int cost = checkCost();
        int lvl = checkLvl();
        int discount = checkDiscount(lvl);
        item = new Item(name,id,cost,lvl,discount);
        supplierList.getList().get(supPosition).addItem(item);//find this supplier and add item
    }
    /**
     * Check particular supplier's item 
     * @param supplier's position in supplier list
     * @return integer id
     */
    public int checkSupplierItemId(int supPosition)
    {
        int id = 0;
        Scanner scString = new Scanner(System.in);
        boolean check = true;        
            while (check)
            {
                try
              {
                System.out.print("Please enter the item's ID: ");                
                String temp = scString.nextLine();
                id = Integer.parseInt(temp);
                if(id < 1 || id > 1000)
                {
                   System.out.println("ID number should between 1 to 1000");
                   check = true;
                }
                int count = 0;                
                for(Item index:supplierList.getList().get(supPosition).getSupplyList())
                {    
                    if(index.getId() == id)
                        count++;
                }
                if(count == 0)
                {
                    check = false;
                }                
                else
                {
                    
                    check = true;
                    System.out.println("This ID is already exist.");         
                }
              }
              catch(Exception e)
              {
                  System.out.println("Please enter a valid number");
                  id = 0;
                  check = true;
                  
              }
              
        }
        return id;
    }
    /**
     * prevent supplier's one item id not existing
     * @param supplier's position in supplier list
     * @return integer id
     */
    public int checkSupplierItemIdExist(int supPosition)
    {
        int id = 0;
        Scanner scString = new Scanner(System.in);
        boolean check = true;        
            while (check)
            {
              try
              {
                System.out.print("Please enter the item's ID: ");                
                String temp = scString.nextLine();
                id = Integer.parseInt(temp);
                if(id < 1 || id > 1000)
                {
                   System.out.println("ID number should between 1 to 1000");
                   check = true;
                }
                int count = 0;                
                for(Item index:supplierList.getList().get(supPosition).getSupplyList())
                {    
                    if(index.getId() == id)
                        count++;
                }
                if(count == 0)
                {
                                      
                    check = true;
                    System.out.println("This ID is not exist."); 
                }                
                else
                {
                    check = false;      
                }
              }
              catch(Exception e)
              {
                  System.out.println("Please enter a valid number");
                  id = 0;
                  check = true;
                  
              }
              
        }
        return id;
    }
    /**
     * update supplier's item
     * @param supplier's position in supplier list
     */
    public void updateSupplierItem(int supPosition)
    {
        System.out.println("Now you can update the item.");
        System.out.println("=============================");
        int id = checkSupplierItemIdExist(supPosition);      
        int itemPosition = supplierList.getList().get(supPosition).searchItemPosition(id);
        String name = checkName();
        int lvl = checkLvl();
        int discount = checkDiscount(lvl);
        supplierList.getList().get(supPosition).getSupplyList().get(itemPosition).setName(name);//find this supplier and find his or her item and set new values
        supplierList.getList().get(supPosition).getSupplyList().get(itemPosition).setLvl(lvl);
        supplierList.getList().get(supPosition).getSupplyList().get(itemPosition).setDiscount(discount);
    }
    /**
     * remove supplier's item
     * @param supplier's position in supplier list
     */
    public void removeSupplierItems(int supPosition)
    {
        System.out.println("Now you can delete the item.");
        System.out.println("=============================");
        int id = checkSupplierItemIdExist(supPosition);      
        int itemPosition = supplierList.getList().get(supPosition).searchItemPosition(id);
        if(supplierList.getList().get(supPosition).getSupplyList().get(itemPosition).getLvl() >= 3)
            System.out.println("Sorry, you cant delete this item.");
        else
        {
            supplierList.getList().get(supPosition).getSupplyList().remove(itemPosition);
            System.out.println("You have already deleted item " + id);
        }
    }
    /**
     * exit supplier menu
     * write the data back to supplier file
     * clean all data
     */
    public void supplierExit()
    {
        writeSupplier();
        supplierList.clean();
    }
    /**
     * write the data back to supplier file
     */
    public void writeSupplier()
    {
        try
        {
            PrintWriter outputFile = new PrintWriter("supplier.txt");
            for(Supplier supplier:supplierList.getList())
            {
                outputFile.print(supplier.getName());
                outputFile.print(",");
                outputFile.print(supplier.getNumber());
                outputFile.print(",");
                for(Item item:supplier.getSupplyList())
                {
                    outputFile.print(item.getName());
                    outputFile.print(" ");
                    outputFile.print(item.getId());
                    outputFile.print(" ");
                    outputFile.print(item.getCost());
                    outputFile.print(" ");
                    outputFile.print(item.getLvl());
                    outputFile.print(" ");
                    outputFile.print(item.getDiscount());
                    outputFile.print(" ");
                }
                outputFile.println("");
                
            }
            outputFile.close();
        }
        catch(IOException e)
        {
            System.out.println("Saving Error!");
        }
    }
    /**
     * remove a particular supplier
     */
    public void removeSupplier()
    {
        int phone = checkPhone();      
        int supPosition = supplierList.searchPosition(phone); //find this supplier
        supplierList.getList().remove(supPosition);
        System.out.println("This supplier has been removed");
    }
}
