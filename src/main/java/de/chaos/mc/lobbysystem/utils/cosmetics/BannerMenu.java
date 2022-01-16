package de.chaos.mc.lobbysystem.utils.cosmetics;

import de.chaos.mc.lobbysystem.utils.BannerBuilder;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.Menu;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.MenuFactory;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BannerMenu {
    private MenuFactory menuFactory;
    private Player player;

    public BannerMenu(MenuFactory menuFactory, Player player) {
        this.menuFactory = menuFactory;
        this.player = player;
        getMenu().openInventory(player);
    }

    public Menu getMenu() {
        Menu menu = menuFactory.createMenu(9, "§6Banner");
        ItemStack banner1 = new BannerBuilder(Material.WHITE_BANNER).name("§6CreeperBanner").addPattern(PatternType.GRADIENT, DyeColor.GREEN).addPattern(PatternType.CREEPER, DyeColor.RED).build();
        ItemStack banner2 = new BannerBuilder(Material.BLUE_BANNER).name("§6WorldBanner").addPattern(PatternType.GLOBE, DyeColor.GREEN).build();
        ItemStack banner3 = new BannerBuilder(Material.BLUE_BANNER).name("§6WorldBanner").addPattern(PatternType.GLOBE, DyeColor.GREEN).build();
        ItemStack banner4 = new BannerBuilder(Material.BLUE_BANNER).name("§6WorldBanner").addPattern(PatternType.GLOBE, DyeColor.GREEN).build();
        ItemStack banner5 = new BannerBuilder(Material.BLUE_BANNER).name("§6WorldBanner").addPattern(PatternType.GLOBE, DyeColor.GREEN).build();
        ItemStack banner6 = new BannerBuilder(Material.BLUE_BANNER).name("§6WorldBanner").addPattern(PatternType.GLOBE, DyeColor.GREEN).build();
        ItemStack banner7 = new BannerBuilder(Material.BLUE_BANNER).name("§6WorldBanner").addPattern(PatternType.GLOBE, DyeColor.GREEN).build();
        ItemStack banner8 = new BannerBuilder(Material.RED_BANNER).name("§6UnknownCreature").addPattern(PatternType.BORDER, DyeColor.BLACK).addPattern(PatternType.CURLY_BORDER, DyeColor.BLACK).addPattern(PatternType.CREEPER, DyeColor.BLACK).addPattern(PatternType.SKULL, DyeColor.BLACK).build();
        ItemStack banner9 = new BannerBuilder(Material.BLUE_BANNER).name("§6WorldBanner").addPattern(PatternType.GLOBE, DyeColor.GREEN).build();

        menu.additem(0, banner1, player1 -> {
                    player1.getInventory().setHelmet(banner1);
                    player1.closeInventory();
        });
        menu.additem(1, banner2, player1 -> {
            player1.getInventory().setHelmet(banner2);
            player1.closeInventory();
        });
        menu.additem(2, banner3, player1 -> {
            player1.getInventory().setHelmet(banner3);
            player1.closeInventory();
        });
        menu.additem(3, banner4, player1 -> {
            player1.getInventory().setHelmet(banner4);
            player1.closeInventory();
        });
        menu.additem(4, banner5, player1 -> {
            player1.getInventory().setHelmet(banner5);
            player1.closeInventory();
        });
        menu.additem(5, banner6, player1 -> {
            player1.getInventory().setHelmet(banner6);
            player1.closeInventory();
        });
        menu.additem(6, banner7, player1 -> {
            player1.getInventory().setHelmet(banner7);
            player1.closeInventory();
        });
        menu.additem(7, banner8, player1 -> {
            player1.getInventory().setHelmet(banner8);
            player1.closeInventory();
        });
        menu.additem(8, banner9, player1 -> {
            player1.getInventory().setHelmet(banner9);
            player1.closeInventory();
        });
        return menu;
    }
}