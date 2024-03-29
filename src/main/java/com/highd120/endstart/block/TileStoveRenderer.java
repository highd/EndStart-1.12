package com.highd120.endstart.block;

import com.highd120.endstart.util.ItemUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class TileStoveRenderer extends TileEntitySpecialRenderer<TileStove> {
    @Override
    public void render(TileStove te, double x, double y, double z, float par5, int par6, float f) {
        if (te.getItem() != null) {
            ItemStack stack = te.getItem();
            if (stack != null) {
            	ItemUtil.drawItem(stack, x + 0.5, y + 0.6, z + 0.5, 0.7, false);
            }
        }
    }
}
