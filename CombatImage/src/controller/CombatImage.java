package controller;

import javax.swing.ImageIcon;

public final class CombatImage extends ImageIcon {
	
	private CombatImage(final String title) {
		super(CombatImage.class.getResource(title));
	}
	
	public static final CombatImage alterar_16x16 = loadImage("/16x16/alterar.png");
	
	public static CombatImage loadImage(String title) {
		return new CombatImage(title);
	}
	
}
