package de.chaos.mc.lobbysystem.utils.profile;

import de.chaos.mc.lobbysystem.utils.ItemBuilder;
import de.chaos.mc.lobbysystem.utils.inventorylibary.MLGRushProfileInv;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.Menu;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.MenuFactory;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ProfileInventorys {
   private MenuFactory menuFactory;
   private MLGRushProfileInv mlgRushProfileInv;
   public ProfileInventorys(MenuFactory menuFactory , MLGRushProfileInv mlgRushProfileInv) {
       this.menuFactory = menuFactory;
       this.mlgRushProfileInv = mlgRushProfileInv;
   }

   public void getMainInventory(Player player) {
       Menu menu = menuFactory.createMenu(45, "§6Profile");
       menu.addSubmenu(1, new ItemBuilder(Material.CHEST).name("§6InventarSorierungen").itemStack(), getSortingInventorys(player));
       menu.openInventory(player);
   }

   public Menu getSortingInventorys(Player player) {
        Menu menu = menuFactory.createMenu(27, "§6Inventar Sortierungen");
        menu.additem(1, new ItemBuilder(Material.BLAZE_ROD).name("§6MLGRush InventarSortierung").itemStack(), player1 -> {
            player.openInventory(mlgRushProfileInv.getInventory(player));
        });
        return menu;
   }
}