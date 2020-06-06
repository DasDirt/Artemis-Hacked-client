package artemis.ui;
import java.awt.Color;
import java.util.ArrayList;
//4 + count*(fr.FONT_HEIGHT + 6)
//count++;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.input.Mouse;

import artemis.Category;
import artemis.Artemis;
import artemis.module;
import artemis.controllers.GuiHudCPSView;

import artemis.events.EventKey;
import artemis.events.EventTarget;
import artemis.util.EntityUtils;
import artemis.util.PingUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;



public class HUD extends GuiScreen{
	Artemis artemis;
	
	int var1;
	
	 
        
        
	
	
	EntityUtils util;
  private GuiHudCPSView cpsView = GuiHudCPSView.SEPARATE;
  public Minecraft mc = Minecraft.getMinecraft();
  
  public static class ModuleComparator implements Comparator<module>{
  @Override
	public int compare(module o1, module o2) {
		if(Minecraft.getMinecraft().fontRendererObj.getStringWidth(o1.getName()) > Minecraft.getMinecraft().fontRendererObj.getStringWidth(o2.getName())) {
			return -1;
		}
		if(Minecraft.getMinecraft().fontRendererObj.getStringWidth(o1.getName()) < Minecraft.getMinecraft().fontRendererObj.getStringWidth(o2.getName())) {
			return 1;
		}
		return 0;
	}
	  
}

  public void draw() {
    ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
    FontRenderer fr = mc.fontRendererObj;
    
  
    
    // Code in here will be executed when the client draws its 2D elements
   //util.drawEntityOnScreen(525, 325, 23, mc.mouseHelper.deltaX, mc.mouseHelper.deltaY, mc.thePlayer);
    
    Collections.sort(Artemis.getModules(),new ModuleComparator());
    //name
    fr.drawString(Artemis.Name + " v" + Artemis.version, 5, 5, 0x90000000);
    fr.drawString(Artemis.Name + " v" + Artemis.version, 4, 4, 0xffe066);
    
    //arrylist
    int count = 0;
    final int[] counter = {1};
    for(module m : Artemis.getModules()) {
    	if(m.isToggled()) {
    	//this.drawVerticalLine(sr.getScaledWidth(), startY, endY, rainbow(counter[0] * 300));
    	
    		
    		
    	Gui.drawRect(sr.getScaledHeight() - fr.getStringWidth(m.getName()) + 253, count*(fr.FONT_HEIGHT + 6), sr.getScaledHeight() - fr.getStringWidth(m.getName()) + 255, 6 + fr.FONT_HEIGHT + count*(fr.FONT_HEIGHT + 6), rainbow(counter[0] * 300));	
    	Gui.drawRect(sr.getScaledHeight() - fr.getStringWidth(m.getName()) + 255, count*(fr.FONT_HEIGHT + 6), sr.getScaledWidth(), 6 + fr.FONT_HEIGHT + count*(fr.FONT_HEIGHT + 6), 0x90000000);
    	Gui.drawRect(532, count*(fr.FONT_HEIGHT + 6), 700, 6 + fr.FONT_HEIGHT + count*(fr.FONT_HEIGHT + 6), rainbow(counter[0] * 300));	
    	fr.drawString(m.getName(), sr.getScaledHeight() - fr.getStringWidth(m.getName()) + 260, 3 + (count * 15) , rainbow(counter[0] * 300));
    	count++;
    	counter[0]++;
      }

    }
    Artemis.instance.TAB.render();
 }
  
  public void onKeyboardPress(int var1) {
	  var1 = Mouse.getEventButton();
	  
      Artemis.instance.TAB.onKeyPressed(var1);
  }
  
  public static int rainbow(int delay) {
      double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
      rainbowState %= 360;
      return Color.getHSBColor((float) (rainbowState / 360.0f), 0.8f, 0.7f).getRGB();
}
}
