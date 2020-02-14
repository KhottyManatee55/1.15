package cofh.archersparadox.entity.projectile;

import cofh.lib.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import static cofh.archersparadox.init.ModReferences.*;

public class ChallengeArrowEntity extends AbstractArrowEntity {

    private static float baseDamage = 0.0F;
    private static final int DISTANCE_FACTOR = 4;
    private static final int MAX_DISTANCE = 10;
    private static final int DURATION = 200;
    private static final int MISS_DURATION = 600;

    public boolean discharged;
    private BlockPos origin;

    public ChallengeArrowEntity(EntityType<? extends ChallengeArrowEntity> entityIn, World worldIn) {

        super(entityIn, worldIn);
        this.damage = baseDamage;
    }

    public ChallengeArrowEntity(World worldIn, LivingEntity shooter) {

        super(CHALLENGE_ARROW_ENTITY, shooter, worldIn);
        this.damage = baseDamage;
        this.origin = shooter.getPosition();
    }

    public ChallengeArrowEntity(World worldIn, double x, double y, double z) {

        super(CHALLENGE_ARROW_ENTITY, x, y, z, worldIn);
        this.damage = baseDamage;
        this.origin = new BlockPos(x, y, z);
    }

    @Override
    protected ItemStack getArrowStack() {

        return discharged ? new ItemStack(TRAINING_ARROW_ITEM) : new ItemStack(CHALLENGE_ARROW_ITEM);
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {

        if (raytraceResultIn.getType() == RayTraceResult.Type.BLOCK) {
            if (getShooter() instanceof PlayerEntity && !Utils.isFakePlayer(getShooter())) {
                PlayerEntity shooter = (PlayerEntity) getShooter();
                if (shooter.isPotionActive(CHALLENGE_STREAK)) {
                    EffectInstance effect = shooter.getActivePotionEffect(CHALLENGE_STREAK);
                    shooter.onFinishedPotionEffect(effect);
                    shooter.removeActivePotionEffect(CHALLENGE_STREAK);

                    shooter.addPotionEffect(new EffectInstance(CHALLENGE_MISS, MISS_DURATION, 0, false, false));
                    shooter.world.playSound(null, shooter.getPosX(), shooter.getPosY(), shooter.getPosZ(), SoundEvents.BLOCK_GLASS_BREAK, shooter.getSoundCategory(), 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                }
            }
        }
        super.onHit(raytraceResultIn);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult raytraceResultIn) {

        Entity entity = raytraceResultIn.getEntity();
        if (entity instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) entity;

            if (getShooter() instanceof PlayerEntity && !Utils.isFakePlayer(getShooter())) {
                PlayerEntity shooter = (PlayerEntity) getShooter();
                if (shooter != target && !shooter.isPotionActive(CHALLENGE_MISS) && !shooter.isPotionActive(CHALLENGE_COMPLETE)) {
                    int challengeCount = 0;
                    if (shooter.isPotionActive(CHALLENGE_STREAK)) {
                        challengeCount = shooter.getActivePotionEffect(CHALLENGE_STREAK).getAmplifier() + 1;
                    }
                    Vec3d originVec = new Vec3d(origin == null ? shooter.getPosition() : origin);
                    double distance = originVec.distanceTo(this.getPositionVec());

                    if (distance >= Math.min(MAX_DISTANCE, challengeCount)) {
                        int distanceBonus = (int) (DISTANCE_FACTOR * distance);
                        shooter.addPotionEffect(new EffectInstance(CHALLENGE_STREAK, DURATION + distanceBonus, challengeCount, false, false));
                        shooter.playSound(SoundEvents.BLOCK_NOTE_BLOCK_CHIME, 1.0F, Math.min(0.6F + 0.05F * challengeCount, 1.1F));
                        shooter.world.playSound(null, shooter.getPosX(), shooter.getPosY(), shooter.getPosZ(), SoundEvents.BLOCK_NOTE_BLOCK_CHIME, shooter.getSoundCategory(), 1.0F, Math.min(0.6F + 0.05F * challengeCount, 1.1F));
                        discharged = true;
                    }
                }
            }
        }
        if (this.pickupStatus == PickupStatus.ALLOWED) {
            this.entityDropItem(this.getArrowStack(), 0.1F);
        }
        this.remove();
        this.playSound(SoundEvents.BLOCK_SAND_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
    }

    @Override
    public void setFire(int seconds) {

    }

    @Override
    public void setKnockbackStrength(int knockbackStrengthIn) {

    }

    @Override
    public void setPierceLevel(byte level) {

    }

    @Override
    public IPacket<?> createSpawnPacket() {

        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
