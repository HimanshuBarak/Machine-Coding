package vending_machine.vendingStates.impl;
import java.util.List;
import vending_machine.Coin;
import vending_machine.Item;
import vending_machine.VendingMachine;
import vending_machine.vendingStates.State;


public class SelectionState implements State{
    public SelectionState(){
        System.out.println("Currently Vending machine is in SelectionState");
    }

    @Override
    public void clickOnInsertCoinButton(VendingMachine machine) throws Exception{
        throw new Exception("you can not click on insert coin button in Selection state");
    }

    @Override
    public void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception {
        return;
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception{
        throw new Exception("you can not insert Coin in selection state");
    }

    @Override
    public void chooseProduct(VendingMachine machine, int codeNumber) throws Exception {
        Item item = machine.getInventory().getItem(codeNumber);
        
        int paidByUser = 0;
        for(Coin coin:machine.getCoinList())
        {
            paidByUser += coin.getValue();
        }
        
        if(paidByUser < item.getPrice())
        {
            System.out.println("Insufficient Amount, Product you selected is for price: " + item.getPrice() + " and you paid: " + paidByUser);
               refundFullMoney(machine);
             throw new Exception("insufficient amount");
         } else if (paidByUser >= item.getPrice()) {
            if(paidByUser > item.getPrice())
            {
                getChange(paidByUser - item.getPrice());
            }
            machine.setVendingMachineState(new DispenseState(machine, codeNumber));
        }
        
        
    }

    @Override
    public int getChange(int returnExtraMoney) throws Exception{
        //actual logic should be to return COINs in the dispense tray, but for simplicity i am just returning the amount to be refunded
        System.out.println("Returned the change in the Coin Dispense Tray: " + returnExtraMoney);
        return returnExtraMoney;
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachine machine) throws Exception{
        System.out.println("Returned the full amount back in the Coin Dispense Tray");
        machine.setVendingMachineState(new IdleState(machine));
        return machine.getCoinList();
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception{
        throw new Exception("product can not be dispensed Selection state");
    }

    @Override
    public void updateInventory(VendingMachine machine, Item item, int codeNumber) throws Exception {
        throw new Exception("Inventory can not be updated in Selection state");
    }
}
