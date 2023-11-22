package com.hirakurai.hiracustomcrafts.util.tools;

import net.minecraft.nbt.CompoundTag;

import java.util.Random;

public class NBTHelper {
    public static CompoundTag createAttributeModifier(String attributeName,
                                                      String name,
                                                      String slot,
                                                      Integer operation,
                                                      Double amount,
                                                      Random random){
        CompoundTag attributeModifier = new CompoundTag();
        attributeModifier.putString("AttributeName", attributeName);
        attributeModifier.putString("Name", name);
        attributeModifier.putString("Slot", slot);
        attributeModifier.putInt("Operation", operation);
        attributeModifier.putDouble("Amount", amount);
        attributeModifier.putInt("UUIDMost", random.nextInt(32000)+1);
        attributeModifier.putInt("UUIDLeast", random.nextInt(64000)+32001);
        return attributeModifier;
    }
}
