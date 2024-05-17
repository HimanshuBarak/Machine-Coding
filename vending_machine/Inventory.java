package vending_machine;

/*
 * 
 * create inventory 
 * shelves = 5 
 * item in shelves
 * 
 * hashmap<item_code, itemshelf 
 * 
 * 
 * 
 * 
 */
public class Inventory {
    ItemShelf[] inventory; // this is basically the list of items preset in the machine

    public Inventory(int itemCount) {
        inventory = new ItemShelf[itemCount];
        // intialize the inventory
         initializeInventory();
    }
    
    void initializeInventory() {
        int startCode = 101;
        for (int i = 0; i < inventory.length; i++) {

            ItemShelf item = new ItemShelf();
            item.setCode(startCode);
            item.setSoldOut(true);
            inventory[i] = item;
            startCode++;

        }
    }
    
    public ItemShelf[] getInventory() {
        return inventory;
    }

    public void addItem(Item item, int itemCode) throws Exception {
        for (ItemShelf shelf : inventory) {
            if (shelf.getCode() == itemCode && !shelf.isSoldOut()) {
                shelf.setSoldOut(false);
                shelf.setItem(item);
            } else {
                throw new Exception("Invalid shelf ,a item is already present here");
            }
        }
    }
    
    public Item getItem(int codeNumber) throws Exception {
        for (ItemShelf shelf : inventory) {
            if (shelf.getCode() == codeNumber) {
                if (shelf.isSoldOut())
                    throw new Exception("Item already sold out");
                else
                    return shelf.getItem();
            }
        }
        throw new Exception("Invalid shelf code");
    }
    

    public void updateSoldOutItem(int codeNumber) {
        for (ItemShelf shelf : inventory) {
             if(shelf.getCode() == codeNumber)
               shelf.setSoldOut(true);
        }
    }
}


