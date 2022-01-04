package de.chaos.mc.lobbysystem.utils.profile;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.ItemBuilder;
import de.chaos.mc.lobbysystem.utils.megaUtils.itemskulls.ItemSkullFactory;
import de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary.MLGRushProfileInv;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.Menu;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.MenuFactory;
import de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary.itemnames.EnglishMLGRushITranslations;
import de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary.itemnames.FrenchMLGRushTranslations;
import de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary.itemnames.GermanMLGRushTranslations;
import de.chaos.mc.lobbysystem.utils.scorebaord.ScoreboardManager;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageInterface;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageType;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;

import java.util.UUID;

public class ProfileInventorys {
   private MenuFactory menuFactory;
   private MLGRushProfileInv mlgRushProfileInv;
   private LanguageInterface languageInterface;
   private ScoreboardManager scoreboardManager;
   public ProfileInventorys(MenuFactory menuFactory , MLGRushProfileInv mlgRushProfileInv, LanguageInterface languageInterface, ScoreboardManager scoreboard) {
       this.menuFactory = menuFactory;
       this.mlgRushProfileInv = mlgRushProfileInv;
       this.languageInterface = languageInterface;
       this.scoreboardManager = scoreboard;
   }

   public void getMainInventory(Player player) {
       Menu menu = menuFactory.createMenu(45, "§6Profile");
       // Glass at the sides
       menu.additem(0, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name("").itemStack(), null);
       menu.additem(1, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name("").itemStack(), null);
       menu.additem(2, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name("").itemStack(), null);
       menu.additem(3, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name("").itemStack(), null);
       menu.additem(4, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name("").itemStack(), null);
       menu.additem(5, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name("").itemStack(), null);
       menu.additem(6, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name("").itemStack(), null);
       menu.additem(7, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name("").itemStack(), null);
       menu.additem(8, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name("").itemStack(), null);

       // SubMenus
       menu.addSubmenu(10, new ItemBuilder(Material.CHEST).name(this.getInvName(player)).itemStack(), getSortingInventorys(player));
       menu.addSubmenu(13, getPlayerLanguageItem(player.getUniqueId()), getLanguageInventory(player));
       menu.openInventory(player);
   }

   public Menu getSortingInventorys(Player player) {
        Menu menu = menuFactory.createMenu(27, getInvName(player));
        menu.additem(10, new ItemBuilder(Material.BLAZE_ROD).name("§6MLGRush " + this.getInvName(player)).itemStack(), player1 -> {
            player.openInventory(mlgRushProfileInv.getInventory(player));
        });
        return menu;
   }

    public Menu getLanguageInventory(Player player) {
        Menu menu = menuFactory.createMenu(27, LobbySystem.getOnlinePlayers().get(player.getUniqueId()).getLanguageInv());
        menu.additem(10, new ItemSkullFactory().createItemSkull("http://textures.minecraft.net/textures/a9edcdd7b06173d7d221c7274c86cba35730170788bb6a1db09cc6810435b92c", "§6English", "UK"), player1 -> {
            languageInterface.setLanguageType(player1.getUniqueId(), LanguageType.EG);
            languageInterface.loadPlayerLanguage(player.getUniqueId());
            LobbySystem.getLobbySystem().getStringsLoader().getPlayerLobbyLanguage(player1.getUniqueId());
            player1.sendMessage(LobbySystem.getOnlinePlayers().get(player1.getUniqueId()).getLanguageUpdated());
            scoreboardManager.switchBoard(player1, LanguageType.EG);

        });
        menu.additem(13, new ItemSkullFactory().createItemSkull("http://textures.minecraft.net/textures/5e7899b4806858697e283f084d9173fe487886453774626b24bd8cfecc77b3f", "§6Deutsch", "Deutschland"), player1 -> {
            languageInterface.setLanguageType(player1.getUniqueId(), LanguageType.DE);
            languageInterface.loadPlayerLanguage(player.getUniqueId());
            LobbySystem.getLobbySystem().getStringsLoader().getPlayerLobbyLanguage(player1.getUniqueId());
            player1.sendMessage(LobbySystem.getOnlinePlayers().get(player1.getUniqueId()).getLanguageUpdated());
            scoreboardManager.switchBoard(player1, LanguageType.DE);
        });
        return menu;
    }


   public ItemStack getPlayerLanguageItem(UUID uuid) {
       LanguageType languageType = languageInterface.getLanguageType(uuid);
       ItemStack stack = null;
       switch (languageType) {
           case DE:
               stack = new ItemSkullFactory().createItemSkull("http://textures.minecraft.net/textures/5e7899b4806858697e283f084d9173fe487886453774626b24bd8cfecc77b3f", "§6Sprache wechseln", "Derzeit: Deutsch");
               break;
           case FR:
               stack = new ItemSkullFactory().createItemSkull("http://textures.minecraft.net/textures/51269a067ee37e63635ca1e723b676f139dc2dbddff96bbfef99d8b35c996bc", "§6French", "Current: French");
               break;
           case EG:
               stack= new ItemSkullFactory().createItemSkull("https://textures.minecraft.net/texture/a9edcdd7b06173d7d221c7274c86cba35730170788bb6a1db09cc6810435b92c", "§6Switch language", "Current: English");
               break;
           case OTHER:
               stack = new ItemSkullFactory().createItemSkull("https://textures.minecraft.net/texture/a9edcdd7b06173d7d221c7274c86cba35730170788bb6a1db09cc6810435b92c", "§6Switch language", "Current: English");
               break;
       }
       return stack;
   }

    public String getInvName(Player player) {
        String string = null;
        switch(languageInterface.getLanguageType(player.getUniqueId())) {
            case EG:
                string = EnglishMLGRushITranslations.INVNAME.getTranslation();
                break;
            case DE:
                string = GermanMLGRushTranslations.INVNAME.getTranslation();
                break;
            case FR:
                string = FrenchMLGRushTranslations.INVNAME.getTranslation();
                break;
            case OTHER:
                string = EnglishMLGRushITranslations.INVNAME.getTranslation();
                break;
        }
        return string;
    }
}