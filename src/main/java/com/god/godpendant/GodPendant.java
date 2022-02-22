package com.god.godpendant;

import com.god.godpendant.items.ItemGodPendant;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = GodPendant.MODID, name = GodPendant.NAME, version = GodPendant.VERSION)
public class GodPendant {
	
	public static final String MODID = "godpendant";
    public static final String NAME = "GodPendant";
    public static final String VERSION = "1.0";
    
    public static final float playerFlySpeed = 0.05f;
    
    public static final Item GODPENDANT = new ItemGodPendant();
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
    
    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
    	if(event.getEntityLiving() instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer)event.getEntityLiving();
    		
    		for(int i = 0; i < player.inventory.getSizeInventory(); ++i) {
    			ItemStack itemStack = player.inventory.getStackInSlot(i);
    			if(itemStack != null && itemStack.getItem() == GODPENDANT) {
    				event.setCanceled(true);
    				break;
    			}
    		}
    	}
    }
    
    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
    	if(event.getEntityLiving() instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer)event.getEntityLiving();
    		
    		for(int i = 0; i < player.inventory.getSizeInventory(); ++i) {
    			ItemStack itemStack = player.inventory.getStackInSlot(i);
    			if(itemStack != null && itemStack.getItem() == GODPENDANT) {
    				//私は神だI can fly
    				player.capabilities.allowFlying = true;
    				player.capabilities.setFlySpeed(playerFlySpeed * 5);
    				
    				player.setHealth(player.getMaxHealth());
    				player.getFoodStats().setFoodLevel(20);
    				player.getFoodStats().setFoodSaturationLevel(20);
    				break;
    			} else if(itemStack != null && itemStack.getItem() != GODPENDANT) {
    				if(player.capabilities.allowFlying && !player.capabilities.isCreativeMode) {
    					player.capabilities.setFlySpeed(playerFlySpeed);
    					player.capabilities.allowFlying = false;
    					player.capabilities.isFlying = false;
    				}
    			}
    		}
    	}
    }
    
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
    	event.getRegistry().register(GODPENDANT);
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event) {
    	ModelLoader.setCustomModelResourceLocation(GODPENDANT, 0, new ModelResourceLocation(new ResourceLocation("godpendant", "godpendant"), "inventory"));
    }

}