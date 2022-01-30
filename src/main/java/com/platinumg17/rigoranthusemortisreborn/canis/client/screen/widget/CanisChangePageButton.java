package com.platinumg17.rigoranthusemortisreborn.canis.client.screen.widget;

//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.systems.RenderSystem;
//import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.audio.SimpleSound;
//import net.minecraft.client.audio.SoundHandler;
//import net.minecraft.client.gui.components.Button;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.util.SoundEvents;
//import net.minecraft.network.chat.TextComponent;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//@OnlyIn(Dist.CLIENT)
//public class CanisChangePageButton extends Button {
//    private final boolean isForward;
//    private final boolean playTurnSound;
//    public static final ResourceLocation BOOK_LOC = RigoranthusEmortisReborn.rl("textures/gui/book.png");
//
//    public CanisChangePageButton(int x, int y, boolean forward, Button.OnPress onPress, boolean playSound) {
//        super(x, y, 23, 13, TextComponent.EMPTY, onPress);
//        this.isForward = forward;
//        this.playTurnSound = playSound;
//    }
//
//    public void renderButton(PoseStack matrixStack, int parX, int parY, float partialTicks) {
//        Minecraft mc = Minecraft.getInstance();
//        Font font = mc.font;
//        RenderSystem.setShader(GameRenderer::getPositionTexShader);
//        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
//        RenderSystem.setShaderTexture(0, Resources.SMALL_WIDGETS);
//        int i = this.getYImage(this.isHoveredOrFocused());
//        RenderSystem.enableBlend();
//        RenderSystem.defaultBlendFunc();
//        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
//        this.blit(stack, this.x, this.y, 0, i * 12, this.width, this.height);
//        this.renderBg(stack, mc, mouseX, mouseY);
//        int j = getFGColor();
//        this.drawCenteredString(stack, font, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | Mth.ceil(this.alpha * 255.0F) << 24);
//
//
//
//
//        //__________OLD____________//
//        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//        Minecraft.getInstance().getTextureManager().bind(BOOK_LOC);
//        int i = 0;
//        int j = 192;
//        if (this.isHovered()) {
//            i += 23;
//        }
//        if (!this.isForward) {
//            j += 13;
//        }
//        this.blit(matrixStack, this.x, this.y, i, j, 23, 13);
//    }
//
//    public void playDownSound(SoundHandler sound) {
//        if (this.playTurnSound) {
//            sound.play(SimpleSound.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
//        }
//    }
//}