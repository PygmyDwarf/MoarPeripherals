/**
 * Copyright (c) 2013-2014, Joshua Asbury (@theoriginalbit)
 * http://wiki.theoriginalbit.com/moarperipherals/
 *
 * MoarPeripherals is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.theoriginalbit.moarperipherals.common.container;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import java.util.ArrayDeque;
import java.util.Random;

/**
 * @author theoriginalbit
 * @since 14/10/2014
 */
public class QueueBuffer {
    private final int maxSize;
    private final String invName;
    private final Random rand = new Random();
    private final ArrayDeque<ItemStack> inventory;

    public QueueBuffer(String name, int size) {
        inventory = new ArrayDeque<ItemStack>(size);
        invName = name;
        maxSize = size;
    }

    public void readFromNBT(NBTTagCompound tag) {
        final NBTTagList list = tag.getTagList("BufferInv" + invName, 10);
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound itemTag = list.getCompoundTagAt(i);
            int slot = itemTag.getByte("Slot") & 255;
            if (slot >= 0 && slot < getSizeInventory()) {
                addItemStack(ItemStack.loadItemStackFromNBT(itemTag));
            }
        }
    }

    public void writeToNBT(NBTTagCompound tag) {
        final NBTTagList list = new NBTTagList();
        for (int i = 0; i < getSizeInventory(); ++i) {
            final ItemStack stack = getNextItemStack();
            if (stack != null) {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte) i);
                stack.writeToNBT(itemTag);
                list.appendTag(itemTag);
            }
        }
        tag.setTag("BufferInv" + invName, list);
    }

    public int getSizeInventory() {
        return maxSize;
    }

    public int getCurrentSize() {
        return inventory.size();
    }

    public void clear() {
        inventory.clear();
    }

    public void addItemStack(ItemStack stack) {
        if (inventory.size() < maxSize) {
            ItemStack item = stack.splitStack(1);
            inventory.add(item);
        }
    }

    public ItemStack getNextItemStack() {
        return inventory.size() > 0 ? inventory.remove() : null;
    }

    public boolean hasFreeSpace() {
        return getCurrentSize() < getSizeInventory();
    }

    public void explodeNext(World world, int x, int y, int z) {
        final ItemStack item = getNextItemStack();
        if (item.stackSize > 0) {
            explodeItemStack(item, world, x, y, z);
        }
    }

    public void explodeBuffer(World world, int x, int y, int z) {
        for (final ItemStack item : inventory) {
            if (item.stackSize > 0) {
                explodeItemStack(item, world, x, y, z);
            }
        }
    }

    private void explodeItemStack(ItemStack item, World world, int x, int y, int z) {
        if (item != null && item.stackSize > 0) {
            float factor = 0.05f;
            float rx = rand.nextFloat() * 0.8f + 0.1f;
            float ry = rand.nextFloat() * 0.8f + 0.1f;
            float rz = rand.nextFloat() * 0.8f + 0.1f;
            EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
            if (item.hasTagCompound()) {
                entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
            }
            entityItem.motionX = rand.nextGaussian() * factor;
            entityItem.motionY = rand.nextGaussian() * factor + 0.2f;
            entityItem.motionZ = rand.nextGaussian() * factor;
            world.spawnEntityInWorld(entityItem);
            item.stackSize = 0;
        }
    }
}
