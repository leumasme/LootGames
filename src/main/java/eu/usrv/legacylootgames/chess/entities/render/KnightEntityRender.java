package eu.usrv.legacylootgames.chess.entities.render;

import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import eu.usrv.legacylootgames.chess.entities.FiguresData;
import eu.usrv.legacylootgames.chess.entities.IChessFigure;

public class KnightEntityRender extends RenderZombie {

    public KnightEntityRender() {
        super();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity pEntity) {
        FiguresData tFigureData = ((IChessFigure) pEntity).getFiguresData();

        if (tFigureData.isWhite()) return tFigureData.getTexture(1);
        else return tFigureData.getTexture();
    }

    @Override
    protected void preRenderCallback(EntityLivingBase pEntity, float pPartialTick) {
        super.preRenderCallback(pEntity, pPartialTick);
        float scale = ((IChessFigure) pEntity).getFiguresData().getRenderScale();
        this.shadowSize = 0.5F * scale;
        GL11.glScalef(scale, scale, scale);
    }
}
