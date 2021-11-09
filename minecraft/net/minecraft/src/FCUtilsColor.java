package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

public enum FCUtilsColor {
	BLACK(0),
	RED(1),
	GREEN(2),
	BROWN(3),
	BLUE(4),
	PURPLE(5),
	CYAN(6),
	LIGHT_GRAY(7),
	GRAY(8),
	PINK(9),
	LIME(10),
	YELLOW(11),
	LIGHT_BLUE(12),
	MAGENTA(13),
	ORANGE(14),
	WHITE(15);
	
	public final int colorID;
	
	public final static String[] colorOrder = {"black", "red", "green", "brown", "blue", "purple", "cyan", "lightGray", "gray", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white"};
	public final static String[] colorOrderCapital = {"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White"};
	
	private FCUtilsColor(int colorID) {
		this.colorID = colorID;
	}
}