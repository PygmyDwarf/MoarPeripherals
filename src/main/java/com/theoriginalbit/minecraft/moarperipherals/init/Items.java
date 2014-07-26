package com.theoriginalbit.minecraft.moarperipherals.init;

import com.theoriginalbit.minecraft.moarperipherals.item.ItemGeneric;
import com.theoriginalbit.minecraft.moarperipherals.item.ItemInkCartridge;
import com.theoriginalbit.minecraft.moarperipherals.reference.Settings;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * A Minecraft mod that adds more peripherals into the ComputerCraft mod.
 * Official Thread:
 * http://www.computercraft.info/forums2/index.php?/topic/19357-
 * Official Wiki:
 * http://wiki.theoriginalbit.com/moarperipherals/
 *
 * Copyright (C) 2014  Joshua Asbury (@theoriginalbit)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
public class Items {

    public static ItemGeneric itemInkCartridge;
    public static ItemGeneric itemKeyboardPart;

    public static void init() {
        if (Settings.enablePrinter) {
            itemInkCartridge = new ItemInkCartridge();
            GameRegistry.registerItem(itemInkCartridge, itemInkCartridge.getUnlocalizedName());
        }
        if (Settings.enableKeyboard) {
            itemKeyboardPart = new ItemGeneric(Settings.itemKeyboardPart, "keyboardPart");
            GameRegistry.registerItem(itemKeyboardPart, itemKeyboardPart.getUnlocalizedName());
        }
    }

    public static void oreRegistration() {

    }

}