/**
 * Copyright (c) 2013-2014, Joshua Asbury (@theoriginalbit)
 * http://wiki.theoriginalbit.com/moarperipherals/
 *
 * MoarPeripherals is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.theoriginalbit.minecraft.moarperipherals.common.tile.abstracts;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileMoarP extends TileEntity {

    private String owner = "[NONE]";

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        owner = tag.getString("owner");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setString("owner", owner);
    }

    @Override
    public Packet getDescriptionPacket() {
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, getDescriptionNBT());
    }

    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
        final NBTTagCompound tag = packet.data;
        readDescriptionNBT(tag);
    }

    protected NBTTagCompound getDescriptionNBT() {
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setString("owner", owner);
        return tag;
    }

    protected void readDescriptionNBT(NBTTagCompound tag) {
        owner = tag.getString("owner");
    }

    public final void setOwner(String username) {
        owner = username;
    }

    public final String getOwner() {
        return owner;
    }

}
