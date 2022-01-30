package com.platinumg17.rigoranthusemortisreborn.magica.common.ritual;

import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.familiar.AbstractFamiliarHolder;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.ritual.AbstractRitual;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleLineData;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.RitualLib;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class RitualSummonersPact extends AbstractRitual {

    @Override
    protected void tick() {
        Level world = getWorld();
        if(world.isClientSide){
            BlockPos pos = getPos();
            for(int i =0; i< 100; i++){
                Vec3 particlePos = new Vec3(pos.getX(), pos.getY(), pos.getZ()).add(0.5, 0, 0.5);
                particlePos = particlePos.add(ParticleUtil.pointInSphere().multiply(5,5,5));
                world.addParticle(ParticleLineData.createData(getCenterColor()),
                        particlePos.x(), particlePos.y(), particlePos.z(),
                        pos.getX()  +0.5, pos.getY() + 1  , pos.getZ() +0.5);
            }
        }
        if(!world.isClientSide && world.getGameTime() % 20 == 0){
            incrementProgress();
            if(getProgress() >= 3){
                List<Entity> entities = getWorld().getEntitiesOfClass(Entity.class, new AABB(getPos()).inflate(5));

                for(Entity entity : entities){
                    for(AbstractFamiliarHolder familiarHolder : RigoranthusEmortisRebornAPI.getInstance().getFamiliarHolderMap().values()){
                        if(familiarHolder.isEntity.test(entity)){
                            entity.remove(Entity.RemovalReason.DISCARDED);
                            ParticleUtil.spawnPoof((ServerLevel) world, entity.blockPosition());
                            world.addFreshEntity(new ItemEntity(world, entity.blockPosition().getX(), entity.blockPosition().getY(), entity.blockPosition().getZ(), familiarHolder.getOutputItem()));
                            world.playSound(null, entity.blockPosition(), SoundEvents.BOOK_PUT, SoundSource.NEUTRAL, 1.0f, 1.0f);
                        }
                    }
                }
                setFinished();
            }
        }
    }

    @Override
    public String getLangName() {
        return "Binding";
    }

    @Override
    public String getLangDescription() {
        return "The Ritual of Binding converts nearby eligible entities into Bound Scripts, used for summoning a Familiar. For more information, see the section on Familiars.";
    }

    @Override
    public String getID() {
        return RitualLib.BINDING;
    }
}