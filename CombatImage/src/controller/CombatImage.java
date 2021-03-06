package controller;

import javax.swing.ImageIcon;

public final class CombatImage extends ImageIcon {
	
	private CombatImage(final String title) {
		super(CombatImage.class.getResource(title));
	}
	
	public static final CombatImage combat = loadImage("/other/combat.png");
	public static final CombatImage combatvinte_20x20 = loadImage("/20x20/combatvinte.png");
	public static final CombatImage alterar_16x16 = loadImage("/16x16/alterar.png");
	public static final CombatImage localizar_22x22 = loadImage("/22x22/localizar.png");
	public static final CombatImage adicionar_22x22 = loadImage("/22x22/adicionar.png");
	public static final CombatImage remover_22x22 = loadImage("/22x22/remover.png");
	public static final CombatImage salvar_22x22 = loadImage("/22x22/salvar.png");
	public static final CombatImage confirmar_16x16 = loadImage("/16x16/confirmar.png");
	public static final CombatImage pasta_16x16 = loadImage("/16x16/pasta.png");
	public static final CombatImage novo_16x16 = loadImage("/16x16/novo.png");
	public static final CombatImage aplicacao_16x16 = loadImage("/16x16/aplicacao.png");
	public static final CombatImage sair_16x16 = loadImage("/16x16/sair.png");
	public static CombatImage loadImage(String title) {
		return new CombatImage(title);
	}
	
}
