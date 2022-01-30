package com.platinumg17.rigoranthusemortisreborn.canis.common.block.tileentity;

import com.platinumg17.rigoranthusemortisreborn.canis.common.util.NBTUtilities;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.WorldUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.UUID;

public class PlacedTileEntity extends BlockEntity {

    private @Deprecated @Nullable LivingEntity placer;
    private @Nullable UUID placerUUID;

    public PlacedTileEntity(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState blockState) {
        super(tileEntityTypeIn, pos, blockState);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);

        this.placerUUID = NBTUtilities.getUniqueId(compound, "placerId");
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        NBTUtilities.putUniqueId(compound, "placerId", this.placerUUID);
    }

    public void setPlacer(@Nullable LivingEntity placer) {
        this.placer = placer;
        this.placerUUID = placer == null ? null : placer.getUUID();
        this.setChanged();
    }

    @Nullable
    public UUID getPlacerId() {
        return this.placerUUID;
    }

    @Nullable
    public LivingEntity getPlacer() {
        return WorldUtil.getCachedEntity(this.level, LivingEntity.class, this.placer, this.placerUUID);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, (e) -> e.getUpdateTag());
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compound = new CompoundTag();
        this.saveAdditional(compound);
        return compound;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }
}
