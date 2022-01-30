package com.platinumg17.rigoranthusemortisreborn.items.weapons.type.projectiles.basetype;

import com.platinumg17.rigoranthusemortisreborn.canis.common.SpecializedEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.entity.item.ReturningProjectileEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.level.Level;

public class ReturningProjectileWeaponItem extends ConsumableProjectileWeaponItem {
    protected final int maxTick;

    public ReturningProjectileWeaponItem(Properties properties, float velocity, float accuracy, int damage, int maxTick) {
        super(properties, velocity, accuracy, damage);
        this.maxTick = maxTick;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack item = playerIn.getItemInHand(handIn);

        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.2F);

        if(!worldIn.isClientSide) {

            ReturningProjectileEntity projectileEntity = new ReturningProjectileEntity(SpecializedEntityTypes.RETURNING_PROJECTILE.get(), playerIn, worldIn, maxTick, false);
            projectileEntity.setItem(item);
            projectileEntity.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, velocity, accuracy);
            projectileEntity.setNoGravity(true);
            worldIn.addFreshEntity(projectileEntity);
        }
        item.hurtAndBreak(1, playerIn, playerEntity -> playerEntity.broadcastBreakEvent(InteractionHand.MAIN_HAND));

        playerIn.getCooldowns().addCooldown(this, maxTick);
        playerIn.awardStat(Stats.ITEM_USED.get(this));
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, item);
    }
}