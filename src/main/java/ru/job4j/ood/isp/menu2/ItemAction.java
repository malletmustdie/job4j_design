package ru.job4j.ood.isp.menu2;

public class ItemAction implements Action {

    @Override
    public void execute(Item item) {
        System.out.println("Something with " + item.getName());
    }

}
