package de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting;

import de.chaos.mc.lobbysystem.utils.ItemBuilder;
import de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.itemnames.EnglishFreezeFightTranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.itemnames.FrenchFreezeFightTranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.itemnames.GermanFreezeFightTranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.ormlite.FreezeFightInvSortingDAO;
import de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.ormlite.UpdateFreezeFightInventorySortingInterface;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageInterface;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class FreezeFightProfileInv {
    private UpdateFreezeFightInventorySortingInterface invInterface;
    private LanguageInterface languageInterface;
    public FreezeFightProfileInv(UpdateFreezeFightInventorySortingInterface updateInventorySortingInterface, LanguageInterface languageInterface) {
        this.invInterface = updateInventorySortingInterface;
        this.languageInterface = languageInterface;
    }

    public void setInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setItem(invInterface.getInventory(player.getUniqueId()).getSwordSlot(), new ItemBuilder(Material.IRON_SWORD).name(getSwordName(player)).itemStack());
        player.getInventory().setItem(invInterface.getInventory(player.getUniqueId()).getBowSlot(), new ItemBuilder(Material.BOW).name(getBowName(player)).itemStack());
        player.getInventory().setItem(invInterface.getInventory(player.getUniqueId()).getEggSlot(),new ItemBuilder(Material.EGG).name(getEggName(player)).itemStack());
        player.getInventory().setItem(invInterface.getInventory(player.getUniqueId()).getArrowSlot(),new ItemBuilder(Material.ARROW).name(getArrowName(player)).itemStack());
    }

    public Inventory getInventory(Player player) {
        FreezeFightInvSortingDAO freezeFightInvSortingDAO = invInterface.getInventory(player.getUniqueId());
        Inventory inventory = Bukkit.createInventory(player, 9, getInvName(player));
        inventory.setItem(freezeFightInvSortingDAO.getSwordSlot(), new ItemBuilder(Material.IRON_SWORD).name(getSwordName(player)).itemStack());
        inventory.setItem(freezeFightInvSortingDAO.getBowSlot(), new ItemBuilder(Material.BOW).name(getBowName(player)).itemStack());
        inventory.setItem(freezeFightInvSortingDAO.getEggSlot(), new ItemBuilder(Material.EGG).name(getEggName(player)).itemStack());
        inventory.setItem(freezeFightInvSortingDAO.getArrowSlot(), new ItemBuilder(Material.ARROW).name(getArrowName(player)).itemStack());
        return inventory;
    }

    public String getSwordName(Player player) {
        String string = null;
        switch(languageInterface.getLanguageType(player.getUniqueId())) {
            case EG:
                string = EnglishFreezeFightTranslations.SWORD.getTranslation();
                break;
            case DE:
                string = GermanFreezeFightTranslations.SWORD.getTranslation();
                break;
            case FR:
                string = FrenchFreezeFightTranslations.SWORD.getTranslation();
                break;
            case OTHER:
                string = EnglishFreezeFightTranslations.SWORD.getTranslation();
                break;
        }
        return string;
    }
    public String getInvName(Player player) {
        String string = null;
        switch(languageInterface.getLanguageType(player.getUniqueId())) {
            case EG:
                string = EnglishFreezeFightTranslations.INVNAME.getTranslation();
                break;
            case DE:
                string = GermanFreezeFightTranslations.INVNAME.getTranslation();
                break;
            case FR:
                string = FrenchFreezeFightTranslations.INVNAME.getTranslation();
                break;
            case OTHER:
                string = EnglishFreezeFightTranslations.INVNAME.getTranslation();
                break;
        }
        return string;
    }



    public String getBowName(Player player) {
        String string = null;
        switch(languageInterface.getLanguageType(player.getUniqueId())) {
            case EG:
                string = EnglishFreezeFightTranslations.BOW.getTranslation();
                break;
            case DE:
                string = GermanFreezeFightTranslations.BOW.getTranslation();
                break;
            case FR:
                string = FrenchFreezeFightTranslations.BOW.getTranslation();
                break;
            case OTHER:
                string = EnglishFreezeFightTranslations.BOW.getTranslation();
                break;
        }
        return string;
    }
    public String getEggName(Player player) {
        String string = null;
        switch(languageInterface.getLanguageType(player.getUniqueId())) {
            case EG:
                string = EnglishFreezeFightTranslations.EGG.getTranslation();
                break;
            case DE:
                string = GermanFreezeFightTranslations.EGG.getTranslation();
                break;
            case FR:
                string = FrenchFreezeFightTranslations.EGG.getTranslation();
                break;
            case OTHER:
                string = EnglishFreezeFightTranslations.EGG.getTranslation();
                break;
        }
        return string;
    }
    public String getArrowName(Player player) {
        String string = null;
        switch(languageInterface.getLanguageType(player.getUniqueId())) {
            case EG:
                string = EnglishFreezeFightTranslations.ARROW.getTranslation();
                break;
            case DE:
                string = GermanFreezeFightTranslations.ARROW.getTranslation();
                break;
            case FR:
                string = FrenchFreezeFightTranslations.ARROW.getTranslation();
                break;
            case OTHER:
                string = EnglishFreezeFightTranslations.ARROW.getTranslation();
                break;
        }
        return string;
    }
}
