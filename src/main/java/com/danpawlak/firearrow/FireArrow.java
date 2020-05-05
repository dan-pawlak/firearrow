package com.danpawlak.firearrow;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("firearrow")
public class FireArrow
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "firearrow";

    public FireArrow() {
        MinecraftForge.EVENT_BUS.register(this);

    }
}
