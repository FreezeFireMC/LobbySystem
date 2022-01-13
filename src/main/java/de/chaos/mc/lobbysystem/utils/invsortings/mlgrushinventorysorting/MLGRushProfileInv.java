package de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting;

import de.chaos.mc.lobbysystem.utils.ItemBuilder;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.itemnames.EnglishMLGRushITranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.itemnames.FrenchMLGRushTranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.itemnames.GermanMLGRushTranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.ormlite.MLGRushInventoryDAO;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.ormlite.UpdateMLGRushInventorySortingInterface;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageInterface;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MLGRushProfileInv {
    private UpdateMLGRushInventorySortingInterface invInterface;
    private LanguageInterface languageInterface;
    public MLGRushProfileInv(UpdateMLGRushInventorySortingInterface updateInventorySortingInterface, LanguageInterface languageInterface) {
        this.invInterface = updateInventorySortingInterface;
        this.languageInterface = languageInterface;
    }

    public void setInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setItem(invInterface.getInventory(player.getUniqueId()).getStickSlot(), new ItemBuilder(Material.STICK).name(getStickName(player)).itemStack());
        player.getInventory().setItem(invInterface.getInventory(player.getUniqueId()).getPickaxeSlot(), new ItemBuilder(Material.STONE_PICKAXE).name(getPickaxtName(player)).itemStack());
        player.getInventory().setItem(invInterface.getInventory(player.getUniqueId()).getSandstoneSlot(),new ItemBuilder(Material.SANDSTONE, 32).name(getBlockName(player)).itemStack());
    }

    public Inventory getInventory(Player player) {
        MLGRushInventoryDAO MLGRushInventoryDAO = invInterface.getInventory(player.getUniqueId());
        Inventory inventory = Bukkit.createInventory(player, 9, getInvName(player));
        inventory.setItem(MLGRushInventoryDAO.getStickSlot(), new ItemBuilder(Material.STICK).name(getStickName(player)).itemStack());
        inventory.setItem(MLGRushInventoryDAO.getPickaxeSlot(), new ItemBuilder(Material.STONE_PICKAXE).name(getPickaxtName(player)).itemStack());
        inventory.setItem(MLGRushInventoryDAO.getSandstoneSlot(), new ItemBuilder(Material.SANDSTONE).name(getBlockName(player)).itemStack());
        return inventory;
    }

    public String getStickName(Player player) {
        String string = null;
        switch(languageInterface.getLanguageType(player.getUniqueId())) {
            case EG:
                string = EnglishMLGRushITranslations.STICK.getTranslation();
                break;
            case DE:
                string = GermanMLGRushTranslations.STICK.getTranslation();
                break;
            case FR:
                string = FrenchMLGRushTranslations.STICK.getTranslation();
                break;
            case OTHER:
                string = EnglishMLGRushITranslations.STICK.getTranslation();
                break;
        }
        return string;
    }

    public String getBlockName(Player player) {
        String string = null;
        switch(languageInterface.getLanguageType(player.getUniqueId())) {
            case EG:
                string = EnglishMLGRushITranslations.BLOCKS.getTranslation();
                break;
            case DE:
                string = GermanMLGRushTranslations.BLOCKS.getTranslation();
                break;
            case FR:
                string = FrenchMLGRushTranslations.BLOCKS.getTranslation();
                break;
            case OTHER:
                string = EnglishMLGRushITranslations.BLOCKS.getTranslation();
                break;
        }
        return string;
    }

    public String getPickaxtName(Player player) {
        String string = null;
        switch(languageInterface.getLanguageType(player.getUniqueId())) {
            case EG:
                string = EnglishMLGRushITranslations.PICKAXT.getTranslation();
                break;
            case DE:
                string = GermanMLGRushTranslations.PICKAXT.getTranslation();
                break;
            case FR:
                string = FrenchMLGRushTranslations.PICKAXT.getTranslation();
                break;
            case OTHER:
                string = EnglishMLGRushITranslations.PICKAXT.getTranslation();
                break;
        }
        return string;
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
