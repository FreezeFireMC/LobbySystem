package de.chaos.mc.lobbysystem.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.Arrays;

public class BannerBuilder {
    private final ItemStack itemStack;
    public BannerBuilder(Material material) {
        itemStack = new ItemStack(material);
    }
    public BannerBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
    }

    public BannerBuilder name(String name) {
        BannerMeta itemMeta = (BannerMeta) itemStack.getItemMeta();
        itemMeta.displayName(Component.text(name));
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public BannerBuilder enchantment(Enchantment enchantment, int level) {
        itemStack.addEnchantment(enchantment, level);
        return this;
    }

    public BannerBuilder setlore(String... lore) {
        BannerMeta itemMeta = (BannerMeta) itemStack.getItemMeta();
        itemMeta.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public BannerBuilder addPattern(PatternType patternType, DyeColor dyeColour) {
        BannerMeta itemMeta = (BannerMeta) itemStack.getItemMeta();
        itemMeta.addPattern(new Pattern(dyeColour, patternType));
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemStack build() {
        return itemStack;
    }
}
