package com.platinumg17.rigoranthusemortisreborn.canis.client.tileentity.renderer;

import com.google.common.base.Objects;
import com.mojang.blaze3d.vertex.PoseStack;
import com.platinumg17.rigoranthusemortisreborn.canis.common.block.tileentity.CanisBedTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class CanisBedRenderer implements BlockEntityRenderer<CanisBedTileEntity> {
    public CanisBedRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) { }
    @Override
    public void render(CanisBedTileEntity tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (tileEntityIn.getBedName() != null && this.isLookingAtBed(tileEntityIn)) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5D, 0.5D, 0.5D);
            // TODO RenderUtil.renderLabelWithScale(true, Minecraft.getInstance().getEntityRenderDispatcher(), tileEntityIn.getBedName(), matrixStackIn, bufferIn, combinedLightIn, 0.025F, 1.2F);
            matrixStackIn.popPose();
        }
    }

    public boolean isLookingAtBed(CanisBedTileEntity tileEntityIn) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.hitResult != null && mc.hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockpos = ((BlockHitResult) mc.hitResult).getBlockPos();
            return Objects.equal(blockpos, tileEntityIn.getBlockPos());
        }
        return false;
    }
}