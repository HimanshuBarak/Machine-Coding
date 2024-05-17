package vending_machine.vendingStates.impl;

import java.util.List;

import vending_machine.Coin;
import vending_machine.Item;
import vending_machine.VendingMachine;
import vending_machine.vendingStates.State;

public class HasMoneyState implements State{
    
    public HasMoneyState(){
        System.out.println("Currently Vending machine is in HasMoneyState");
    }

    
    @Override
    public void clickOnInsertCoinButton(VendingMachine machine) throws Exception {
        return;
    }
    
    @Override
    public void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception {
        machine.setVendingMachineState(new SelectionState());

    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception{
        machine.getCoinList().add(coin);
    }

    @Override
    public void chooseProduct(VendingMachine machine, int codeNumber) throws Exception{
        throw new Exception("You can not choose Product in idle state");
    }

    @Override
    public int getChange(int returnChangeMoney) throws Exception{
        throw new Exception("You can not get change in idle state");
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachine machine) throws Exception{
        throw new Exception("You can not get refunded in idle state");
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception{
        throw new Exception("Product can not be dispensed in idle state");
    }
    
    @Override
    public void updateInventory(VendingMachine machine, Item item, int codeNumber) throws Exception {
        machine.getInventory().addItem(item, codeNumber);
    }
}
