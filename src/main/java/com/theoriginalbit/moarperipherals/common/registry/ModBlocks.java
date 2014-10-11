/**
 * Copyright (c) 2013-2014, Joshua Asbury (@theoriginalbit)
 * http://wiki.theoriginalbit.com/moarperipherals/
 *
 * MoarPeripherals is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.theoriginalbit.moarperipherals.common.registry;

import com.theoriginalbit.moarperipherals.common.block.*;
import com.theoriginalbit.moarperipherals.common.config.ConfigHandler;
import com.theoriginalbit.moarperipherals.common.item.block.ItemBlockPairable;
import com.theoriginalbit.moarperipherals.common.tile.*;
import com.theoriginalbit.moarperipherals.common.reference.ComputerCraftInfo;
import com.theoriginalbit.moarperipherals.common.upgrades.UpgradeChatBox;
import com.theoriginalbit.moarperipherals.common.upgrades.UpgradeDictionary;
import com.theoriginalbit.moarperipherals.common.upgrades.UpgradeIronNote;
import cpw.mods.fml.common.registry.GameRegistry;
import dan200.computercraft.api.ComputerCraftAPI;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * @author theoriginalbit
 * @since 3/10/2014.
 */
public final class ModBlocks {

    public static final ModBlocks INSTANCE = new ModBlocks();

    private ModBlocks() {
        // prevent other instances being constructed
    }

    public static Block blockChatBox, blockPlayerDetector, blockIronNote, blockKeyboard, blockPrinter,
            blockDictionary, blockAntenna, blockAntennaCell, blockAntennaMiniCell, blockAntennaController;

    public final void register() {
        if (ConfigHandler.enablePlayerDetector) {
            blockPlayerDetector = new BlockPlayerDetector();
            GameRegistry.registerBlock(blockPlayerDetector, "blockPlayerDetector");
            GameRegistry.registerTileEntity(TilePlayerDetector.class, "tilePlayerDetector");

            // add the player detector to the ore dictionary as per request from tattyseal
            OreDictionary.registerOre("peripheralPlayerDetector", blockPlayerDetector);
        }

        if (ConfigHandler.enableChatBox) {
            blockChatBox = new BlockChatBox();
            GameRegistry.registerBlock(blockChatBox, "blockChatBox");
            GameRegistry.registerTileEntity(TileChatBox.class, "tileChatBox");
            ComputerCraftAPI.registerTurtleUpgrade(new UpgradeChatBox());
        }

        if (ConfigHandler.enableIronNote) {
            blockIronNote = new BlockIronNote();
            GameRegistry.registerBlock(blockIronNote, "blockIronNote");
            GameRegistry.registerTileEntity(TileIronNote.class, "tileIronNote");
            ComputerCraftAPI.registerTurtleUpgrade(new UpgradeIronNote());
        }

        if (ConfigHandler.enableKeyboard) {
            blockKeyboard = new BlockKeyboard();
            GameRegistry.registerBlock(blockKeyboard, ItemBlockPairable.class, "blockKeyboard");
            GameRegistry.registerTileEntity(TileKeyboard.class, "tileKeyboard");
        }

        if (ConfigHandler.enablePrinter) {
            blockPrinter = new BlockPrinter();
//            GameRegistry.registerBlock(blockPrinter, "blockPrinter");
//            GameRegistry.registerTileEntity(TilePrinter.class, "tilePrinter");
        }

        if (ConfigHandler.enableDictionary) {
            blockDictionary = new BlockDictionary();
            GameRegistry.registerBlock(blockDictionary, "blockDictionary");
            GameRegistry.registerTileEntity(TileDictionary.class, "tileDictionary");
            ComputerCraftAPI.registerTurtleUpgrade(new UpgradeDictionary());
        }

        if (ConfigHandler.enableAntenna) {
            blockAntenna = new BlockAntenna();
            GameRegistry.registerBlock(blockAntenna, "blockAntenna");

            blockAntennaCell = new BlockAntennaCell();
            GameRegistry.registerBlock(blockAntennaCell, "blockAntennaCell");

            blockAntennaMiniCell = new BlockAntennaMiniCell();
            GameRegistry.registerBlock(blockAntennaMiniCell, "blockAntennaMiniCell");

            blockAntennaController = new BlockAntennaController();
            GameRegistry.registerBlock(blockAntennaController, "blockAntennaController");
            GameRegistry.registerTileEntity(TileAntennaController.class, "tileAntennaController");
        }
    }

    public final void addRecipes() {
        if (ConfigHandler.enablePlayerDetector) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockPlayerDetector),
                    "SBS",
                    "BPB",
                    "SCS",

                    'S', "stone",
                    'B', Blocks.stone_button,
                    'P', Blocks.stone_pressure_plate,
                    'C', ComputerCraftInfo.cc_cable
            ));
        }

        if (ConfigHandler.enableChatBox) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockChatBox),
                    "GGG",
                    "GNG",
                    "GCG",

                    'G', "ingotGold",
                    'N', Blocks.noteblock,
                    'C', ComputerCraftInfo.cc_cable
            ));
        }

        if (ConfigHandler.enableIronNote) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockIronNote),
                    "III",
                    "INI",
                    "ICI",

                    'I', "ingotIron",
                    'N', Blocks.noteblock,
                    'C', ComputerCraftInfo.cc_cable
            ));
        }

        if (ConfigHandler.enableKeyboard) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockKeyboard),
                    "KKK",

                    'K', ModItems.itemKeyboardPart
            ));
        }

        if (ConfigHandler.enableDictionary) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockDictionary),
                    "SBS",
                    "BDB",
                    "SCS",

                    'S', "stone",
                    'B', Items.book,
                    'D', "gemDiamond",
                    'C', ComputerCraftInfo.cc_cable
            ));
        }

        if (ConfigHandler.enableAntenna) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockAntenna, 4),
                    "ICI",
                    "ICI",
                    "ICI",

                    'I', "ingotIron",
                    'C', ComputerCraftInfo.cc_cable
            ));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockAntennaCell),
                    "IMI",
                    "MCM",
                    "IMI",

                    'I', "ingotIron",
                    'M', ComputerCraftInfo.cc_wirelessModem,
                    'C', ComputerCraftInfo.cc_cable
            ));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockAntennaMiniCell),
                    "IMI",
                    "MCM",
                    "IMI",

                    'I', "ingotIron",
                    'M', ModItems.itemCellPart,
                    'C', ComputerCraftInfo.cc_cable
            ));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockAntennaController),
                    "ICI",
                    "IWI",
                    "IPI",

                    'I', "ingotIron",
                    'C', ComputerCraftInfo.cc_cable,
                    'W', ComputerCraftInfo.cc_wiredModem,
                    'P', ComputerCraftInfo.cc_blockComputer
            ));
        }
    }

}