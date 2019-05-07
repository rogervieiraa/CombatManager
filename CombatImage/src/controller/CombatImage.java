package controller;

import javax.swing.ImageIcon;

public final class CombatImage extends ImageIcon {
	
	private CombatImage(final String title) {
		super(CombatImage.class.getResource(title));
	}
	
	public static final CombatImage combatvinte_20x20 = loadImage("/20x20/combatvinte.png");
	public static final CombatImage alterar_16x16 = loadImage("/16x16/alterar.png");
	public static final CombatImage localizar_22x22 = loadImage("/22x22/localizar.png");
	public static final CombatImage adicionar_22x22 = loadImage("/22x22/adicionar.png");
	public static final CombatImage remover_22x22 = loadImage("/22x22/remover.png");
	public static final CombatImage salvar_22x22 = loadImage("/22x22/salvar.png");
	
	public static CombatImage loadImage(String title) {
		return new CombatImage(title);
	}
	
}
