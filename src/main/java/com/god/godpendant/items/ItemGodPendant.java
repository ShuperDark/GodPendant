package com.god.godpendant.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGodPendant extends Item {

	
	public ItemGodPendant() {
		super();
		
		this.setRegistryName("godpendant", "godpendant");
		this.setCreativeTab(CreativeTabs.COMBAT);
		this.setUnlocalizedName("godpendant");
		this.setMaxStackSize(1);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

}
