package net.binaryaura.customize.client.gui.huditem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class HudItemObjective extends HudItemText {

	public HudItemObjective(String name) {
		super(name);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void renderHUDItem(ScaledResolution res, RenderGameOverlayEvent eventParent) {
		Scoreboard scoreboard = this.mc.theWorld.getScoreboard();
        ScoreObjective scoreobjective = null;
        ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(this.mc.thePlayer.getName());

        if (scoreplayerteam != null)
        {
            int i1 = scoreplayerteam.func_178775_l().getColorIndex();

            if (i1 >= 0)
            {
                scoreobjective = scoreboard.getObjectiveInDisplaySlot(3 + i1);
            }
        }

        scoreobjective = scoreobjective != null ? scoreobjective : scoreboard.getObjectiveInDisplaySlot(1);
        if(scoreobjective == null) return;
		
        Collection collection = scoreboard.getSortedScores(scoreobjective);
        ArrayList arraylist = Lists.newArrayList(Iterables.filter(collection, new Predicate()
        {
            private static final String __OBFID = "CL_00001958";
            public boolean func_178903_a(Score p_178903_1_)
            {
                return p_178903_1_.getPlayerName() != null && !p_178903_1_.getPlayerName().startsWith("#");
            }
            public boolean apply(Object p_apply_1_)
            {
                return this.func_178903_a((Score)p_apply_1_);
            }
        }));
        ArrayList arraylist1;

        if (arraylist.size() > 15)
        {
            arraylist1 = Lists.newArrayList(Iterables.skip(arraylist, collection.size() - 15));
        }
        else
        {
            arraylist1 = arraylist;
        }

        int i = fontRenderer.getStringWidth(scoreobjective.getDisplayName());
        log.info(scoreobjective.getDisplayName() + ": " + i);
        String s;

        for (Iterator iterator = arraylist1.iterator(); iterator.hasNext(); i = Math.max(i, fontRenderer.getStringWidth(s)))
        {
            Score score = (Score)iterator.next();
            scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
            s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName()) + ": " + EnumChatFormatting.RED + score.getScorePoints();
        	log.info(fontRenderer.getStringWidth(s));
        }

        int i1 = arraylist1.size() * fontRenderer.FONT_HEIGHT;
        int j1 = res.getScaledHeight() / 2 + i1 / 3;
        byte b0 = 3;
        int k1 = res.getScaledWidth() - i - b0;
        log.info("k1: " + res.getScaledWidth() + ", " + i + ", " + b0);
        int j = 0;
        Iterator iterator1 = arraylist1.iterator();

        while (iterator1.hasNext())
        {
            Score score1 = (Score)iterator1.next();
            ++j;
            ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
            String s1 = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score1.getPlayerName());
            String s2 = EnumChatFormatting.RED + "" + score1.getScorePoints();
            int k = j1 - j * fontRenderer.FONT_HEIGHT;
            int l = res.getScaledWidth() - b0 + 2;
            log.info("l: " + res.getScaledWidth() + ", " + b0 + ", " + l);
            Gui.drawRect(k1 - 2, k, l, k + fontRenderer.FONT_HEIGHT, 1342177280);
            log.info(l - (k1 - 2));
            fontRenderer.drawString(s1, k1, k, 553648127);
            fontRenderer.drawString(s2, l - fontRenderer.getStringWidth(s2), k, 553648127);

            if (j == arraylist1.size())
            {
                String s3 = scoreobjective.getDisplayName();
                Gui.drawRect(k1 - 2, k - fontRenderer.FONT_HEIGHT - 1, l, k - 1, 1610612736);
                Gui.drawRect(k1 - 2, k - 1, l, k, 1342177280);
                fontRenderer.drawString(s3, k1 + i / 2 - fontRenderer.getStringWidth(s3) / 2, k - fontRenderer.FONT_HEIGHT, 553648127);
            }
        }
    }

	@Override
	protected int getDeltaX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getDeltaY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getBGColor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getColor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getBGAlpha() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getAlpha() {
		// TODO Auto-generated method stub
		return 0;
	}

}
